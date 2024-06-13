package SpaceShooters.GameMode;

import SpaceShooters.GameFrame;

/**
 * This class observes the end of a game and triggers the game end process.
 */
public class GameEndsObserver {
    private GameFrame owner;

    /**
     * Constructs a GameEndsObserver with the specified GameFrame owner.
     *
     * @param owner the GameFrame that owns this observer
     */
    public GameEndsObserver(GameFrame owner) {
        this.owner = owner;
    }

    /**
     * Ends the game by calling the gameEnd method on the owner GameFrame.
     */
    public void endGame() {
        owner.gameEnd();
    }
}
