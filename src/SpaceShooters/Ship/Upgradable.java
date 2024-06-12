package SpaceShooters.Ship;

public abstract class Upgradable {
    int level = 0;
    int cost = 50;

    abstract void recalculateValues();
    void levelUp(boolean natural){
        level++;
        recalculateValues();
        if(!natural){
            cost += 50 + 10*level; //TODO balance it
        }
    }

    public int getLevel() {
        return level;
    }
}
