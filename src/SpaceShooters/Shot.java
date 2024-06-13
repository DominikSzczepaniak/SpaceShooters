package SpaceShooters;

import SpaceShooters.Ship.Ship;

import java.util.Iterator;

/**
 * Represents a shot fired in the game, capable of damaging ships.
 */
public class Shot {
    public static final int ENEMY_Y = 800;
    public static final int PLAYER_Y = 200;
    double flySpeed;
    private final int damage;
    boolean owner; // 0 - player 1 - bot
    private final int x;
    private int y;
    private final int width = 10;

    /**
     * Constructs a Shot object with specified attributes.
     *
     * @param damage The damage inflicted by the shot.
     * @param x The initial x-coordinate of the shot.
     * @param y The initial y-coordinate of the shot.
     * @param flySpeed The speed at which the shot travels.
     * @param owner Indicates whether the shot is fired by the player (false) or a bot (true).
     */
    public Shot(int damage, int x, int y, double flySpeed, boolean owner) {
        this.damage = damage;
        this.x = x;
        this.y = y;
        this.flySpeed = flySpeed;
        this.owner = owner;
    }

    /**
     * Updates the position of the shot and checks for collisions with ships.
     *
     * @param shipList An array of ships to check for collisions with enemy shots.
     * @param playerShip The player's ship to check for collisions with enemy ships.
     * @param iterator An iterator to remove the shot from the list if necessary.
     */
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

    /**
     * Retrieves the damage inflicted by the shot.
     *
     * @return The damage inflicted by the shot.
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Retrieves the x-coordinate of the shot.
     *
     * @return The x-coordinate of the shot.
     */
    public int getX() {
        return x;
    }

    /**
     * Retrieves the y-coordinate of the shot.
     *
     * @return The y-coordinate of the shot.
     */
    public int getY() {
        return y;
    }

    /**
     * Retrieves the width of the shot.
     *
     * @return The width of the shot.
     */
    public int getWidth() {
        return width;
    }

}
