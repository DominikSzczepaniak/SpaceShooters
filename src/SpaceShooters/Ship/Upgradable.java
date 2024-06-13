package SpaceShooters.Ship;

/**
 * Abstract class representing an upgradable entity, defining attributes and methods common to upgradable components.
 */
public abstract class Upgradable {
    /** The current level of the upgradable entity. */
    int level = 0;
    /** The cost to upgrade the entity to the next level. */
    int cost = 50;

    /**
     * Abstract method to recalculate values specific to each subclass.
     */
    abstract void recalculateValues();

    /**
     * Increases the level of the entity by one and recalculates its values.
     *
     * @param natural Indicates if the level up occurred naturally (true) or due to other factors (false).
     */
    void levelUp(boolean natural){
        level++;
        recalculateValues();
        if(!natural){
            cost += 50 + 10 * level; //TODO balance it
        }
    }

    /**
     * Retrieves the current level of the entity.
     *
     * @return The current level of the entity.
     */
    public int getLevel() {
        return level;
    }
}
