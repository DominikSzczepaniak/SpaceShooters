package SpaceShooters.Database;

import SpaceShooters.GameMode.GameMode;
import SpaceShooters.Player;

public class LoginHandler {
    DatabaseConnection connection;
    static LoginHandler instance = null;

    public LoginHandler() throws Exception{
        connection = DatabaseConnection.getInstance();
        instance = this;
    }
    public Player validateLogin(String username, String password) throws Exception{
        return null;
    }

    public Player registerPlayer(String username, String password) throws Exception{
        //...
        return validateLogin(username, password);
    }

    void savePlayerData(Player p){

    }

    public static LoginHandler getInstance() throws Exception {
        if(instance != null){
            return instance;
        }
        LoginHandler loginHandler = new LoginHandler();
        LoginHandler.instance = loginHandler;
        return loginHandler;
    }

    public boolean playerExists(String name){
        return true;
    }
}
