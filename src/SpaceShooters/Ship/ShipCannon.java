package SpaceShooters.Ship;

public class ShipCannon extends Upgradable {
    int shotDamage;
    double shotSpeed;

    public ShipCannon(int level) {
        this.level = level;
        recalculateValues();

    }

    @Override
    public void recalculateValues() {
        shotDamage = 100 * level;
        shotSpeed = 1 + 0.2 * level;
    }
}
