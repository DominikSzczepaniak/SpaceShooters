//package SpaceShooters.Ship;
//
//import SpaceShooters.Game;
//import SpaceShooters.GameMode.GameMode;
//import SpaceShooters.GameMode.GameModeFactory;
//import SpaceShooters.Player;
//import SpaceShooters.Shot;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class ShipTest {
//    @Test
//    public void BasicUpgrades(){
//        Ship ship = ShipFactory.createEnemyShip(3);
//        assertEquals(ship.getCannonLevel(), 3);
//        assertEquals(ship.getCrewLevel(), 3);
//        assertEquals(ship.getShieldLevel(), 3);
//        ship.upgradeCannon();
//        assertEquals(ship.getCannonLevel(), 4);
//        assertEquals(ship.getCrewLevel(), 3);
//        assertEquals(ship.getShieldLevel(), 3);
//    }
//
//    @Test
//    public void GettingHit(){
//        Ship ship = ShipFactory.createEnemyShip(10); //Passiveshield - 20 * 10 = 200 //activeShield - 80 * 10 = 800
//        Shot shot = new Shot(199, 0, 0);
//        int current = ship.currentHp;
//        ship.ReceiveDamage(shot);
//        assertEquals(ship.currentHp, current-199);
//        ship.setPassiveBarrierHp(200);
//        ship.ReceiveDamage(shot);
//        assertEquals(ship.getPasiveShieldHp(), 1);
//        ship.toggleBarrierActivation();
//        ship.ReceiveDamage(shot);
//        ship.ReceiveDamage(shot);
//        assertEquals(ship.getActiveShieldHp(), 800-199-199);
//    }
//
//    @Test
//    public void ShotCooldown(){
//        GameMode gameMode = GameModeFactory
//        Ship ship = ShipFactory.createEnemyShip(10);
//        ship.shoot();
//        assertNull(ship.shoot());
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        assertNotNull(ship.shoot());
//    }
//
//    @Test
//    public void creatingPlayerShip(){
//        Ship ship = ShipFactory.createEnemyShip(10);
//        PlayerShipData data = new PlayerShipData(ship);
//        Ship playerShip = ShipFactory.createPlayerShip(data);
//        assertEquals(playerShip.getCrewLevel(), 10);
//        assertEquals(playerShip.getCannonLevel(), 10);
//        assertEquals(playerShip.getShieldLevel(), 10);
//    }
//}