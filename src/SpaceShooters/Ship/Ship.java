package SpaceShooters.Ship;

import SpaceShooters.GameFrame;
import SpaceShooters.GameMode.GameMode;
import SpaceShooters.GameMode.ShipObserver;
import SpaceShooters.Shot;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;

/**
 * Represents a spaceship in the game.
 */
public class Ship {
    private final ShipCannon cannon;
    private final ShipCrew crew;
    private final ShipShield shield;
    private boolean barrierActive = false;
    int baseHp;
    int level;
    int currentHp;
    private int x;
    private final int y;
    private final int width;
    private Instant lastShot = Instant.now().minusMillis(10000); // Ensures last_shot is initially higher than any possible shot cooldown
    private Instant barrierDestroyed = Instant.now().minusMillis(100000); // Cooldown before repairing barrier after it's destroyed
    public final boolean lookingDown;
    private ShipObserver shipObserver = null;
    private boolean playerShip = false;
    private int loopValue = 0;

    // Random generator for initial x position
    private static final Random random = new Random();

    /**
     * Constructs a ship with specified parameters.
     *
     * @param y           The y-coordinate of the ship.
     * @param width       The width of the ship.
     * @param level       The level of the ship.
     * @param lookingDown Whether the ship is looking down.
     */
    public Ship(int y, int width, int level, boolean lookingDown) {
        this.y = y;
        this.width = width;
        this.level = level;
        this.x = random.nextInt(GameFrame.MAXWIDTH); // Set initial x randomly within game width
        crew = new ShipCrew(level);
        cannon = new ShipCannon(level);
        shield = new ShipShield(level);
        calculateBaseHp();
        currentHp = baseHp;
        this.lookingDown = lookingDown;
    }

    /**
     * Constructs a ship with specified parameters and an observer.
     *
     * @param y           The y-coordinate of the ship.
     * @param width       The width of the ship.
     * @param level       The level of the ship.
     * @param lookingDown Whether the ship is looking down.
     * @param observer    The observer for the ship.
     */
    public Ship(int y, int width, int level, boolean lookingDown, ShipObserver observer) {
        this.y = y;
        this.width = width;
        this.level = level;
        crew = new ShipCrew(level);
        cannon = new ShipCannon(level);
        shield = new ShipShield(level);
        calculateBaseHp();
        currentHp = baseHp;
        this.lookingDown = lookingDown;
        shipObserver = observer;
    }

    /**
     * Constructs a ship with detailed parameters.
     *
     * @param y            The y-coordinate of the ship.
     * @param width        The width of the ship.
     * @param shipLevel    The level of the ship.
     * @param crewLevel    The level of the crew.
     * @param cannonLevel  The level of the cannon.
     * @param shieldLevel  The level of the shield.
     * @param lookingDown  Whether the ship is looking down.
     * @param observer     The observer for the ship.
     * @param playerShip   Whether the ship is controlled by the player.
     */
    public Ship(int y, int width, int shipLevel, int crewLevel, int cannonLevel, int shieldLevel, boolean lookingDown, ShipObserver observer, boolean playerShip){
        this.y = y;
        this.width = width;
        this.level = shipLevel;
        crew = new ShipCrew(crewLevel);
        shield = new ShipShield(shieldLevel);
        cannon = new ShipCannon(cannonLevel);
        calculateBaseHp();
        currentHp = baseHp;
        this.lookingDown = lookingDown;
        this.shipObserver = observer;
        this.playerShip = playerShip;
    }

    /**
     * Constructs a ship with detailed parameters (without observer).
     *
     * @param y            The y-coordinate of the ship.
     * @param width        The width of the ship.
     * @param shipLevel    The level of the ship.
     * @param crewLevel    The level of the crew.
     * @param cannonLevel  The level of the cannon.
     * @param shieldLevel  The level of the shield.
     * @param lookingDown  Whether the ship is looking down.
     * @param playerShip   Whether the ship is controlled by the player.
     */
    public Ship(int y, int width, int shipLevel, int crewLevel, int cannonLevel, int shieldLevel, boolean lookingDown, boolean playerShip){
        this.y = y;
        this.width = width;
        this.level = shipLevel;
        crew = new ShipCrew(crewLevel);
        shield = new ShipShield(shieldLevel);
        cannon = new ShipCannon(cannonLevel);
        calculateBaseHp();
        currentHp = baseHp;
        this.lookingDown = lookingDown;
        this.playerShip = playerShip;
    }

