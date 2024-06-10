package SpaceShooters.Database;

import SpaceShooters.Player;
import SpaceShooters.Ship.PlayerShipData;
import org.json.JSONObject;

public class LoginHandler {
    private DatabaseConnection connection;
    private static LoginHandler instance = null;

    private LoginHandler() throws Exception {
        connection = DatabaseConnection.getInstance();
    }

    public static LoginHandler getInstance() throws Exception {
        if (instance == null) {
            instance = new LoginHandler();
        }
        return instance;
    }

    public Player validateLogin(String username, String password) throws Exception {
        JSONObject database = connection.getDatabase();
        JSONObject players = database.getJSONObject("players");

        if (players.has(username)) {
            JSONObject playerData = players.getJSONObject(username);
            String storedPassword = playerData.getString("password");

            if (storedPassword.equals(password)) {
                return createPlayerFromJSON(playerData);
            }
        }
        return null;
    }

    public Player registerPlayer(String username, String password) throws Exception {
        JSONObject database = connection.getDatabase();
        JSONObject players = database.getJSONObject("players");

        if (!players.has(username)) {
            JSONObject newPlayer = new JSONObject();
            newPlayer.put("password", password);
            newPlayer.put("level", 1);
            newPlayer.put("normalModeLevel", 0);
            newPlayer.put("experience", 0);
            newPlayer.put("money", 0);
            newPlayer.put("shipData", new JSONObject()); // Placeholder for PlayerShipData

            players.put(username, newPlayer);
            connection.saveDatabase();

            return new Player(1, 0, new PlayerShipData(), 0, 0);
        }
        return null;
    }

    public void savePlayerData(Player p) throws Exception {
        JSONObject database = connection.getDatabase();
        JSONObject players = database.getJSONObject("players");
        JSONObject player = players.getJSONObject(p.getUsername());

        player.put("level", p.getLevel());
        player.put("normalModeLevel", p.getNormalModeLevel());
        player.put("experience", p.getExperience());
        player.put("money", p.getMoney());
        player.put("shipData", convertShipDataToJSON(p.getShipData()));

        connection.saveDatabase();
    }

    public boolean playerExists(String name) throws Exception {
        JSONObject database = connection.getDatabase();
        JSONObject players = database.getJSONObject("players");
        return players.has(name);
    }

    private Player createPlayerFromJSON(JSONObject playerData) {
        int level = playerData.getInt("level");
        int normalModeLevel = playerData.getInt("normalModeLevel");
        double experience = playerData.getDouble("experience");
        double money = playerData.getDouble("money");
        PlayerShipData shipData = convertJSONToShipData(playerData.getJSONObject("shipData"));

        return new Player(level, normalModeLevel, shipData, experience, money);
    }

    private JSONObject convertShipDataToJSON(PlayerShipData shipData) {
        JSONObject json = new JSONObject();
        // Convert shipData to JSON
        return json;
    }

    private PlayerShipData convertJSONToShipData(JSONObject json) {
        // Convert JSON to PlayerShipData
        return new PlayerShipData();
    }
}
