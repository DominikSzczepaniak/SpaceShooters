package SpaceShooters.Database;

import SpaceShooters.GameMode.GameMode;
import SpaceShooters.GameMode.NormalMode;
import SpaceShooters.Player;


public class LevelHandler {
    DatabaseConnection connection;
    static LevelHandler instance = null;

    public LevelHandler(DatabaseConnection con){
        connection = con;
        instance = this;
    }
    public GameMode getNextNormalMode(Player p){
        return null;
    }

    public GameMode getNextSurvivalMode(Player p){
        return null;
    }

    void updatePlayerNormalMode(Player p, int level){

    }

    public static LevelHandler getInstance() throws Exception {
        if(instance != null){
            return instance;
        }
        throw new Exception("LevelHandler instance does not exist");
    }

    public void saveNormalMode(NormalMode game){

    }

    public GameMode getNormalMode(int level){
        return null;
    }

}
