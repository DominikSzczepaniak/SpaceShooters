package SpaceShooters.Ship;

/**
 * Represents data associated with a player-controlled ship, including crew, cannon, shield, and ship levels.
 * Made for JSON serialization and deserialization.
 */
public class PlayerShipData {
    // Fields to store levels of crew, cannon, shield, and ship
    int crewLevel, cannonLevel, shieldLevel, shipLevel;

    /**
     * Constructs a PlayerShipData object based on the provided Ship object.
     *
     * @param ship The Ship object from which to initialize the data.
     */
    public PlayerShipData(Ship ship){
        crewLevel = ship.getCrewLevel();
        cannonLevel = ship.getCannonLevel();
        shieldLevel = ship.getShieldLevel();
        shipLevel = ship.level;
    }

    /**
     * Constructs a default PlayerShipData object with all levels initialized to 1.
     */
    public PlayerShipData(){
        crewLevel = 1;
        cannonLevel = 1;
        shieldLevel = 1;
        shipLevel = 1;
    }

    /**
     * Retrieves the current cannon level of the ship.
     *
     * @return The current cannon level.
     */
    public int getCannonLevel() {
        return cannonLevel;
    }

    /**
     * Retrieves the current crew level of the ship.
     *
     * @return The current crew level.
     */
    public int getCrewLevel() {
        return crewLevel;
    }

    /**
     * Retrieves the current shield level of the ship.
     *
     * @return The current shield level.
     */
    public int getShieldLevel() {
        return shieldLevel;
    }

    /**
     * Retrieves the current ship level.
     *
     * @return The current ship level.
     */
    public int getShipLevel() {
        return shipLevel;
    }

    /**
     * Sets the crew level of the ship.
     *
     * @param crewLevel The new crew level to set.
     */
    public void setCrewLevel(int crewLevel) {
        this.crewLevel = crewLevel;
    }

    /**
     * Sets the cannon level of the ship.
     *
     * @param cannonLevel The new cannon level to set.
     */
    public void setCannonLevel(int cannonLevel) {
        this.cannonLevel = cannonLevel;
    }

    /**
     * Sets the shield level of the ship.
     *
     * @param shieldLevel The new shield level to set.
     */
    public void setShieldLevel(int shieldLevel) {
        this.shieldLevel = shieldLevel;
    }

    /**
     * Sets the ship level.
     *
     * @param shipLevel The new ship level to set.
     */
    public void setShipLevel(int shipLevel) {
        this.shipLevel = shipLevel;
    }
}
