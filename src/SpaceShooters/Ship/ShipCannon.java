package SpaceShooters.Ship;

/**
 * Represents the cannon of a ship, encapsulating attributes such as shot damage and shot speed.
 */
public class ShipCannon extends Upgradable {
    int shotDamage;
    double shotSpeed;

    /**
     * Constructs a ShipCannon object with the specified level.
     *
     * @param level The level of the ship cannon.
     */
    public ShipCannon(int level) {
        this.level = level;
        recalculateValues();
    }

    /**
     * Recalculates the shot damage and shot speed based on the current level.
     */
    @Override
    public void recalculateValues() {
        shotDamage = 100 * level;
        shotSpeed = 1 + 0.2 * level;
    }
}
