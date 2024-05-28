package SpaceShooters.Ship;

public class ShipShield extends Upgradable{
    int hpBonus;
    int passiveBarrierHp;
    int activeBarrierHp;
    int currentPassiveBarrierHp;
    int currentActiveBarrierHp;

    public ShipShield(int level){
        this.level = level;
        recalculateValues();
        currentPassiveBarrierHp = 0;
        currentActiveBarrierHp = activeBarrierHp;
    }

    private void recalculateValues(){
        hpBonus = 10 * level;
        passiveBarrierHp = 20 * level;
        activeBarrierHp = 80 * level;
    }

    @Override
    public void levelUp() {
        level++;
        recalculateValues();
    }
}
