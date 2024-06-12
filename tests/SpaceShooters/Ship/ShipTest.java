package SpaceShooters.Ship;

import SpaceShooters.Game;
import SpaceShooters.GameMode.GameMode;
import SpaceShooters.GameMode.GameModeFactory;
import SpaceShooters.GameMode.ShipObserver;
import SpaceShooters.Player;
import SpaceShooters.Shot;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class ShipTest {
    ShipObserver shipObserver = new ShipObserver(null);
    GameMode mode = null;
    @Test
    public void basicUpgrades(){
        Ship ship = ShipFactory.createEnemyShip(3, mode);
        assertEquals(ship.getCannonLevel(), 3);
        assertEquals(ship.getCrewLevel(), 3);
        assertEquals(ship.getShieldLevel(), 3);
        ship.upgradeCannon(false);
        assertEquals(ship.getCannonLevel(), 4);
        assertEquals(ship.getCrewLevel(), 3);
        assertEquals(ship.getShieldLevel(), 3);
    }

    @Test
    public void gettingHit(){
        Ship ship = ShipFactory.createEnemyShip(10, mode); //Passiveshield - 20 * 10 = 200 //activeShield - 80 * 10 = 800
        Shot shot = new Shot(199, 0, 0, 100, false);
        int current = ship.currentHp;
        ship.receiveDamage(shot);
        assertEquals(ship.currentHp, current-199);
        ship.setPassiveBarrierHp(200);
        ship.receiveDamage(shot);
        assertEquals(ship.getPasiveShieldHp(), 1);
        ship.toggleBarrierActivation();
        ship.receiveDamage(shot);
        ship.receiveDamage(shot);
        assertEquals(ship.getActiveShieldHp(), 800-199-199);
    }

    @Test
    public void shotCooldown(){
        Ship ship = ShipFactory.createEnemyShip(10, shipObserver);
        ship.shoot();
        Instant lastShot = ship.getLastShot();
        ship.shoot();
        assertEquals(lastShot, ship.getLastShot());
        lastShot = ship.getLastShot();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ship.shoot();
        assertNotEquals(lastShot, ship.getLastShot());

    }

    @Test
    public void creatingPlayerShip(){
        Ship ship = ShipFactory.createEnemyShip(10, mode);
        PlayerShipData data = new PlayerShipData(ship);
        Ship playerShip = ShipFactory.createPlayerShip(data);
        assertEquals(playerShip.getCrewLevel(), 10);
        assertEquals(playerShip.getCannonLevel(), 10);
        assertEquals(playerShip.getShieldLevel(), 10);
    }
}