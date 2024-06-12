package SpaceShooters;

import SpaceShooters.GameMode.GameEndsObserver;
import SpaceShooters.GameMode.GameMode;

import java.util.ArrayList;
import java.util.List;

public class Game {
    GameMode gameMode;
    Player player;
    List<Shot> shotList = new ArrayList<>();
    MainWindow owner;
    GameEndsObserver gameEndsObserver;

    Game(Player p, MainWindow owner){
        this.player = p;
        this.owner = owner;
    }

    public void setupGameType(GameMode gameType){
        this.gameMode = gameType;
    }

    public void endGame(){
        //to znaczy ze byl win
        gameEndsObserver.endGame();
        player.receiveExperience(gameMode.getExperienceAward());
        player.receiveMoney(gameMode.getMoneyAward());
    }

    public void ShipShot(Shot shot){
        shotList.add(shot);
    }


    public Player getPlayer() {
        return player;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public void update() {
    }

    public List<Shot> getShotList() {
        return shotList;
    }

    public void setObserver(GameFrame gameFrame){
        gameEndsObserver = new GameEndsObserver(gameFrame);
    }

    public void playerShipDestroyed() {
        gameEndsObserver.endGame();
    }
}
