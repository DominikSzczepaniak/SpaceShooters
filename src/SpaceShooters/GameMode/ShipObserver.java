package SpaceShooters.GameMode;

import SpaceShooters.Ship.Ship;
import SpaceShooters.Shot;

import java.util.List;

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
}
