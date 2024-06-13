package SpaceShooters;

import SpaceShooters.Ship.PlayerShipData;
import SpaceShooters.Ship.Ship;
import SpaceShooters.Ship.ShipFactory;

/**
 * Represents a player in the game, managing player-specific attributes and actions.
 */
public class Player {
    private int level;
    private final int normalModeLevel;
    private PlayerShipData shipData;
    private int experience;
    private double nextLevelExperience;
    private double money;
    private double moneyMultiplier;
    private Ship playerShip;
    private final String username;

    /**
     * Constructs a Player object with specified initial attributes.
     *
     * @param level The player's current level.
     * @param normalModeLevel The player's level in normal mode.
     * @param shipData The data representing the player's ship.
     * @param experience The player's current experience points.
     * @param money The player's current money.
     * @param username The player's username.
     */
    public Player(int level, int normalModeLevel, PlayerShipData shipData, int experience, double money, String username) {
        this.level = level;
        this.normalModeLevel = normalModeLevel;
        this.shipData = shipData;
        this.experience = experience;
        this.username = username;
        calculateLevelStats();
        this.money = money;
        createPlayerShip();
    }

    /**
     * Retrieves the player's username.
     *
     * @return The player's username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Retrieves the player's current level.
     *
     * @return The player's current level.
     */
    public int getLevel() {
        return level;
    }

    /**
     * Retrieves the player's level in normal mode.
     *
     * @return The player's level in normal mode.
     */
    public int getNormalModeLevel() {
        return normalModeLevel;
    }

    /**
     * Retrieves the player's current experience points.
     *
     * @return The player's current experience points.
     */
    public double getExperience() {
        return experience;
    }

    /**
     * Retrieves the player's current money.
     *
     * @return The player's current money.
     */
    public double getMoney() {
        return money;
    }

    /**
     * Retrieves the data representing the player's ship.
     *
     * @return The PlayerShipData object representing the player's ship.
     */
    public PlayerShipData getShipData() {
        shipData = new PlayerShipData(playerShip);
        return shipData;
    }

    /**
     * Retrieves the player's ship object.
     *
     * @return The Ship object representing the player's ship.
     */
    public Ship getPlayerShip(){
        return playerShip;
    }

    /**
     * Upgrades the crew of the player's ship if sufficient money is available.
     */
    public void upgradeCrew() {
        if(money >= playerShip.getCrewUpgradeCost()){
            money -= playerShip.getCrewUpgradeCost();
            playerShip.upgradeCrew(false);
        }
    }

    /**
     * Upgrades the cannon of the player's ship if sufficient money is available.
     */
    public void upgradeCannon() {
        if(money >= playerShip.getCannonUpgradeCost()){
            money -= playerShip.getCannonUpgradeCost();
            playerShip.upgradeCannon(false);
        }
    }

    /**
     * Upgrades the shield of the player's ship if sufficient money is available.
     */
    public void upgradeShield() {
        if(money >= playerShip.getShieldUpgradeCost()){
            money -= playerShip.getShieldUpgradeCost();
            playerShip.upgradeShield(false);
        }
    }

    /**
     * Increases the player's experience points and upgrades ship components if the player levels up.
     *
     * @param experience The amount of experience points to add.
     */
    public void receiveExperience(int experience) {
        this.experience += experience;
        if(this.experience >= nextLevelExperience){
            playerShip.upgradeCannon(true);
            playerShip.upgradeCrew(true);
            playerShip.upgradeShield(true);
            this.experience -= (int) nextLevelExperience;
            level++;
            calculateLevelStats();
        }
    }

    /**
     * Calculates the next level's experience requirement and money multiplier based on the player's current level.
     */
    private void calculateLevelStats(){
        nextLevelExperience = level * 50 + level * 10;
        this.moneyMultiplier = Math.pow(1.014, level);
    }

    /**
     * Adds money to the player's current amount, scaled by the money multiplier.
     *
     * @param money The amount of money to add.
     */
    public void receiveMoney(int money){
        this.money += Math.floor(money * moneyMultiplier);
    }

    /**
     * Creates the player's ship using the stored ship data.
     */
    private void createPlayerShip() {
        playerShip = ShipFactory.createPlayerShip(shipData);
    }

    /**
     * Toggles the activation of the shield on the player's ship.
     */
    public void toggleShield(){
        playerShip.toggleBarrierActivation();
    }

    /**
     * Initiates the shooting action on the player's ship.
     */
    public void shoot(){
        playerShip.shoot();
    }

    /**
     * Moves the player's ship left.
     */
    public void moveLeft(){
        playerShip.move(0);
    }

    /**
     * Moves the player's ship right.
     */
    public void moveRight(){
        playerShip.move(1);
    }

    /**
     * Retrieves the player's ship object.
     *
     * @return The Ship object representing the player's ship.
     */
    public Ship getShip(){
        return playerShip;
    }
}
