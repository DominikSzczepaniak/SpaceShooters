package SpaceShooters;

import SpaceShooters.Database.LoginHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginMenu extends JPanel {
    private LoginHandler loginHandler;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private Player player;

    public LoginMenu() throws Exception {
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
                        if (!loginHandler.playerExists(usernameField.getText())) {
                            player = register(usernameField.getText(), new String(passwordField.getPassword()));
                            if (player != null) {
                                // Rejestracja i logowanie zakończone pomyślnie
                                firePropertyChange("player", null, player);
                            } else {
                                showErrorMessage("Registration failed. Please try again.");
                            }
                        } else {
                            showErrorMessage("Invalid username or password");
                            usernameField.setText("");
                            passwordField.setText("");
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    showErrorMessage("An error occurred. Please try again.");
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

    private Player login(String username, String password) throws Exception {
        return loginHandler.validateLogin(username, password);
    }

    private Player register(String username, String password) throws Exception {
        return loginHandler.registerPlayer(username, password);
    }

    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Login Error", JOptionPane.ERROR_MESSAGE);
    }

    public Player getPlayer() {
        return player;
    }
}
