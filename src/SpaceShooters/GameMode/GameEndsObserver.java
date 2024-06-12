package SpaceShooters.GameMode;

import SpaceShooters.GameFrame;

public class GameEndsObserver {
    GameFrame owner;
    public GameEndsObserver(GameFrame owner){
        this.owner = owner;
    }
    public void endGame() {
        owner.gameEnd();
    }
}
