package SpaceShooters.GameMode;

import SpaceShooters.GameObserver;
import SpaceShooters.Ship.Ship;
import SpaceShooters.Ship.ShipFactory;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;

/**
 * Represents the survival game mode. This mode consists of continuous stages with increasing difficulty.
 */
public class SurvivalMode extends GameMode {
    private int stage;
    private final double levelBoostPerStage;
    private double enemyLevel;

    /**
     * Constructs a SurvivalMode with the specified level, experience award, money award, level boost per stage, and observer.
     *
     * @param level the initial level of the game mode
     * @param experienceAward the amount of experience awarded
     * @param moneyAward the amount of money awarded
     * @param levelBoostPerStage the amount by which the enemy level increases per stage
     * @param observer the GameObserver to notify of game events
     */
    public SurvivalMode(int level, int experienceAward, int moneyAward, double levelBoostPerStage, GameObserver observer) {
        super(level, experienceAward, moneyAward, observer);
        this.stage = 1;
        this.enemyLevel = max(level / 3 - 2, 1);
        this.levelBoostPerStage = levelBoostPerStage;

        List<Ship> cur = new ArrayList<>();
        cur.add(ShipFactory.createEnemyShip((int) Math.floor(enemyLevel), this));
        this.currentEnemies = cur;
    }

    /**
     * Advances the game to the next stage, increasing the difficulty by boosting the enemy level.
     */
    @Override
    public void nextStage() {
        stage++;
        enemyLevel += levelBoostPerStage;

        List<Ship> cur = new ArrayList<>();
        for (int i = 0; i < stage + (stage % 5 + 1); i++) {
            cur.add(ShipFactory.createEnemyShip((int) Math.floor(enemyLevel), this));
        }
        currentEnemies = cur;
    }
}
