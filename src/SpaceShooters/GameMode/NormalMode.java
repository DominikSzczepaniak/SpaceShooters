package SpaceShooters.GameMode;

import SpaceShooters.GameObserver;
import SpaceShooters.Ship.Ship;
import SpaceShooters.Ship.ShipFactory;

import java.util.ArrayList;
import java.util.List;

public class NormalMode extends GameMode{
    List<List<Ship>> enemies;
    int lastStage;
    int currentStage;

    public NormalMode(int level, int experienceAward, int moneyAward, GameObserver observer) {
        super(level, experienceAward, moneyAward, observer);
        currentStage = 1;
        lastStage = Math.floorDiv(level, 7) + 1;
        enemies = new ArrayList<>(lastStage+5);
        for(int i = 0; i<lastStage+5; i++){
            enemies.add(new ArrayList<>());
        }
        for(int i = 1; i<=lastStage; i++){
            List<Ship> cur = new ArrayList<>();
            if(i % 5 == 0){
                cur.add(ShipFactory.createEnemyShip((Math.floorDiv(level, 4) + 1) * (i/5 + 2), this));
            }
            else{
                //every level spawn 2 enemies more
                for(int j = 1; j<=i*2; j++){
                    cur.add(ShipFactory.createEnemyShip(Math.floorDiv(level, 4) + 1, this));
                }
            }
            enemies.add(i, cur); //boss
        }
        this.currentEnemies = enemies.get(currentStage);
    }

    @Override
    public void nextStage(){
        if(currentStage == lastStage){
            observer.endGame();
        }
        currentStage++;
        currentEnemies = enemies.get(currentStage);
    }

}
