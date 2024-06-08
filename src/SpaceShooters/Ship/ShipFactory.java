package SpaceShooters.Ship;

import SpaceShooters.GameMode.GameMode;
import SpaceShooters.GameMode.ShipObserver;
import SpaceShooters.Shot;

public class ShipFactory {
    private static final int SHIPWIDTH = 30;
//    static int shipId;
    public static Ship createPlayerShip(PlayerShipData data){
        return new Ship(Shot.PLAYER_Y, SHIPWIDTH, data.shipLevel, data.crewLevel, data.cannonLevel, data.shieldLevel, false, true);
    }

    public static Ship createPlayerShip(PlayerShipData data, ShipObserver observer){
        return new Ship(Shot.PLAYER_Y, SHIPWIDTH, data.shipLevel, data.crewLevel, data.cannonLevel, data.shieldLevel, false, observer, true);
    }

    public static Ship createEnemyShip(int level, ShipObserver observer){
        return new Ship(Shot.ENEMY_Y, SHIPWIDTH, level, true, observer);
    }

    public static Ship createEnemyShip(int level){
        return new Ship(Shot.ENEMY_Y, SHIPWIDTH, level, true);
    }
}
