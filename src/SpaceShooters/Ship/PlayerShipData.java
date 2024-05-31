package SpaceShooters.Ship;

public class PlayerShipData {
    int crewLevel, cannonLevel, shieldLevel, shipLevel;
    public PlayerShipData(Ship ship){
        crewLevel = ship.getCrewLevel();
        cannonLevel = ship.getCannonLevel();
        shieldLevel = ship.getShieldLevel();
        shipLevel = ship.level;
    }
    public static PlayerShipData GeneratePlayerShipData(Ship ship){
        return new PlayerShipData(ship);
    }
}
