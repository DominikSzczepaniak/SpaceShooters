package SpaceShooters.Database;

import java.sql.Connection;
import java.sql.DriverManager;

public class PostgresConnection {
    public static Connection Connect(String url, String dbName, String username, String password){
        Connection c;
        try{
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://" + url + "/" + dbName, username, password);
            return c;
        } catch (Exception e){
            e.printStackTrace();
            System.err.println("Wrong url, dbname, username or password");
        }
        return null;
    }
}
