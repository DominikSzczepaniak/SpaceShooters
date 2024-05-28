package SpaceShooters.Ship;

import SpaceShooters.Shot;

public class Ship {
    private final ShipCannon cannon;
    private final ShipCrew crew;
    private final ShipShield shield;
    int baseHp;
    int level;
    int currentHp;
    private int x;
    private final int y;
    private final int width;

    public Ship(int y, int width, int level) {
        this.y = y;
        this.width = width;
        this.level = level;
        baseHp = level * 50;
        crew = new ShipCrew(level);
        cannon = new ShipCannon(level);
        shield = new ShipShield(level);
        currentHp = baseHp + shield.hpBonus;

    }

    void shoot(){

    }

    void ActivateBarrier(){

    }

    public void ReceiveDamage(Shot shot){
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getX() {
        return x;
    }
}
