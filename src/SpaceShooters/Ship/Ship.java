package SpaceShooters.Ship;

import SpaceShooters.Shot;

import java.time.Duration;
import java.time.Instant;

public class Ship {
    private final ShipCannon cannon;
    private final ShipCrew crew;
    private final ShipShield shield;
    private boolean barrierActive = false;
    int baseHp;
    int level;
    int currentHp;
    private int x;
    private final int y;
    private final int width;
    private Instant lastShot = Instant.now().minusMillis(10000); //making sure that last_shot is higher than any possible shot cooldown
    private Instant barrierDestroyed = Instant.now().minusMillis(100000); //when barrier destroyed apply cooldown before we can start repairing
    public final boolean lookingDown;

    public Ship(int y, int width, int level, boolean lookingDown) {
        this.y = y;
        this.width = width;
        this.level = level;
        crew = new ShipCrew(level);
        cannon = new ShipCannon(level);
        shield = new ShipShield(level);
        baseHp = level * 50 + shield.hpBonus;
        currentHp = baseHp;
        this.lookingDown = lookingDown;
    }

    public Ship(int y, int width, int shipLevel, int crewLevel, int cannonLevel, int shieldLevel, boolean lookingDown){
        this.y = y;
        this.width = width;
        this.level = shipLevel;
        crew = new ShipCrew(crewLevel);
        shield = new ShipShield(shieldLevel);
        cannon = new ShipCannon(cannonLevel);
        baseHp = level * 50 + shield.hpBonus;
        currentHp = baseHp;
        this.lookingDown = lookingDown;
    }

    Shot shoot(){
        Instant time_now = Instant.now();
        long cooldown_timer = Duration.between(lastShot, time_now).toMillis();
        if(cooldown_timer >= crew.reloadTime){
            lastShot = time_now;
            return new Shot(cannon.shotDamage, x, y);
        }
        else{
            return null; //might also throw exception, but i dont want to spam logs TODO might change
        }
    }

    void ActivateBarrier(){
        barrierActive = true;
    }

    public void endGame(){

    }

    private int DamageActiveBarrier(int damage){
        if(damage > shield.currentActiveBarrierHp){
            int remaining = damage - shield.currentActiveBarrierHp;
            shield.currentActiveBarrierHp = 0;
            barrierActive = false;
            barrierDestroyed = Instant.now();
            return remaining;
        }
        else{
            shield.currentActiveBarrierHp -=damage ;
            return 0;
        }
    }

    private int DamagePassiveBarrier(int damage){
        if(damage > shield.currentPassiveBarrierHp){
            int remaining = damage - shield.currentPassiveBarrierHp;
            shield.currentPassiveBarrierHp = 0;
            return remaining;
        }
        else{
            shield.currentPassiveBarrierHp -= damage;
            return 0;
        }
    }

    private void DamageHp(int damage){
        if(damage > currentHp){
            endGame();
        }
        else{
            currentHp -= damage;
        }
    }

    public void ReceiveDamage(Shot shot){
        if(barrierActive){
            DamageHp(DamagePassiveBarrier(DamageActiveBarrier(shot.getDamage())));
        }
        else{
            DamageHp(DamagePassiveBarrier(shot.getDamage()));
        }
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

    public int getCrewLevel(){
        return crew.getLevel();
    }

    public int getShieldLevel(){
        return shield.getLevel();
    }

    public int getCannonLevel(){
        return cannon.getLevel();
    }

    public Instant getBarrierDestroyed(){
        return barrierDestroyed;
    }

    public void upgradeCrew(){
        crew.levelUp();
    }

    public void upgradeShield(){
        shield.levelUp();
    }

    public void upgradeCannon(){
        cannon.levelUp();
    }
}
