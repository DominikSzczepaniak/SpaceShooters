package SpaceShooters.GameMode;

import SpaceShooters.Game;
import SpaceShooters.GameObserver;

/**
 * A factory class for creating different game modes.
 */
public class GameModeFactory {

    /**
     * Creates a new instance of SurvivalMode.
     *
     * @param level the level of the game mode
     * @param owner the Game instance that owns this game mode
     * @return a new SurvivalMode instance
     */
    public static GameMode createSurvivalMode(int level, Game owner) {
        GameObserver gameObserver = new GameObserver(owner);
        return new SurvivalMode(level, level * 25, level * 25, level % 10, gameObserver);
    }

    /**
     * Creates a new instance of NormalMode.
     *
     * @param level the level of the game mode
     * @param owner the Game instance that owns this game mode
     * @return a new NormalMode instance
     */
    public static GameMode createNormalMode(int level, Game owner) {
        GameObserver gameObserver = new GameObserver(owner);
        return new NormalMode(level, level * 100, level * 100, gameObserver);
    }
}