    /**
     * Calculates the base HP of the ship based on its level and shield bonuses.
     */
    private void calculateBaseHp(){
        baseHp = 200 + level * 50 + shield.hpBonus;
    }

    /**
     * Initiates a shooting action from the ship.
     * Checks cooldown and creates a new Shot instance if possible.
     */
    public void shoot(){
        Instant time_now = Instant.now();
        long cooldown_timer = Duration.between(lastShot, time_now).toMillis();
        if((double) cooldown_timer /1000 >= crew.reloadTime){
            lastShot = time_now;
            Shot shot = new Shot(cannon.shotDamage, x, y, cannon.shotSpeed, playerShip);
            shipObserver.shipShot(shot);
        }
    }

    /**
     * Toggles the activation of the ship's barrier.
     * Only activates if the active shield HP is greater than 0.
     */
    public void toggleBarrierActivation(){
        if(getActiveShieldHp() == 0){
            return;
        }
        barrierActive = !barrierActive;
    }

    /**
     * Initiates the destruction sequence for the ship.
     * Notifies observers about ship destruction.
     */
    public void destroy(){
        if(playerShip){
            shipObserver.playerShipDestroyed();
        }
        shipObserver.shipDestroyed(this);
    }

    /**
     * Sets up an observer for the ship based on the game mode.
     *
     * @param mode The game mode observer to be set.
     */
    public void setupObserver(GameMode mode){
        shipObserver = new ShipObserver(mode);
    }

    /**
     * Handles damage to the ship's active barrier.
     * Activates barrier destroyed state if damage exceeds active barrier HP.
     *
     * @param damage The amount of damage to the ship.
     * @return Remaining damage after active barrier damage.
     */
    private int damageActiveBarrier(int damage){
        if(damage > getActiveShieldHp()){
            int remaining = damage - getActiveShieldHp();
            setActiveBarrierHp(0);
            barrierActive = false;
            barrierDestroyed = Instant.now();
            return remaining;
        }
        else{
            setActiveBarrierHp(getActiveShieldHp() - damage);
            return 0;
        }
    }

    /**
     * Handles damage to the ship's passive barrier.
     * Disables passive barrier if damage exceeds its HP.
     *
     * @param damage The amount of damage to the ship.
     * @return Remaining damage after passive barrier damage.
     */
    private int damagePassiveBarrier(int damage){
        if(damage > getPasiveShieldHp()){
            int remaining = damage - getPasiveShieldHp();
            setPassiveBarrierHp(0);
            return remaining;
        }
        else{
            setPassiveBarrierHp(getPasiveShieldHp() - damage);
            return 0;
        }
    }

    /**
     * Inflicts damage to the ship's current HP.
     * If the damage exceeds the current HP, the ship is destroyed.
     *
     * @param damage The amount of damage to inflict.
     */
    private void damageHp(int damage){
        if(damage > currentHp){
            destroy();
        }
        else{
            currentHp -= damage;
        }
    }

    /**
     * Applies damage to the ship's HP after handling barriers.
     *
     * @param shot The shot causing the damage.
     */
    public void receiveDamage(Shot shot){
        if(barrierActive){
            damageHp(damagePassiveBarrier(damageActiveBarrier(shot.getDamage())));
        }
        else{
            damageHp(damagePassiveBarrier(shot.getDamage()));
        }
    }

    /**
     * Moves the ship left or right based on the direction and crew movement speed.
     *
     * @param dir The direction of movement (0 for left, 1 for right).
     */
    public void move(int dir){
        int units = crew.movementSpeed;
        if (dir == 0){
            x -= units;
        }
        else{
            x += units;
        }
    }

    /**
     * Moves the ship left or right by a specified number of units.
     *
     * @param dir   The direction of movement (0 for left, 1 for right).
     * @param units The number of units to move the ship.
     */
    public void move(int dir, int units){
        if (dir == 0){
            x -= units;
        }
        else{
            x += units;
        }
    }

