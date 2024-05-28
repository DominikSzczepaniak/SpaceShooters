package SpaceShooters.GameMode;

import SpaceShooters.Database.LevelHandler;
import SpaceShooters.Player;
import SpaceShooters.Ship.Ship;

public class GameModeFactory {
    LevelHandler levelHandler;
    GameMode createSurvivalMode(int level){

        return null;
    }

    GameMode createNormalMode(int level, Ship[] enemies){

        return null;
    }

    GameMode getNormalMode(int level){

        return null;
    }

    GameMode getNextNormalMode(Player p){

        return null;
    }
}
