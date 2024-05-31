package SpaceShooters;

import SpaceShooters.Ship.Ship;

public class Shot {
    public static final int ENEMY_Y = 800;
    public static final int PLAYER_Y = 200;
    double flySpeed;
    private final int damage;
    boolean owner; // 0 - player 1 - bot
    private final int x;
    private int y;

    public Shot(int damage, int x, int y) {
        this.damage = damage;
        this.x = x;
        this.y = y;
    }

    public void updatePosition(Ship[] shipList, Ship playerShip){
        y += flySpeed;
        if(y == ENEMY_Y && !owner){
            for(var ship : shipList){
                int minShipx = ship.getX() - ship.getWidth();
                int maxShipx = ship.getX() + ship.getWidth();
                if(minShipx <= x && x <= maxShipx){
                    ship.ReceiveDamage(this);
                }
            }
        }
        if(y == PLAYER_Y && owner){
            int minShipx = playerShip.getX() - playerShip.getWidth();
            int maxShipx = playerShip.getX() + playerShip.getWidth();
            if(minShipx <= x && x <= maxShipx){
                playerShip.ReceiveDamage(this);
            }
        }
    }

    public int getDamage() {
        return damage;
    }
}
