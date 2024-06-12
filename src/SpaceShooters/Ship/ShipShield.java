package SpaceShooters.Ship;

public class ShipShield extends Upgradable{
    int hpBonus;
    int maximumPassiveBarrierHp;
    int maximumActiveBarrierHp;
    int currentPassiveBarrierHp;
    int currentActiveBarrierHp;

    public ShipShield(int level){
        this.level = level;
        recalculateValues();
        currentPassiveBarrierHp = 0;
        currentActiveBarrierHp = maximumActiveBarrierHp;
    }

    @Override
    public void recalculateValues(){
        hpBonus = 10 * level;
        maximumPassiveBarrierHp = 20 * level;
        maximumActiveBarrierHp = 80 * level;
    }

}
