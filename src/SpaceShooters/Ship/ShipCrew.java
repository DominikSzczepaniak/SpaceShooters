package SpaceShooters.Ship;

public class ShipCrew extends Upgradable{
    double reloadTime;
    int activeBarrierRenewalValue;
    int passiveBarrierRenewalValue;
    int movementSpeed = 5;

    public ShipCrew(int level){
        this.level = level;
        recalculateValues();
    }

    @Override
    public void recalculateValues(){
        reloadTime = 1 - 0.05 * level;
        activeBarrierRenewalValue = 20 * level;
        passiveBarrierRenewalValue = 5 * level;
        movementSpeed = 5 + level * 2;
    }

    public int getActiveBarrierRenewalValue() {
        return activeBarrierRenewalValue;
    }

    public int getPassiveBarrierRenewalValue() {
        return passiveBarrierRenewalValue;
    }
}
