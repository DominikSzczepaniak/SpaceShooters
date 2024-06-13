package SpaceShooters.GameMode;

import SpaceShooters.GameObserver;
import SpaceShooters.Ship.Ship;
import SpaceShooters.Shot;

import java.util.List;

public abstract class GameMode {
    protected int level;
    protected int experienceAward;
    protected int moneyAward;
    protected GameObserver observer;
    List<Ship> currentEnemies;

    public GameMode(int level, int experienceAward, int moneyAward, GameObserver observer) {
        this.level = level;
        this.experienceAward = experienceAward;
        this.moneyAward = moneyAward;
        this.observer = observer;
    }

    public abstract void nextStage();

    public void shipShot(Shot shot){
        observer.shipShot(shot);
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExperienceAward() {
        return experienceAward;
    }

    public int getMoneyAward() {
        return moneyAward;
    }

    public List<Ship> getCurrentEnemies() {
        return currentEnemies;
    }

    public void shipDestroyed(Ship ship){
        currentEnemies.remove(ship);
    }

    public void playerShipDestroyed() {
        observer.playerShipDestroyed();
    }
}
