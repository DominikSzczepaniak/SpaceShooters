package SpaceShooters.GameMode;

import SpaceShooters.Game;
import SpaceShooters.Ship.Ship;
import SpaceShooters.Shot;

import java.util.List;

public abstract class GameMode {
    protected int level;
    protected int experienceAward;
    protected int moneyAward;
    protected Game owner;
    List<Ship> currentEnemies;

    public GameMode(int level, int experienceAward, int moneyAward, Game owner) {
        this.level = level;
        this.experienceAward = experienceAward;
        this.moneyAward = moneyAward;
        this.owner = owner;
    }

    public void spawnEnemies(){
        owner.spawnEnemies();
    }
    public abstract void endGame();

    public void ShipShot(Shot shot){
        owner.ShipShot(shot);
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
