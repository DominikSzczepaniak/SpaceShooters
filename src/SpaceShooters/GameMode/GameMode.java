package SpaceShooters.GameMode;

import SpaceShooters.GameObserver;
import SpaceShooters.Ship.Ship;
import SpaceShooters.Shot;

import java.util.List;

/**
 * An abstract class representing a game mode. It handles the core mechanics of the game mode,
 * including levels, rewards, and interactions with game observers and ships.
 */
public abstract class GameMode {
    protected int level;
    protected int experienceAward;
    protected int moneyAward;
    protected GameObserver observer;
    protected List<Ship> currentEnemies;

    /**
     * Constructs a GameMode with the specified level, experience award, money award, and observer.
     *
     * @param level the current level of the game mode
     * @param experienceAward the amount of experience awarded
     * @param moneyAward the amount of money awarded
     * @param observer the GameObserver to notify of game events
     */
    public GameMode(int level, int experienceAward, int moneyAward, GameObserver observer) {
        this.level = level;
        this.experienceAward = experienceAward;
        this.moneyAward = moneyAward;
        this.observer = observer;
    }

    /**
     * Advances the game to the next stage. This method is abstract and must be implemented
     * by subclasses to define the specific behavior for progressing in the game mode.
     */
    public abstract void nextStage();

    /**
     * Notifies the observer that a ship has been shot.
     *
     * @param shot the Shot that hit the ship
     */
    public void shipShot(Shot shot) {
        observer.shipShot(shot);
    }

    /**
     * Gets the current level of the game mode.
     *
     * @return the current level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Sets the current level of the game mode.
     *
     * @param level the new level to set
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Gets the amount of experience awarded in this game mode.
     *
     * @return the experience award
     */
    public int getExperienceAward() {
        return experienceAward;
    }

    /**
     * Gets the amount of money awarded in this game mode.
     *
     * @return the money award
     */
    public int getMoneyAward() {
        return moneyAward;
    }

    /**
     * Gets the list of current enemy ships.
     *
     * @return the list of current enemy ships
     */
    public List<Ship> getCurrentEnemies() {
        return currentEnemies;
    }

    /**
     * Removes a destroyed ship from the list of current enemies.
     *
     * @param ship the ship to remove
     */
    public void shipDestroyed(Ship ship) {
        currentEnemies.remove(ship);
    }

    /**
     * Notifies the observer that the player's ship has been destroyed.
     */
    public void playerShipDestroyed() {
        observer.playerShipDestroyed();
    }
}
