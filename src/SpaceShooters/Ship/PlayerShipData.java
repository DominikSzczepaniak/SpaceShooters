package SpaceShooters.Ship;

public class PlayerShipData {
    int crewLevel, cannonLevel, shieldLevel, shipLevel;
    public PlayerShipData(Ship ship){
        crewLevel = ship.getCrewLevel();
        cannonLevel = ship.getCannonLevel();
        shieldLevel = ship.getShieldLevel();
        shipLevel = ship.level;
    }

    public PlayerShipData(){
        crewLevel = 1;
        cannonLevel = 1;
        shieldLevel = 1;
        shipLevel = 1;
    }
    public static PlayerShipData GeneratePlayerShipData(Ship ship){
        return new PlayerShipData(ship);
    }

    public int getCannonLevel() {
        return cannonLevel;
    }

    public int getCrewLevel() {
        return crewLevel;
    }

    public int getShieldLevel() {
        return shieldLevel;
    }

    public int getShipLevel() {
        return shipLevel;
    }

    public void setCrewLevel(int crewLevel) {
        this.crewLevel = crewLevel;
    }

    public void setCannonLevel(int cannonLevel) {
        this.cannonLevel = cannonLevel;
    }

    public void setShieldLevel(int shieldLevel) {
        this.shieldLevel = shieldLevel;
    }

    public void setShipLevel(int shipLevel) {
        this.shipLevel = shipLevel;
    }
}
