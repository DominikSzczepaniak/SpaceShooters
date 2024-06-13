package SpaceShooters.Database;

import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DatabaseConnection {
    private JSONObject database;
    private static DatabaseConnection instance = null;
    private static final String FILE_PATH = "database.json";

    public DatabaseConnection() {
        try {
            String content = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
            database = new JSONObject(content);
        } catch (IOException e) {
            database = new JSONObject();
            database.put("players", new JSONObject());
        }
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public JSONObject getDatabase() {
        return database;
    }

    public void saveDatabase() throws Exception {
        try (FileWriter file = new FileWriter(FILE_PATH)) {
            file.write(database.toString(4)); // Pretty print with an indentation of 4 spaces
            file.flush();
        }
    }
}
