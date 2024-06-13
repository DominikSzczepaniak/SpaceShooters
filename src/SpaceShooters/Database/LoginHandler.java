package SpaceShooters.Database;

import SpaceShooters.Player;
import SpaceShooters.Ship.PlayerShipData;
import org.json.JSONObject;

/**
 * This class handles login and registration of players.
 * It uses the DatabaseConnection to access and modify player data.
 */
public class LoginHandler {
    private final DatabaseConnection connection;
    private static LoginHandler instance = null;

    /**
     * Private constructor to prevent instantiation.
     * Initializes the DatabaseConnection instance.
     */
    private LoginHandler() {
        connection = DatabaseConnection.getInstance();
    }

    /**
     * Returns the singleton instance of LoginHandler.
     * If the instance is not created yet, it creates a new one.
     *
     * @return the singleton instance of LoginHandler
     */
    public static LoginHandler getInstance() {
        if (instance == null) {
            instance = new LoginHandler();
        }
        return instance;
    }

    /**
     * Validates the login credentials of a player.
     *
     * @param username the username of the player
     * @param password the password of the player
     * @return a Player object if the credentials are valid, null otherwise
     */
    public Player validateLogin(String username, String password) {
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

    /**
     * Registers a new player.
     *
     * @param username the username of the new player
     * @param password the password of the new player
     * @return a Player object if registration is successful, null otherwise
     * @throws Exception if an I/O error occurs while saving the database
     */
    public Player registerPlayer(String username, String password) throws Exception {
        JSONObject database = connection.getDatabase();
        JSONObject players = database.getJSONObject("players");

        if (!players.has(username)) {
            JSONObject newPlayer = new JSONObject();
            newPlayer.put("username", username);
            newPlayer.put("password", password);
            newPlayer.put("level", 1);
            newPlayer.put("normalModeLevel", 0);
            newPlayer.put("experience", 0);
            newPlayer.put("money", 0);
            newPlayer.put("shipData", new JSONObject()); // Placeholder for PlayerShipData

            players.put(username, newPlayer);
            connection.saveDatabase();

            return new Player(1, 0, new PlayerShipData(), 0, 0, username);
        }
        return null;
    }

    /**
     * Saves the current state of a player's data.
     *
     * @param p the Player object containing the data to save
     * @throws Exception if an I/O error occurs while saving the database
     */
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

    /**
     * Checks if a player with the given username exists.
     *
     * @param name the username to check
     * @return true if the player exists, false otherwise
     */
    public boolean playerExists(String name) {
        JSONObject database = connection.getDatabase();
        JSONObject players = database.getJSONObject("players");
        return players.has(name);
    }

    /**
     * Creates a Player object from JSON data.
     *
     * @param playerData the JSON data of the player
     * @return a Player object created from the JSON data
     */
    private Player createPlayerFromJSON(JSONObject playerData) {
        String username = playerData.getString("username");
        int level = playerData.getInt("level");
        int normalModeLevel = playerData.getInt("normalModeLevel");
        int experience = playerData.getInt("experience");
        double money = playerData.getDouble("money");
        PlayerShipData shipData = convertJSONToShipData(playerData.getJSONObject("shipData"));

        return new Player(level, normalModeLevel, shipData, experience, money, username);
    }

    /**
     * Converts PlayerShipData to JSON.
     *
     * @param shipData the PlayerShipData object to convert
     * @return a JSONObject representing the ship data
     */
    private JSONObject convertShipDataToJSON(PlayerShipData shipData) {
        JSONObject json = new JSONObject();
        json.put("crewLevel", shipData.getCrewLevel());
        json.put("cannonLevel", shipData.getCannonLevel());
        json.put("shieldLevel", shipData.getShieldLevel());
        json.put("shipLevel", shipData.getShipLevel());
        return json;
    }

    /**
     * Converts JSON data to PlayerShipData.
     *
     * @param json the JSON data to convert
     * @return a PlayerShipData object created from the JSON data
     */
    private PlayerShipData convertJSONToShipData(JSONObject json) {
        PlayerShipData shipData = new PlayerShipData();
        shipData.setCrewLevel(json.getInt("crewLevel"));
        shipData.setCannonLevel(json.getInt("cannonLevel"));
        shipData.setShieldLevel(json.getInt("shieldLevel"));
        shipData.setShipLevel(json.getInt("shipLevel"));
        return shipData;
    }
}
