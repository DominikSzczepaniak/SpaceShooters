package SpaceShooters;

import SpaceShooters.GameMode.SurvivalMode;
import SpaceShooters.Ship.Ship;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.List;

public class GameFrame extends JFrame implements ActionListener, KeyListener {
    public final static int MAXWIDTH = 1200;
    private final Timer timer;
    private final Game game;
    private final Player player;
    private List<Ship> enemies;
    private final JPanel gamePanel;
    private final JLabel hpLabel;
    private final JLabel shieldLabel;

    public GameFrame(Game game) {
        this.game = game;
        this.player = game.getPlayer();
        this.enemies = game.getGameMode().getCurrentEnemies();

        gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawGame(g);
            }
        };

        gamePanel.setFocusable(true);
        gamePanel.setPreferredSize(new Dimension(1200, 900));
        gamePanel.setBackground(Color.BLACK);
        gamePanel.addKeyListener(this);

        hpLabel = new JLabel("HP: " + player.getShip().getCurrentHp());
        shieldLabel = new JLabel("Shield: " + (!player.getShip().getBarrierActive() ? player.getShip().getPasiveShieldHp() : player.getShip().getActiveShieldHp()));

        hpLabel.setForeground(Color.WHITE);
        shieldLabel.setForeground(Color.WHITE);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.BLACK);
        infoPanel.add(hpLabel);
        infoPanel.add(shieldLabel);

        gamePanel.add(infoPanel, BorderLayout.NORTH);

        add(gamePanel);

        setTitle("Space Shooters");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        timer = new Timer(16, this);
        timer.start();
    }

    private void checkEndGame(){
        if(enemies.size() == 0){
            game.getGameMode().nextStage();
            if(game.getGameMode() instanceof SurvivalMode){
                player.receiveExperience(game.getGameMode().getExperienceAward());
                player.receiveMoney(game.getGameMode().getMoneyAward());
            }
            game.shotList.clear();
            try{
                enemies = game.getGameMode().getCurrentEnemies();
            }
            catch (IndexOutOfBoundsException e){
                gameEnd();
            }
        }
    }

    private void drawGame(Graphics g) {
        drawPlayer(g);
        drawEnemies(g);
        drawShots(g);
    }

    private void drawPlayer(Graphics g) {
        Ship playerShip = player.getShip();
        if(player.getPlayerShip().isShielded()){
            g.setColor(Color.BLUE);
        }
        else{
            g.setColor(Color.GREEN);
        }
        g.fillRect(playerShip.getX(), playerShip.getY(), playerShip.getWidth(), 10);
    }

    private void drawEnemies(Graphics g) {
        g.setColor(Color.RED);
        for (Ship enemy : enemies) {
            g.fillRect(enemy.getX(), enemy.getY(), enemy.getWidth(), 10);
        }
    }

    private void drawShots(Graphics g){
        g.setColor(Color.YELLOW);
        for(Shot shot : game.getShotList()){
            g.fillRect(shot.getX(), shot.getY(), shot.getWidth(), 10);
        }
    }

    private void makeRandomMoveEnemy(){
        for(Ship enemy : enemies){
            enemy.move((int)(Math.random() * 2), 1);
        }
    }

    private void tryShootEnemy(){
        for(Ship enemy : enemies){
            enemy.shoot();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.getPlayerShip().calculateShield();
        checkEndGame();
        makeRandomMoveEnemy();
        tryShootEnemy();
        Iterator<Shot> iterator = game.getShotList().iterator();
        while (iterator.hasNext()) {
            Shot shot = iterator.next();
            shot.updatePosition(game.getGameMode().getCurrentEnemies().toArray(new Ship[0]), player.getShip(), iterator);
        }

        hpLabel.setText("HP: " + player.getShip().getCurrentHp());
        shieldLabel.setText("Shield: " + (!player.getShip().getBarrierActive() ? player.getShip().getPasiveShieldHp() : player.getShip().getActiveShieldHp()));

        gamePanel.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A) {
            player.moveLeft();
        }
        if (key == KeyEvent.VK_D) {
            player.moveRight();
        }
        if (key == KeyEvent.VK_SPACE){
            player.shoot();
        }
        if (key == KeyEvent.VK_Q){
            player.toggleShield();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Not used
    }

    public void gameEnd() {
        this.dispose();
        new MainWindow(player);
        timer.stop();
    }
}
