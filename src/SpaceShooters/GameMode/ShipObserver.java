package SpaceShooters.GameMode;

import SpaceShooters.Ship.Ship;
import SpaceShooters.Shot;

/**
 * Observes ship events and notifies the corresponding game mode.
 */
public class ShipObserver {
    private final GameMode gameMode;

    /**
     * Constructs a ShipObserver with the specified GameMode.
     *
     * @param mode the GameMode to notify of ship events
     */
    public ShipObserver(GameMode mode) {
        this.gameMode = mode;
    }

    /**
     * Notifies the game mode that a ship has been shot.
     *
     * @param shot the Shot that hit the ship
     */
    public void shipShot(Shot shot) {
        if (gameMode != null) {
            gameMode.shipShot(shot);
        }
    }

    /**
     * Notifies the game mode that a ship has been destroyed.
     *
     * @param ship the Ship that was destroyed
     */
    public void shipDestroyed(Ship ship) {
        if (gameMode != null) {
            gameMode.shipDestroyed(ship);
        }
    }

    /**
     * Notifies the game mode that the player's ship has been destroyed.
     */
    public void playerShipDestroyed() {
        if (gameMode != null) {
            gameMode.playerShipDestroyed();
        }
    }
}
