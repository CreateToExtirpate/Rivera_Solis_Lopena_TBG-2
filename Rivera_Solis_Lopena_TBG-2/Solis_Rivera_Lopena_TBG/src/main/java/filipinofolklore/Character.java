package filipinofolklore;

import java.util.Random;

public class Character {

    protected String name;
    protected int health;
    protected int minATK;
    protected int maxATK;
    protected int id;
    protected double speed;
    private final HealthBar healthBar;

    public Character(String name, int health, int minATK, int maxATK, double speed, int id) {
        this.name = name;
        this.health = health;
        this.minATK = minATK;
        this.maxATK = maxATK;
        this.speed = speed;
        this.id = id;
        healthBar = new HealthBar(health);
    }

    public void takeDamage(int damage) {
        health -= damage;
        // Prevent negative HP
        if (health < 0) {
            health = 0;
        }
    }

    public boolean isDefeated() {
        return health <= 0;
    }

    public int getAttack() {
        Random rand = new Random();
        return rand.nextInt((maxATK - minATK) + 1) + minATK;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getMinATK() {
        return minATK;
    }

    public int getMaxATK() {
        return maxATK;
    }

    public double getSpeed() {
        return speed;
    }

    public void getHealthBar() {
        healthBar.displayHealth(health);
    }
}
