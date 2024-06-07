package SpaceShooters.GameMode;

import SpaceShooters.Database.DatabaseConnection;
import SpaceShooters.Database.LevelHandler;
import SpaceShooters.Game;
import SpaceShooters.Player;
import SpaceShooters.Ship.Ship;

public class GameModeFactory {
    LevelHandler levelHandler;
    public GameMode createSurvivalMode(int level, Game owner){
        return new SurvivalMode(level, level * 25, level * 25, level % 10, (double) level / 4, owner);
    }

    public GameMode createNormalMode(int level, Game owner) throws Exception {
        NormalMode game = new NormalMode(level, level*100, level * 100, owner);
        LevelHandler.getInstance().saveNormalMode(game);
        return game;
    }

    public GameMode getNormalMode(int level) throws Exception {
        return LevelHandler.getInstance().getNormalMode(level);
    }

    public GameMode getNextNormalMode(Player p){

        return null;
    }
}
