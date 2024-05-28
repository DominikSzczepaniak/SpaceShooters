package SpaceShooters.Database;
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    public Connection connection;
    static DatabaseConnection instance = null;
    public DatabaseConnection(Connection con) throws Exception {
        if(instance == null){
            connection = con;
            instance = this;
        }
        else{
            throw new Exception("Cant use two databases at the same time");
        }
    }

    static public DatabaseConnection getInstance() throws Exception {
        if(instance != null) {
            return instance;
        }
        throw new Exception("DatabaseConnection instance does not exist");
    }

}
