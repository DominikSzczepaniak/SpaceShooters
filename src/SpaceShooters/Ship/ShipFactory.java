package SpaceShooters.Ship;

import SpaceShooters.GameMode.GameMode;
import SpaceShooters.GameMode.ShipObserver;
import SpaceShooters.Shot;

/**
 * Factory class for creating instances of Ship objects.
 */
public class ShipFactory {
    private static final int SHIPWIDTH = 30;

    /**
     * Creates a player ship using the provided PlayerShipData.
     *
     * @param data The data containing player ship specifications.
     * @return A new instance of PlayerShip.
     */
    public static Ship createPlayerShip(PlayerShipData data){
        return new Ship(Shot.PLAYER_Y, SHIPWIDTH, data.shipLevel, data.crewLevel, data.cannonLevel, data.shieldLevel, false, true);
    }

    /**
     * Creates an enemy ship with the specified level and observer.
     *
     * @param level    The level of the enemy ship.
     * @param observer The ShipObserver to observe ship events.
     * @return A new instance of enemy Ship.
     */
    public static Ship createEnemyShip(int level, ShipObserver observer){
        return new Ship(Shot.ENEMY_Y, SHIPWIDTH, level, true, observer);
    }

    /**
     * Creates an enemy ship with the specified level and game mode.
     *
     * @param level The level of the enemy ship.
     * @param mode  The GameMode in which the enemy ship operates.
     * @return A new instance of enemy Ship.
     */
    public static Ship createEnemyShip(int level, GameMode mode){
        ShipObserver observer = new ShipObserver(mode);
        return new Ship(Shot.ENEMY_Y, SHIPWIDTH, level, true, observer);
    }
}
