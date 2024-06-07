package SpaceShooters.Ship;

import SpaceShooters.GameMode.GameMode;
import SpaceShooters.Shot;

public class ShipFactory {
    private static final int SHIPWIDTH = 30;
//    static int shipId;
    public static Ship createPlayerShip(PlayerShipData data, GameMode owner){
        return new Ship(Shot.PLAYER_Y, SHIPWIDTH, data.shipLevel, data.crewLevel, data.cannonLevel, data.shieldLevel, false, owner, true);
    }

    public static Ship createEnemyShip(int level, GameMode owner){
        return new Ship(Shot.ENEMY_Y, SHIPWIDTH, level, true, owner);
    }
}
