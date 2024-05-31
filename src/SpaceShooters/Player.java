package SpaceShooters;

import SpaceShooters.Ship.PlayerShipData;
import SpaceShooters.Ship.Ship;
import SpaceShooters.Ship.ShipFactory;

public class Player {
    //we want to keep levels of each crew, cannon, shield and ship so we can just recreate it with factory
    int level;
    int normalModeLevel;
    PlayerShipData shipData;
    double experience;
    double money;
    double moneyMultiplier;
    Ship playerShip;
    public Player(int level, int normalModeLevel, PlayerShipData shipData, double experience, double money){
        this.level = level;
        this.normalModeLevel = normalModeLevel;
        this.shipData = shipData;
        this.experience = experience;
        this.money = money;
        moneyMultiplier = Math.pow(1.014, level);
        createPlayerShip();
    }

    void upgradeCrew(){

    }

    void upgradeCannon(){

    }

    void upgradeShield(){

    }

    void ReceiveExperience(int experience){

    }

    void createPlayerShip(){
        playerShip = ShipFactory.createPlayerShip(shipData);
    }
}
