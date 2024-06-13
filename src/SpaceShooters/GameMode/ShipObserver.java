package SpaceShooters.GameMode;

import SpaceShooters.Ship.Ship;
import SpaceShooters.Shot;

public class ShipObserver {
    private final GameMode gameMode;
    public ShipObserver(GameMode mode){
        this.gameMode = mode;
    }

    public void shipShot(Shot shot){
        if(gameMode != null){
            gameMode.shipShot(shot);
        }
    }

    public void shipDestroyed(Ship ship){
        if(gameMode != null){
            gameMode.shipDestroyed(ship);
        }
    }

    public void playerShipDestroyed() {
        if(gameMode != null){
            gameMode.playerShipDestroyed();
        }
    }
}
