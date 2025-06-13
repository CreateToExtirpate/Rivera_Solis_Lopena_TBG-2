package filipinofolklore;

import java.util.Random;

public class MonsterSkill {

    /*
     * This is the damage implementation:
     * Monsters from woods will deal normal damage and
     * Monsters from swamp and village will have different amplified damage
     */

    private static Monster monster;
    private static Player player;
    private static int moon = 1;

    public MonsterSkill() {
    }

    // Swamps
    private static int siyokoyAttackCounter = 0;
    private static int berberokaAttackCounter = 0;
    private static int minokawaAttackCounter = 0;

    public void identifySkill(int id, Monster mon, Player play) {
        monster = mon;
        player = play;

        switch (id) {
            case 11 ->
                sigbinSkill();
            case 12 -> kapreSkill();
            case 21 -> berberokaSkill();
            case 22 -> siyokoySkill();
            case 23 -> santelmoSkill();
            case 31 -> manananggalSkill();
            case 32 -> tikbalangSkill();
            case 33 -> tiyanakSkill();
            case 34 -> bruhaSkill();
            case 41 -> minokawaSkill();
            case 42 -> bakunawaSkill();
        }
    }

    // WOOD MONSTERS
    private static void sigbinSkill() {
        int damage = monster.attack();
        player.takeDamage(damage);
        System.out.println(monster.getName() + " ran towards you and attacks you for " + damage + " damage!");
    }

    private static void kapreSkill() {
        int damage = monster.attack();
        player.takeDamage(damage);
        System.out.println(monster.getName() + " smacks you for " + damage + " damage!");
    }

    // SWAMP MONSTERS
    private static void berberokaSkill() {
        // Berberoka will deal 1.5x the damage on the 3rd succeeding attack and resets
        // the counter.
        int damage;
        berberokaAttackCounter++;

        if (berberokaAttackCounter == 4) {
            damage = (int) (monster.attack() * 1.5);
            System.out.println(monster.getName() + " unleashes a CHARGED attack!");
            berberokaAttackCounter = 0;
        } else {
            damage = monster.attack();
        }
        player.takeDamage(damage);
        System.out.println(monster.getName() + " attacks you for " + damage + " damage!");
    }

    private static void siyokoySkill() {
        // Once Siyokoy reaches his 3rd succeeding attack, Siyokoy will deal 2x the
        // damage from then on.
        int damage;

        if (siyokoyAttackCounter < 3) {
            siyokoyAttackCounter++;
        }

        if (siyokoyAttackCounter == 3) {
            damage = (int) (monster.attack() * 2);
            System.out.println(monster.getName() + " unleashes a CHARGED attack!");
            System.out.println("From now on, " + monster.getName() + " will deal 2x damage!");
        } else {
            damage = monster.attack();
        }
        player.takeDamage(damage);
        System.out.println(monster.getName() + " charges. It attacks you for " + damage + " damage!");
    }

    private static void santelmoSkill() {
        int baseDamage = monster.attack();
        int finalDamage = baseDamage;
        player.takeDamage(finalDamage);
    }

    // VILLAGE MONSTERS
    private static void manananggalSkill() {
        // Manananggal will heal itself for half the damage dealt
        int baseDamage = monster.attack();
        int finalDamage = baseDamage;
        player.takeDamage(finalDamage);

        System.out.println(monster.getName() + "drains life! Damage Dealt: " + finalDamage + " damage.");
        monster.health += finalDamage / 2;
        System.out.println(monster.getName() + " heals for " + (finalDamage / 2) + " HP.");
    }

    private static void tikbalangSkill() {
        int baseDamage = monster.attack();
        int finalDamage = baseDamage;
        player.takeDamage(finalDamage);
    }

    private static void tiyanakSkill() {
        /*
         * There is a 50% chance that the Tiyanak will deal
         * continuous attacks.
         * The first damage is its normal attack damage.
         * The second damage is 1/4 of that, and
         * The third damage is 1/6 of that.
         * Add those damages and it will be the final damage to the player.
         */
        Random rand = new Random();
        int damage1;
        int damage2;
        int damage3;
        if (rand.nextDouble() < 0.50) {
            System.out.println(monster.getName() + " unleashes continuous attacks! ");
            damage1 = monster.attack();
            damage2 = (int) (monster.attack() / 4);
            damage3 = (int) (monster.attack() / 6);
            int damage = damage1 + damage2 + damage3;
            player.takeDamage(damage);
            System.out.println("");
            System.out.println(
                    monster.getName() + " attacks you for " + damage1 + ", " + damage2 + ", and " + damage3 + "!");
            System.out.println("Continuous attacks hurt a lot! This is nothing like ever!");
        } else {
            int damage = monster.attack();
            player.takeDamage(damage);
            System.out.println(monster.getName() + " attacks you for " + damage + " damage!");
        }

    }

    private static void bruhaSkill() {
        int damage = monster.attack();
        player.takeDamage(damage);
        System.out.println(monster.getName() + " attacks you for " + damage + " damage!");
    }

    // Mountain Boss
    private static void minokawaSkill() {
        // Once Minokawa reaches his 5th succeeding attack, Minokawa will deal 2x the
        // damage from then on.
        int damage;
        double multiplier;

        multiplier = switch (minokawaAttackCounter) {
            case 0 ->
                1.0;
            case 1 ->
                1.25;
            case 2 ->
                1.5;
            case 3 ->
                1.75;
            default ->
                2.0;
        };

        switch ((int)(multiplier * 100)) {
            case 0 -> {
                System.out.println(monster.getName() + "'s attack will get higher the longer the fight.");
                System.out.println(monster.getName() + " dealt damage 1.0x!");
            }
            case 125 -> System.out.println(monster.getName() + " dealt damage 1.0x!");
            case 150 -> System.out.println(monster.getName() + " dealt damage 1.5x!");
            case 175 -> System.out.println(monster.getName() + " dealt damage 1.75x!");
            case 200 -> {
                System.out.println(monster.getName() + " unleashes a CHARGED attack!");
                System.out.println("From now on, " + monster.getName() + " will deal 2x damage!");
            }
        }

        damage = (int) (monster.attack() * multiplier);
        player.takeDamage(damage);
        System.out.println(monster.getName() + " charges. It attacks you for " + damage + " damage!");
        minokawaAttackCounter++;
    }

    // Ocean Boss
    private static void bakunawaSkill() {
        if (monster.health < monster.getMaxHealth() / 3 && moon == 1) {
            System.out.println(monster.getName() + "'s  health has fallen below a third.");
            System.out.println(monster.getName() + " The sky darkens as Bakunawa devours the moon.");
            System.out.println("The moon restored " + monster.getName() + "'s health by 50%!");
            monster.health += monster.getMaxHealth() / 2;
            moon = 0;
        }
        int damage = monster.attack();
        player.takeDamage(damage);
        System.out.println(monster.getName() + " attacks you for " + damage + " damage!");
    }
}
