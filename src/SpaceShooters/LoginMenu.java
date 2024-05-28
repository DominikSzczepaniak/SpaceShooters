package SpaceShooters;

import SpaceShooters.Database.LoginHandler;

import javax.swing.*;

public class LoginMenu extends JFrame {
    LoginHandler loginHandler;

    public LoginMenu() throws Exception{
        loginHandler = LoginHandler.getInstance();
    }

    void run(){

    }

    Player login(String username, String password) throws Exception{
        return loginHandler.validateLogin(username, password);
    }

    Player register(String username, String password) throws Exception{
        return loginHandler.registerPlayer(username, password);
    }
}
