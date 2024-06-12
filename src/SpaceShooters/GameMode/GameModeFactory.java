package SpaceShooters.GameMode;

import SpaceShooters.Game;
import SpaceShooters.GameObserver;

public class GameModeFactory {
    public static GameMode createSurvivalMode(int level, Game owner){
        GameObserver gameObserver = new GameObserver(owner);
        return new SurvivalMode(level, level * 25, level * 25, level % 10, (double) level / 4, gameObserver);
    }

    public static GameMode createNormalMode(int level, Game owner){
        GameObserver gameObserver = new GameObserver(owner);
        return new NormalMode(level, level*100, level * 100, gameObserver);
    }
}
