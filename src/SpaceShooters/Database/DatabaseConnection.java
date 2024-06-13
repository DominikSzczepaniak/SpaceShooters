package SpaceShooters.Database;

import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * This class represents a connection to the database stored in a JSON file.
 * It provides methods to read and write data to the file.
 */
public class DatabaseConnection {
    private JSONObject database;
    private static DatabaseConnection instance = null;
    private static final String FILE_PATH = "database.json";

    /**
     * Constructs a new DatabaseConnection and initializes the database.
     * If the database file exists, it reads the content. Otherwise, it creates a new empty database.
     */
    public DatabaseConnection() {
        try {
            String content = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
            database = new JSONObject(content);
        } catch (IOException e) {
            database = new JSONObject();
            database.put("players", new JSONObject());
        }
    }

    /**
     * Returns the singleton instance of the DatabaseConnection.
     * If the instance is not created yet, it creates a new one.
     *
     * @return the singleton instance of DatabaseConnection
     */
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    /**
     * Returns the JSON object representing the database.
     *
     * @return the database as a JSONObject
     */
    public JSONObject getDatabase() {
        return database;
    }

    /**
     * Saves the current state of the database to the JSON file.
     *
     * @throws Exception if an I/O error occurs
     */
    public void saveDatabase() throws Exception {
        try (FileWriter file = new FileWriter(FILE_PATH)) {
            file.write(database.toString(4)); // Pretty print with an indentation of 4 spaces
            file.flush();
        }
    }
}
