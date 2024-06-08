package SpaceShooters.GameMode;

import SpaceShooters.Game;
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

    public void spawnEnemies(){
        observer.spawnEnemies();
    }
    public abstract void endGame();

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

    public void setExperienceAward(int experienceAward) {
        this.experienceAward = experienceAward;
    }

    public int getMoneyAward() {
        return moneyAward;
    }

    public void setMoneyAward(int moneyAward) {
        this.moneyAward = moneyAward;
    }


}
