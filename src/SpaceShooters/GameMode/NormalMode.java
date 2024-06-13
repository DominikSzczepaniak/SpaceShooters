package SpaceShooters.GameMode;

import SpaceShooters.GameObserver;
import SpaceShooters.Ship.Ship;
import SpaceShooters.Ship.ShipFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the normal game mode. This mode consists of multiple stages with increasing difficulty.
 */
public class NormalMode extends GameMode {
    private List<List<Ship>> enemies;
    private int lastStage;
    private int currentStage;

    /**
     * Constructs a NormalMode with the specified level, experience award, money award, and observer.
     *
     * @param level the current level of the game mode
     * @param experienceAward the amount of experience awarded
     * @param moneyAward the amount of money awarded
     * @param observer the GameObserver to notify of game events
     */
    public NormalMode(int level, int experienceAward, int moneyAward, GameObserver observer) {
        super(level, experienceAward, moneyAward, observer);
        currentStage = 1;
        lastStage = Math.floorDiv(level, 7) + 1;
        enemies = new ArrayList<>(lastStage + 5);

        for (int i = 0; i < lastStage + 5; i++) {
            enemies.add(new ArrayList<>());
        }

        for (int i = 1; i <= lastStage; i++) {
            List<Ship> cur = new ArrayList<>();
            if (i % 5 == 0) {
                cur.add(ShipFactory.createEnemyShip((Math.floorDiv(level, 4) + 1) * (i / 5 + 2), this));
            } else {
                // Every level spawns 2 enemies more
                for (int j = 1; j <= i * 2; j++) {
                    cur.add(ShipFactory.createEnemyShip(Math.floorDiv(level, 4) + 1, this));
                }
            }
            enemies.add(i, cur);
        }

        this.currentEnemies = enemies.get(currentStage);
    }

    /**
     * Advances the game to the next stage. If the current stage is the last stage, the game ends.
     */
    @Override
    public void nextStage() {
        if (currentStage == lastStage) {
            observer.endGame();
        } else {
            currentStage++;
            currentEnemies = enemies.get(currentStage);
        }
    }
}
