package SpaceShooters.Ship;

public class ShipCrew extends Upgradable{
    double reloadTime;
    final double barrierRenewalTime = 0.2;
    double activeBarrierRenewalValue;
    double passiveBarrierRenewalValue;

    public ShipCrew(int level){
        this.level = level;
        recalculateValues();
    }

    @Override
    public void recalculateValues(){
        reloadTime = 1 - 0.05 * level;
        activeBarrierRenewalValue = 20 * level;
        passiveBarrierRenewalValue = 5 * level;
    }

//    @Override
//    public void levelUp() {
//        level++;
//        recalculateValues();
//    }
}
