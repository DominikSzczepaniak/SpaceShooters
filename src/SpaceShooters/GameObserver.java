package SpaceShooters;

/**
 * Observes game events and delegates them to the associated Game instance.
 */
public class GameObserver {
    private final Game game;

    /**
     * Constructs a GameObserver with the specified Game instance.
     *
     * @param game The Game instance to observe.
     */
    public GameObserver(Game game){
        this.game = game;
    }

    /**
     * Notifies the game that a ship has been shot.
     *
     * @param shot The Shot object representing the shot fired.
     */
    public void shipShot(Shot shot){
        game.shipShot(shot);
    }

    /**
     * Notifies the game that the player ship has been destroyed.
     */
    public void playerShipDestroyed() {
        game.playerShipDestroyed();
    }

    /**
     * Notifies the game to end the game session.
     */
    public void endGame(){
        game.endGame();
    }
}
