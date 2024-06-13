package SpaceShooters;

import SpaceShooters.GameMode.GameEndsObserver;
import SpaceShooters.GameMode.SurvivalMode;
import SpaceShooters.Ship.PlayerShipData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    Player p = new Player(1, 0, new PlayerShipData(), 0, 0, "test");
    @Test
    public void testGettingXpAndMoney(){
        SurvivalMode sv = new SurvivalMode(1, 1, 100, 1, null);
        Game game = new Game(p, null);
        game.setupGameType(sv);
        GameEndsObserver dummy = new GameEndsObserver(null){
            @Override
            public void endGame() {
            }
        };
        game.setObserver(dummy);
        game.endGame();
        assertEquals(p.getExperience(), 1);
        assertEquals(p.getMoney(), 101);
    }
}