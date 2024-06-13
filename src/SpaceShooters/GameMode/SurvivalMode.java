package SpaceShooters.GameMode;

import SpaceShooters.GameObserver;
import SpaceShooters.Ship.Ship;
import SpaceShooters.Ship.ShipFactory;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;

public class SurvivalMode extends GameMode{
    private int stage;
    private final double levelBoostPerStage;
    private double enemyLevel;
    public SurvivalMode(int level, int experienceAward, int moneyAward, double levelBoostPerStage, GameObserver observer) {
        super(level, experienceAward, moneyAward, observer);
        this.stage = 1;
        enemyLevel = max(level / 3 - 2, 1);
        List<Ship> cur = new ArrayList<>();
        cur.add(ShipFactory.createEnemyShip((int) Math.floor(enemyLevel), this));
        currentEnemies = cur;
        this.levelBoostPerStage = levelBoostPerStage;
    }

    public void nextStage() {
        stage++;
        enemyLevel += levelBoostPerStage;
        List<Ship> cur = new ArrayList<>();
        for(int i = 0; i<stage + (stage%5 + 1); i++){
            cur.add(ShipFactory.createEnemyShip((int) Math.floor(enemyLevel), this));
        }
        currentEnemies = cur;
    }


}
