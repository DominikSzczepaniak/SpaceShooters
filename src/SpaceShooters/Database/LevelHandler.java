package SpaceShooters.Database;

import SpaceShooters.Game;
import SpaceShooters.GameMode.GameMode;
import SpaceShooters.GameMode.GameModeFactory;
import SpaceShooters.GameMode.NormalMode;
import SpaceShooters.Player;
import org.json.JSONObject;

public class LevelHandler {
    private DatabaseConnection connection;
    private static LevelHandler instance = null;

    private LevelHandler() throws Exception {
        connection = DatabaseConnection.getInstance();
    }

    public static LevelHandler getInstance() throws Exception {
        if (instance == null) {
            instance = new LevelHandler();
        }
        return instance;
    }

    public GameMode getNextNormalMode(Player p, Game owner) throws Exception {
        JSONObject database = connection.getDatabase();
        JSONObject players = database.getJSONObject("players");
        JSONObject player = players.getJSONObject(p.getUsername());

        int currentLevel = player.getInt("normalModeLevel");
        JSONObject normalModes = database.optJSONObject("normalModes");
        if(normalModes != null && normalModes.has(Integer.toString(currentLevel))){

        }
        NormalMode nextLevel = GameModeFactory.createNormalMode(currentLevel + 1, owner);
        return null;
    }

    public GameMode getNextSurvivalMode(Player p) {
        // Implementacja
        return null;
    }

    public void updatePlayerNormalMode(Player p, int level) throws Exception {
        JSONObject database = connection.getDatabase();
        JSONObject players = database.getJSONObject("players");
        JSONObject player = players.getJSONObject(p.getUsername());

        player.put("normalModeLevel", level);
        connection.saveDatabase();
    }

    public void saveNormalMode(NormalMode game) throws Exception {
        // Implementacja
    }

    public GameMode getNormalMode(int level) {
        // Implementacja
        return null;
    }
}
