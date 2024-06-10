package SpaceShooters;

import SpaceShooters.GameMode.GameMode;
import SpaceShooters.Ship.PlayerShipData;
import SpaceShooters.Ship.Ship;
import SpaceShooters.Ship.ShipFactory;

public class Player {
    private int level;
    private int normalModeLevel;
    private PlayerShipData shipData;
    private double experience;
    private double money;
    private double moneyMultiplier;
    private Ship playerShip;
    private String username;

    public Player(int level, int normalModeLevel, PlayerShipData shipData, double experience, double money) {
        this.level = level;
        this.normalModeLevel = normalModeLevel;
        this.shipData = shipData;
        this.experience = experience;
        this.money = money;
        this.moneyMultiplier = Math.pow(1.014, level);
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
        return shipData;
    }

    public Ship getPlayerShip(){
        return playerShip;
    }

    public void upgradeCrew() {
        // Implementacja
    }

    public void upgradeCannon() {
        // Implementacja
    }

    public void upgradeShield() {
        // Implementacja
    }

    public void receiveExperience(int experience) {
        // Implementacja
    }

    private void createPlayerShip() {
        playerShip = ShipFactory.createPlayerShip(shipData);
    }
}
