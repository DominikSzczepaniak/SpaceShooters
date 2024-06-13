package SpaceShooters;

import SpaceShooters.GameMode.GameEndsObserver;
import SpaceShooters.GameMode.GameMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Game class represents the main controller for the game.
 * It manages the game mode, player, shots, and game events.
 */
public class Game {
    private GameMode gameMode;
    private final Player player;
    private final List<Shot> shotList = new ArrayList<>();
    private final MainWindow owner;
    private GameEndsObserver gameEndsObserver;

    /**
     * Constructs a Game instance with a player and owner window.
     *
     * @param p     The Player object representing the player in the game.
     * @param owner The MainWindow object that owns this game instance.
     */
    public Game(Player p, MainWindow owner){
        this.player = p;
        this.owner = owner;
    }

    /**
     * Sets up the game with the specified game mode.
     *
     * @param gameType The GameMode object representing the type of game to be played.
     */
    public void setupGameType(GameMode gameType){
        this.gameMode = gameType;
    }

    /**
     * Ends the game, triggering the game ends observer and awarding experience and money to the player.
     */
    public void endGame(){
        gameEndsObserver.endGame();
        player.receiveExperience(gameMode.getExperienceAward());
        player.receiveMoney(gameMode.getMoneyAward());
    }

    /**
     * Adds a shot fired by a ship to the shot list.
     *
     * @param shot The Shot object representing the shot fired.
     */
    public void shipShot(Shot shot){
        shotList.add(shot);
    }

    /**
     * Retrieves the player associated with this game.
     *
     * @return The Player object representing the player in the game.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Retrieves the current game mode of this game.
     *
     * @return The GameMode object representing the current game mode.
     */
    public GameMode getGameMode() {
        return gameMode;
    }

    /**
     * Retrieves the list of shots fired in the game.
     *
     * @return A List of Shot objects representing shots fired in the game.
     */
    public List<Shot> getShotList() {
        return shotList;
    }

    /**
     * Sets the observer for game events to the specified GameFrame.
     *
     * @param gameFrame The GameFrame object to be set as the observer for game events.
     */
    public void setObserver(GameFrame gameFrame){
        gameEndsObserver = new GameEndsObserver(gameFrame);
    }

    /**
     * Sets the observer for game events to the specified GameEndsObserver.
     *
     * @param gameEndsObserver The GameEndsObserver object to be set as the observer for game events.
     */
    public void setObserver(GameEndsObserver gameEndsObserver){
        this.gameEndsObserver = gameEndsObserver;
    }

    /**
     * Notifies the observer that the player's ship has been destroyed.
     */
    public void playerShipDestroyed() {
        gameEndsObserver.endGame();
    }
}
