package filipinofolklore;

public class Player {

    int atkSpeed = 30;
    int atkDmg = 25;
    int health = 100;

    private String name;
    private final WeaponHandler weaponHandler = new WeaponHandler();
    private final Inventory inventory = new Inventory(health);
    private static Weapon equipped = new Weapon("Stick", 5, 20, 20);
    private final HealthBar healthBar = new HealthBar(health);

    public Player() {

    }

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public int getAtkSpeed() {
        return atkSpeed;
    }

    public int attack() {
        return atkDmg;
    }

    public void takeDamage(int damage) {
        health -= damage;
    }

    public void openInventory(boolean inBattle) {
        health = inventory.showInventory(health, healthBar, inBattle);
    }

    // To equip a new weapon, weaponHandler spawns a new weapon for the player
    // The current weapon depends on whether the player took the spawned weapon or
    // not
    public void spawnWeapons(int area) {
        equipped = weaponHandler.weaponSpawn(area, equipped);
    }

    public static Weapon getEquippedWeapon() {
        return equipped;
    }

    public String getWeaponName() {
        return equipped.getName();
    }

    public void lootBody(String monsterName) {
        inventory.spawnLoot(monsterName);
    }

    // Player Health Bar
    public void getPlayerHealthBar() {
        healthBar.displayHealth(health);
    }

}
