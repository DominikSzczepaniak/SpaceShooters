package SpaceShooters;

import SpaceShooters.Database.DatabaseConnection;
import SpaceShooters.Database.PostgresConnection;

import javax.swing.*;

public class GameWindow extends JFrame {
    static String databaseUrl = "localhost:5432";
    static String databaseName = "SpaceShootersDB";
    static String databaseUsername = "SpaceShootersProgram";
    static String databasePassword = "SpaceShootersBase";

    JFrame mainWindow;
    JPanel mainPanel;
    Game game;
    Player player;

    void run() throws Exception{
        if(player == null){
            LoginMenu loginMenu = new LoginMenu();
            loginMenu.setVisible(true);
            mainPanel = loginMenu;
        }
        else{
            int todo;
        }
    }

    public GameWindow(){
        try {
            run();
        }catch (Exception ignored){}
    }

    public static void main(String[] args){
        try{
            DatabaseConnection db = new DatabaseConnection(PostgresConnection.Connect(databaseUrl, databaseName, databaseUsername, databasePassword));
            GameWindow gameWindow = new GameWindow();
        } catch(Exception e){
            System.err.println(e.getMessage());
        }


    }
}
