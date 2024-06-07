package SpaceShooters.GameMode;

import SpaceShooters.Game;
import SpaceShooters.Ship.Ship;
import SpaceShooters.Ship.ShipFactory;

import java.util.ArrayList;
import java.util.List;

public class NormalMode extends GameMode{
    List<List<Ship>> enemies;
    int lastStage;
    int currentStage;
    private Game owner;

    public NormalMode(int level, int experienceAward, int moneyAward, Game owner) {
        super(level, experienceAward, moneyAward, owner);
        currentStage = 1;
        lastStage = Math.floorDiv(level, 7) + 1;
        enemies = new ArrayList<>(lastStage+1);
        for(int i = 1; i<=lastStage; i++){
            if(i % 5 == 0){
                List<Ship> cur = new ArrayList<>();
                cur.add(ShipFactory.createEnemyShip((Math.floorDiv(level, 4) + 1) * (i/5 + 2)));
                enemies.add(i, cur); //boss
            }
            else{
                //every level spawn 2 enemies more
                List<Ship> cur = new ArrayList<>();
                for(int j = 1; j<=i*2; j++){
                    cur.add(ShipFactory. createEnemyShip(Math.floorDiv(level, 4) + 1));
                }
                enemies.add(i, cur);
            }
        }
        this.owner = owner;
    }

    void nextStage(){
        currentStage++;
        currentEnemies = enemies.get(currentStage);
        spawnEnemies();
    }

    @Override
    public void spawnEnemies() {
        //TODO
    }

    @Override
    public void endGame() {

    }
}
