package filipinofolklore;

public class HealthBar {

    private final int maxHealth;

    public HealthBar(int maxHealth) {
        this.maxHealth = maxHealth / 10;
    }

    public void displayHealth(int health) {
        int currentHealth = health / 10;

        System.out.print(" |");
        for (int i = 0; i < currentHealth; i++) {
            System.out.print("█");
        }
        for (int i = 0; i < maxHealth - currentHealth; i++) {
            System.out.print(" ");
        }
        System.out.println("|");
    }
}
