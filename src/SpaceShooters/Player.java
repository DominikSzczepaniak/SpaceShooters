package SpaceShooters;

import SpaceShooters.Ship.PlayerShipData;
import SpaceShooters.Ship.Ship;
import SpaceShooters.Ship.ShipFactory;

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

    public String getUsername() {
        return username;
    }

    public int getLevel() {
        return level;
    }

    public int getNormalModeLevel() {
        return normalModeLevel;
    }

    public double getExperience() {
        return experience;
    }

    public double getMoney() {
        return money;
    }

    public PlayerShipData getShipData() {
        shipData = new PlayerShipData(playerShip);
        return shipData;
    }

    public Ship getPlayerShip(){
        return playerShip;
    }

    public void upgradeCrew() {
        if(money >= playerShip.getCrewUpgradeCost()){
            money -= playerShip.getCrewUpgradeCost();
            playerShip.upgradeCrew(false);
        }
    }

    public void upgradeCannon() {
        if(money >= playerShip.getCannonUpgradeCost()){
            money -= playerShip.getCannonUpgradeCost();
            playerShip.upgradeCannon(false);
        }
    }

    public void upgradeShield() {
        if(money >= playerShip.getShieldUpgradeCost()){
            money -= playerShip.getShieldUpgradeCost();
            playerShip.upgradeShield(false);
        }
    }

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

    private void calculateLevelStats(){
        nextLevelExperience = level * 50 + level*10;
        this.moneyMultiplier = Math.pow(1.014, level);
    }

    public void receiveMoney(int money){
        this.money += Math.floor(money * moneyMultiplier);
    }

    private void createPlayerShip() {
        playerShip = ShipFactory.createPlayerShip(shipData);
    }

    public void toggleShield(){
        playerShip.toggleBarrierActivation();
    }

    public void shoot(){
        playerShip.shoot();
    }

    public void moveLeft(){
        playerShip.move(0);
    }

    public void moveRight(){
        playerShip.move(1);
    }

    public Ship getShip(){
        return playerShip;
    }
}