    /**
     * Calculates shield regeneration and HP regeneration for the ship.
     * Triggered periodically to restore shields and HP over time.
     */
    public void calculateShield(){
        loopValue++;
        if(loopValue == 60){
            setPassiveBarrierHp(Math.min(shield.maximumPassiveBarrierHp, getPasiveShieldHp() + Math.floorDiv(crew.getPassiveBarrierRenewalValue(), 4)));
            if(barrierDestroyed.plusSeconds(3).isBefore(Instant.now())){
                setActiveBarrierHp(Math.min(shield.maximumActiveBarrierHp, getActiveShieldHp() + Math.floorDiv(crew.getActiveBarrierRenewalValue(), 4)));
            }
            currentHp = Math.min(currentHp + baseHp/10, baseHp);
            loopValue=0;
        }
    }

    /**
     * Returns the y-coordinate of the ship.
     *
     * @return The y-coordinate.
     */
    public int getY() {
        return y;
    }

    /**
     * Returns the width of the ship.
     *
     * @return The width.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the current x-coordinate of the ship.
     *
     * @return The x-coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the level of the ship's crew.
     *
     * @return The crew level.
     */
    public int getCrewLevel(){
        return crew.getLevel();
    }

    /**
     * Returns the level of the ship's shield.
     *
     * @return The shield level.
     */
    public int getShieldLevel(){
        return shield.getLevel();
    }

    /**
     * Returns the level of the ship's cannon.
     *
     * @return The cannon level.
     */
    public int getCannonLevel(){
        return cannon.getLevel();
    }

    /**
     * Upgrades the ship's crew to the next level.
     *
     * @param natural Whether the upgrade is natural (through gameplay) or not.
     */
    public void upgradeCrew(boolean natural){
        crew.levelUp(natural);
    }

    /**
     * Upgrades the ship's shield to the next level.
     *
     * @param natural Whether the upgrade is natural (through gameplay) or not.
     */
    public void upgradeShield(boolean natural){
        shield.levelUp(natural);
    }

    /**
     * Upgrades the ship's cannon to the next level.
     *
     * @param natural Whether the upgrade is natural (through gameplay) or not.
     */
    public void upgradeCannon(boolean natural){
        cannon.levelUp(natural);
    }

    /**
     * Returns the current HP of the ship's passive barrier.
     *
     * @return The current passive barrier HP.
     */
    public int getPasiveShieldHp(){
        return shield.currentPassiveBarrierHp;
    }

    /**
     * Returns the current HP of the ship's active barrier.
     *
     * @return The current active barrier HP.
     */
    public int getActiveShieldHp(){
        return shield.currentActiveBarrierHp;
    }

    /**
     * Sets the HP of the ship's passive barrier.
     *
     * @param hp The HP value to set.
     */
    public void setPassiveBarrierHp(int hp){
        shield.currentPassiveBarrierHp = hp;
    }

    /**
     * Sets the HP of the ship's active barrier.
     *
     * @param hp The HP value to set.
     */
    public void setActiveBarrierHp(int hp){
        shield.currentActiveBarrierHp = hp;
    }

    /**
     * Returns the timestamp of the last shot fired by the ship.
     *
     * @return The timestamp of the last shot.
     */
    public Instant getLastShot() {
        return lastShot;
    }

    /**
     * Returns the base HP of the ship (excluding shields and level bonuses).
     *
     * @return The base HP.
     */
    public int getBaseHp() {
        return baseHp;
    }

    /**
     * Returns the upgrade cost of the ship's cannon.
     *
     * @return The cannon upgrade cost.
     */
    public int getCannonUpgradeCost(){
        return cannon.cost;
    }

    /**
     * Returns the upgrade cost of the ship's shield.
     *
     * @return The shield upgrade cost.
     */
    public int getShieldUpgradeCost(){
        return shield.cost;
    }

    /**
     * Returns the upgrade cost of the ship's crew.
     *
     * @return The crew upgrade cost.
     */
    public int getCrewUpgradeCost(){
        return crew.cost;
    }

    /**
     * Checks if the ship's barrier is currently active.
     *
     * @return True if the barrier is active, false otherwise.
     */
    public boolean isShielded(){
        return barrierActive;
    }

    /**
     * Returns the current HP of the ship.
     *
     * @return The current HP.
     */
    public int getCurrentHp() {
        return currentHp;
    }

    /**
     * Checks if the ship's barrier is active.
     *
     * @return True if the barrier is active, false otherwise.
     */
    public boolean getBarrierActive(){
        return barrierActive;
    }
}