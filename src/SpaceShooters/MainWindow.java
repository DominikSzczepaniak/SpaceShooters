package SpaceShooters;

import SpaceShooters.Database.LoginHandler;
import SpaceShooters.GameMode.GameModeFactory;
import SpaceShooters.GameMode.NormalMode;
import SpaceShooters.GameMode.SurvivalMode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow extends JFrame {
    JPanel mainPanel;
    Game game;
    Player player;

    public MainWindow() {
        try {
            run();
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    try {
                        onClose();
                    } catch (Exception ignored) {}
                }
            });
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(1600, 1200);
            setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to initialize database connection: " + e.getMessage());
        }
    }

    public MainWindow(Player player){
        this.player = player;
        try {
            run();
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    try {
                        onClose();
                    } catch (Exception ignored) {}
                }
            });
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(1600, 1200);
            setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to initialize database connection: " + e.getMessage());
        }
    }

    void run() {
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
        statsPanel.add(new JLabel("HP: " + player.getPlayerShip().getBaseHp()));
        statsPanel.add(new JLabel("Experience: " + player.getExperience()));
        statsPanel.add(new JLabel("Money: " + player.getMoney()));
        statsPanel.add(new JLabel("Cannon Level: " + player.getPlayerShip().getCannonLevel()));
        statsPanel.add(new JLabel("Shield Level: " + player.getPlayerShip().getShieldLevel()));
        statsPanel.add(new JLabel("Crew Level: " + player.getPlayerShip().getCrewLevel()));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1));
        JButton normalModeButton = new JButton("Zagraj tryb normalny");
        JButton survivalModeButton = new JButton("Zagraj tryb survival");
        JButton shopButton = new JButton("Otworz sklep");

        normalModeButton.addActionListener(e -> {
            int nextLevel = player.getNormalModeLevel() + 1;
            game = new Game(player, MainWindow.this);
            NormalMode nextLevelMode = (NormalMode) GameModeFactory.createNormalMode(nextLevel, game);
            game.setupGameType(nextLevelMode);
            player.getPlayerShip().setupObserver(nextLevelMode);
            gameStart();
        });

        survivalModeButton.addActionListener(e -> {
            game = new Game(player, MainWindow.this);
            SurvivalMode survivalGame = (SurvivalMode) GameModeFactory.createSurvivalMode(player.getLevel(), game);
            game.setupGameType(survivalGame);
            player.getPlayerShip().setupObserver(survivalGame);
            gameStart();
        });

        shopButton.addActionListener(e -> showShop());

        buttonPanel.add(normalModeButton);
        buttonPanel.add(survivalModeButton);
        buttonPanel.add(shopButton);

        mainPanel.add(statsPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        setContentPane(mainPanel);
        pack();
    }

    void gameStart(){
        game.setObserver(new GameFrame(game));
        this.dispose();
    }

    void showShop() {
        JPanel shopPanel = new JPanel();
        shopPanel.setLayout(new GridLayout(3, 1));
        JLabel currentMoneyLabel = new JLabel("Current money: " + player.getMoney());
        JLabel upgradeCannonLabel = new JLabel("Upgrade Cannon Cost: " + player.getPlayerShip().getCannonUpgradeCost());
        JLabel upgradeShieldLabel = new JLabel("Upgrade Shield Cost: " + player.getPlayerShip().getShieldUpgradeCost());
        JLabel upgradeCrewLabel = new JLabel("Upgrade Crew Cost: " + player.getPlayerShip().getCrewUpgradeCost());
        JButton upgradeCannonButton = new JButton("Upgrade Cannon");
        JButton upgradeShieldButton = new JButton("Upgrade Shield");
        JButton upgradeCrewButton = new JButton("Upgrade Crew");
        JButton backButton = new JButton("Back");

        upgradeCannonButton.addActionListener(e -> {
            player.upgradeCannon();
            showMainMenu();
        });

        upgradeShieldButton.addActionListener(e -> {
            player.upgradeShield();
            showMainMenu();
        });

        upgradeCrewButton.addActionListener(e -> {
            player.upgradeCrew();
            showMainMenu();
        });
        backButton.addActionListener(e -> showMainMenu());
        shopPanel.add(backButton);
        shopPanel.add(currentMoneyLabel);
        shopPanel.add(upgradeCannonLabel);
        shopPanel.add(upgradeShieldLabel);
        shopPanel.add(upgradeCrewLabel);
        shopPanel.add(upgradeCannonButton);
        shopPanel.add(upgradeShieldButton);
        shopPanel.add(upgradeCrewButton);

        mainPanel.removeAll();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(shopPanel, BorderLayout.CENTER);

        setContentPane(mainPanel);
        pack();
    }

    private void onClose() throws Exception {
        if(player != null){
            LoginHandler.getInstance().savePlayerData(player);
        }
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
