package SpaceShooters.GameMode;

import SpaceShooters.Game;
import SpaceShooters.Ship.Ship;
import SpaceShooters.Ship.ShipFactory;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;

public class SurvivalMode extends GameMode{
    private int stage;
    private double additionalEnemiesPerLevel;
    private double levelBoostPerStage;
    private double enemyLevel;
    public SurvivalMode(int level, int experienceAward, int moneyAward, double additionalEnemiesPerLevel, double levelBoostPerStage, Game owner) {
        super(level, experienceAward, moneyAward, owner);
        this.stage = 1;
        enemyLevel = max(level / 3 - 2, 1);
        List<Ship> cur = new ArrayList<>();
        cur.add(ShipFactory.createEnemyShip((int) Math.floor(enemyLevel)));
        currentEnemies = cur;
        this.additionalEnemiesPerLevel = additionalEnemiesPerLevel;
        this.levelBoostPerStage = levelBoostPerStage;
    }

    public void nextStage() {
        stage++;
        enemyLevel += levelBoostPerStage;
        List<Ship> cur = new ArrayList<>();
        for(int i = 0; i<stage + (stage%5 + 1); i++){
            cur.add(ShipFactory.createEnemyShip((int) Math.floor(enemyLevel)));
        }
        currentEnemies = cur;
        spawnEnemies();
    }

    @Override
    public void spawnEnemies() {
        //TODO
    }

    @Override
    public void endGame() {
        // Logic to end the game
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public List<Ship> getCurrentEnemies() {
        return currentEnemies;
    }

    public void setCurrentEnemies(List<Ship> currentEnemies) {
        this.currentEnemies = currentEnemies;
    }

    public double getAdditionalEnemiesPerLevel() {
        return additionalEnemiesPerLevel;
    }

    public void setAdditionalEnemiesPerLevel(double additionalEnemiesPerLevel) {
        this.additionalEnemiesPerLevel = additionalEnemiesPerLevel;
    }

    public double getLevelBoostPerStage() {
        return levelBoostPerStage;
    }

    public void setLevelBoostPerStage(double levelBoostPerStage) {
        this.levelBoostPerStage = levelBoostPerStage;
    }
}
