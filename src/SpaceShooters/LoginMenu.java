package SpaceShooters;

import SpaceShooters.Database.LoginHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginMenu extends JPanel {
    LoginHandler loginHandler;
    JPanel mainPanel = new JPanel();
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private Player player;

    public LoginMenu() throws Exception{
        loginHandler = LoginHandler.getInstance();
        setupUI();
    }

    private void setupUI() {
        setLayout(new GridLayout(3, 2));
        JLabel userLabel = new JLabel("Username: ");
        JLabel passLabel = new JLabel("Password: ");
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login/Register");

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    player = login(usernameField.getText(), new String(passwordField.getPassword()));
                    if (player != null) {
                        // Zalogowano pomyślnie
                        firePropertyChange("player", null, player);
                    } else {
                        if(!loginHandler.playerExists(usernameField.getText())){
                            register(usernameField.getText(), new String(passwordField.getPassword()));
                            login(usernameField.getText(), new String(passwordField.getPassword()));
                        }
                        else{
                            JOptionPane.showMessageDialog(LoginMenu.this,
                                    "Invalid username or password",
                                    "Login Error",
                                    JOptionPane.ERROR_MESSAGE);
                            usernameField.setText("");
                            passwordField.setText("");
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        add(userLabel);
        add(usernameField);
        add(passLabel);
        add(passwordField);
        add(new JLabel());
        add(loginButton);
    }

    Player login(String username, String password) throws Exception{
        return loginHandler.validateLogin(username, password);
    }

    Player register(String username, String password) throws Exception{
        return loginHandler.registerPlayer(username, password);
    }

    public Player getPlayer() {
        return player;
    }
}
