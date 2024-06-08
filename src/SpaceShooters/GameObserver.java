package SpaceShooters;

public class GameObserver {
    private final Game game;
    public GameObserver(Game game){
        this.game = game;
    }

    public void shipShot(Shot shot){
        game.ShipShot(shot);
    }

    public void spawnEnemies(){
        game.spawnEnemies();
    }
}
