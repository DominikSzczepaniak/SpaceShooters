package SpaceShooters;

import SpaceShooters.Ship.Ship;

import java.util.Iterator;

public class Shot {
    public static final int ENEMY_Y = 800;
    public static final int PLAYER_Y = 200;
    double flySpeed;
    private final int damage;
    boolean owner; // 0 - player 1 - bot
    private final int x;
    private int y;
    private final int width = 10;

    public Shot(int damage, int x, int y, double flySpeed, boolean owner) {
        this.damage = damage;
        this.x = x;
        this.y = y;
        this.flySpeed = flySpeed;
        this.owner = owner;
    }

    public void updatePosition(Ship[] shipList, Ship playerShip, Iterator<Shot> iterator) {
        y += (int) (flySpeed * (owner ? 1.0 : -1.0));
        if (y >= ENEMY_Y && owner) {
            for (var ship : shipList) {
                int minShipx = ship.getX() - ship.getWidth();
                int maxShipx = ship.getX() + ship.getWidth();
                if (minShipx <= x && x <= maxShipx) {
                    ship.receiveDamage(this);
                    iterator.remove();
                    break;
                }
            }
        }
        if (y <= PLAYER_Y && !owner) {
            int minShipx = playerShip.getX() - playerShip.getWidth();
            int maxShipx = playerShip.getX() + playerShip.getWidth();
            if (minShipx <= x && x <= maxShipx) {
                playerShip.receiveDamage(this);
                iterator.remove();
            }
        }
        if(this.y < playerShip.getY() - flySpeed*2 || this.y > 1000){
            iterator.remove();
        }
    }

    public int getDamage() {
        return damage;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

}
