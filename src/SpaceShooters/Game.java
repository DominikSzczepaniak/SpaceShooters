package SpaceShooters;

import SpaceShooters.GameMode.GameMode;

import java.util.ArrayList;
import java.util.List;

public class Game {
    GameMode gameType;
    Player player;
    List<Shot> shotList = new ArrayList<>();
    GameWindow owner;

    Game(Player p, GameMode mode, GameWindow owner){
        this.player = p;
        this.gameType = mode;
        this.owner = owner;
    }

    void EndGame(){

    }

    public void ShipShot(Shot shot){
        shotList.add(shot);
    }

    public void spawnEnemies(){

    }
}
