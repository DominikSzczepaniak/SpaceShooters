package SpaceShooters;

import SpaceShooters.Database.LoginHandler;

import javax.swing.*;
import java.awt.*;

/**
 * Panel for user login and registration.
 */
public class LoginMenu extends JPanel {
    private final LoginHandler loginHandler;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private Player player;

    /**
     * Constructs a LoginMenu panel.
     */
    public LoginMenu() {
        loginHandler = LoginHandler.getInstance();
        setupUI();
    }

    /**
     * Sets up the user interface components.
     */
    private void setupUI() {
        setLayout(new GridLayout(3, 2));

        JLabel userLabel = new JLabel("Username: ");
        JLabel passLabel = new JLabel("Password: ");

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        passwordField.addActionListener(e -> loginButton.doClick());

        loginButton = new JButton("Login/Register");
        loginButton.addActionListener(e -> {
            try {
                player = login(usernameField.getText(), new String(passwordField.getPassword()));
                if (player != null) {
                    // Successfully logged in
                    firePropertyChange("player", null, player);
                } else {
                    if (!loginHandler.playerExists(usernameField.getText())) {
                        player = register(usernameField.getText(), new String(passwordField.getPassword()));
                        if (player != null) {
                            // Registration and login successful
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
        });

        add(userLabel);
        add(usernameField);
        add(passLabel);
        add(passwordField);
        add(new JLabel());
        add(loginButton);
    }

    /**
     * Logs in a player using the provided username and password.
     *
     * @param username The username of the player.
     * @param password The password of the player.
     * @return The Player object if login is successful, otherwise null.
     */
    private Player login(String username, String password) {
        return loginHandler.validateLogin(username, password);
    }

    /**
     * Registers a new player using the provided username and password.
     *
     * @param username The username of the new player.
     * @param password The password of the new player.
     * @return The Player object if registration and login are successful, otherwise null.
     * @throws Exception If registration fails.
     */
    private Player register(String username, String password) throws Exception {
        return loginHandler.registerPlayer(username, password);
    }

    /**
     * Displays an error message dialog.
     *
     * @param message The error message to display.
     */
    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Login Error", JOptionPane.ERROR_MESSAGE);
    }

}
