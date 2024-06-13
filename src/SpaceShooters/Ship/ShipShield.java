package SpaceShooters.Ship;

/**
 * Represents the shield of a ship, encapsulating attributes such as HP bonus and barrier values.
 */
public class ShipShield extends Upgradable {
    int hpBonus;
    int maximumPassiveBarrierHp;
    int maximumActiveBarrierHp;
    int currentPassiveBarrierHp;
    int currentActiveBarrierHp;

    /**
     * Constructs a ShipShield object with the specified level.
     *
     * @param level The level of the ship shield.
     */
    public ShipShield(int level){
        this.level = level;
        recalculateValues();
        currentPassiveBarrierHp = 0;
        currentActiveBarrierHp = maximumActiveBarrierHp;
    }

    /**
     * Recalculates the HP bonus, maximum passive barrier HP, and maximum active barrier HP based on the current level.
     */
    @Override
    public void recalculateValues(){
        hpBonus = 10 * level;
        maximumPassiveBarrierHp = 20 * level;
        maximumActiveBarrierHp = 80 * level;
    }
}
