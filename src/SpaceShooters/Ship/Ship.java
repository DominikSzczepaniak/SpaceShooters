package SpaceShooters.Ship;

import SpaceShooters.GameFrame;
import SpaceShooters.GameMode.GameMode;
import SpaceShooters.GameMode.ShipObserver;
import SpaceShooters.Shot;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import java.util.random.RandomGenerator;
public class Ship {
    private final ShipCannon cannon;
    private final ShipCrew crew;
    private final ShipShield shield;
    private boolean barrierActive = false;
    int baseHp;
    int level;
    int currentHp;
    private int x = RandomGenerator.getDefault().nextInt(0, GameFrame.MAXWIDTH);
    private final int y;
    private final int width;
    private Instant lastShot = Instant.now().minusMillis(10000); //making sure that last_shot is higher than any possible shot cooldown
    private Instant barrierDestroyed = Instant.now().minusMillis(100000); //when barrier destroyed apply cooldown before we can start repairing
    public final boolean lookingDown;
    private ShipObserver shipObserver = null;
    private boolean playerShip = false;
    int loopValue = 0;

    public Ship(int y, int width, int level, boolean lookingDown) {
        this.y = y;
        this.width = width;
        this.level = level;
        crew = new ShipCrew(level);
        cannon = new ShipCannon(level);
        shield = new ShipShield(level);
        calculateBaseHp();
        currentHp = baseHp;
        this.lookingDown = lookingDown;
    }


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

    private void calculateBaseHp(){
        baseHp = 200 + level * 50 + shield.hpBonus;
    }

    public void shoot(){
        Instant time_now = Instant.now();
        long cooldown_timer = Duration.between(lastShot, time_now).toMillis();
        if((double) cooldown_timer /1000 >= crew.reloadTime){
            lastShot = time_now;
            Shot shot = new Shot(cannon.shotDamage, x, y, cannon.shotSpeed, playerShip);
            shipObserver.shipShot(shot);
        }
    }

    public void toggleBarrierActivation(){
        barrierActive = !barrierActive;
    }

    public void destroy(){
        if(playerShip){
            shipObserver.playerShipDestroyed();
        }
        shipObserver.shipDestroyed(this);
    }

    public void setupObserver(GameMode mode){
        shipObserver = new ShipObserver(mode);
    }

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

    private int damagePassiveBarrier(int damage){
        if(damage > getPasiveShieldHp()){
            int remaining = damage - getPasiveShieldHp();
            System.out.println(remaining);
            setPassiveBarrierHp(0);
            return remaining;
        }
        else{
            setPassiveBarrierHp(getPasiveShieldHp() - damage);
            return 0;
        }
    }

    private void damageHp(int damage){
        if(damage > currentHp){
            destroy();
        }
        else{
            currentHp -= damage;
        }
    }

    public void receiveDamage(Shot shot){
        if(barrierActive){
            damageHp(damagePassiveBarrier(damageActiveBarrier(shot.getDamage())));
        }
        else{
            damageHp(damagePassiveBarrier(shot.getDamage()));
        }
    }

    public void move(int dir){
        int units = crew.movementSpeed;
        if (dir == 0){
            x -= units;
        }
        else{
            x += units;
        }
    }

    public void move(int dir, int units){
        if (dir == 0){
            x -= units;
        }
        else{
            x += units;
        }
    }

    public void calculateShield(){
        loopValue++;
        if(loopValue == 60){
            setPassiveBarrierHp(Math.min(shield.maximumPassiveBarrierHp, getPasiveShieldHp() + Math.floorDiv(crew.getPassiveBarrierRenewalValue(), 4)));
            setActiveBarrierHp(Math.min(shield.maximumActiveBarrierHp, getActiveShieldHp() + Math.floorDiv(crew.getActiveBarrierRenewalValue(), 4)));
            currentHp = Math.min(currentHp + baseHp/10, baseHp);
            loopValue=0;
        }
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getX() {
        return x;
    }

    public int getCrewLevel(){
        return crew.getLevel();
    }

    public int getShieldLevel(){
        return shield.getLevel();
    }

    public int getCannonLevel(){
        return cannon.getLevel();
    }

    public Instant getBarrierDestroyed(){
        return barrierDestroyed;
    }

    public void upgradeCrew(boolean natural){
        crew.levelUp(natural);
    }

    public void upgradeShield(boolean natural){
        shield.levelUp(natural);
    }

    public void upgradeCannon(boolean natural){
        cannon.levelUp(natural);
    }

    public int getPasiveShieldHp(){
        return shield.currentPassiveBarrierHp;
    }

    public int getActiveShieldHp(){
        return shield.currentActiveBarrierHp;
    }

    public void setPassiveBarrierHp(int hp){
        shield.currentPassiveBarrierHp = hp;
    }
    public void setActiveBarrierHp(int hp){
        shield.currentActiveBarrierHp = hp;
    }

    public Instant getLastShot() {
        return lastShot;
    }

    public int getBaseHp() {
        return baseHp;
    }

    public int getCannonUpgradeCost(){
        return cannon.cost;
    }

    public int getShieldUpgradeCost(){
        return shield.cost;
    }

    public int getCrewUpgradeCost(){
        return crew.cost;
    }

    public boolean isShielded(){
        return barrierActive;
    }

    public int getCurrentHp() {
        return currentHp;
    }

    public boolean getBarrierActive(){
        return barrierActive;
    }
}

