package SpaceShooters.Ship;

public class ShipCannon extends Upgradable{
    int shotDamage;
    int shotSpeed;

    public ShipCannon(int level){
        this.level = level;
        recalculateValues();

    }

    private void recalculateValues(){
        shotDamage = 100 * level;
        shotSpeed = 200 + 5*level;
    }
    @Override
    public void levelUp() {
        level++;
        recalculateValues();
    }
}
