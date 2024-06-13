package SpaceShooters.Ship;

/**
 * Represents the crew of a ship, encapsulating attributes such as reload time, barrier renewal values, and movement speed.
 */
public class ShipCrew extends Upgradable {
    double reloadTime;
    int activeBarrierRenewalValue;
    int passiveBarrierRenewalValue;
    int movementSpeed = 5;

    /**
     * Constructs a ShipCrew object with the specified level.
     *
     * @param level The level of the ship crew.
     */
    public ShipCrew(int level){
        this.level = level;
        recalculateValues();
    }

    /**
     * Recalculates the reload time, active barrier renewal value, passive barrier renewal value, and movement speed based on the current level.
     */
    @Override
    public void recalculateValues(){
        reloadTime = 1 - 0.05 * level;
        activeBarrierRenewalValue = 20 * level;
        passiveBarrierRenewalValue = 5 * level;
        movementSpeed = 5 + level * 2;
    }

    /**
     * Retrieves the active barrier renewal value.
     *
     * @return The active barrier renewal value.
     */
    public int getActiveBarrierRenewalValue() {
        return activeBarrierRenewalValue;
    }

    /**
     * Retrieves the passive barrier renewal value.
     *
     * @return The passive barrier renewal value.
     */
    public int getPassiveBarrierRenewalValue() {
        return passiveBarrierRenewalValue;
    }
}
