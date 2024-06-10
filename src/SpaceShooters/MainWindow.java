package SpaceShooters;

import SpaceShooters.Database.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    JFrame mainWindow;
    JPanel mainPanel;
    Game game;
    Player player;

    public MainWindow() {
        try {
            DatabaseConnection db = DatabaseConnection.getInstance();
            run();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to initialize database connection: " + e.getMessage());
        }
    }

    void run() throws Exception {
        if (player == null) {
            LoginMenu loginMenu = new LoginMenu();
            loginMenu.addPropertyChangeListener("player", evt -> {
                player = (Player) evt.getNewValue();
                try {
                    showMainMenu();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            mainPanel = loginMenu;
            setContentPane(mainPanel);
            pack();
            setVisible(true);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        } else {
            showMainMenu();
        }
    }

    void showMainMenu() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));
        statsPanel.add(new JLabel("Player Stats:"));
        statsPanel.add(new JLabel("Level: " + player.getLevel()));
        statsPanel.add(new JLabel("HP: " + player.getPlayerShip().getBaseHp())); // Adjust according to your ship data structure
        statsPanel.add(new JLabel("Cannon Level: " + player.getPlayerShip().getCannonLevel())); // Adjust according to your ship data structure
        statsPanel.add(new JLabel("Shield Level: " + player.getPlayerShip().getShieldLevel())); // Adjust according to your ship data structure
        statsPanel.add(new JLabel("Crew Level: " + player.getPlayerShip().getCrewLevel())); // Adjust according to your ship data structure

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1));
        JButton normalModeButton = new JButton("Zagraj tryb normalny");
        JButton survivalModeButton = new JButton("Zagraj tryb survival");
        JButton shopButton = new JButton("Otworz sklep");

        normalModeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implementacja trybu normalnego
            }
        });

        survivalModeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implementacja trybu survival
            }
        });

        shopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showShop();
            }
        });

        buttonPanel.add(normalModeButton);
        buttonPanel.add(survivalModeButton);
        buttonPanel.add(shopButton);

        mainPanel.add(statsPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        setContentPane(mainPanel);
        pack();
    }

    void showShop() {
        JPanel shopPanel = new JPanel();
        shopPanel.setLayout(new GridLayout(3, 1));

        JButton upgradeCannonButton = new JButton("Upgrade Cannon");
        JButton upgradeShieldButton = new JButton("Upgrade Shield");
        JButton upgradeCrewButton = new JButton("Upgrade Crew");

        upgradeCannonButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                player.upgradeCannon();
                showMainMenu();
            }
        });

        upgradeShieldButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                player.upgradeShield();
                showMainMenu();
            }
        });

        upgradeCrewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                player.upgradeCrew();
                showMainMenu();
            }
        });

        shopPanel.add(upgradeCannonButton);
        shopPanel.add(upgradeShieldButton);
        shopPanel.add(upgradeCrewButton);

        mainPanel.removeAll();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(shopPanel, BorderLayout.CENTER);

        setContentPane(mainPanel);
        pack();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new MainWindow();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
