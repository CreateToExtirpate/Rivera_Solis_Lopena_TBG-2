package filipinofolklore;

import java.util.Scanner;

public class BattleEncounter {

    private final Player player;
    private final Monster monster;
    private final Travel travel;
    private final Scanner sc = new Scanner(System.in);
    public int parryCounter = 0;

    public BattleEncounter(Player player, Monster monster, Travel travel) {
        this.player = player;
        this.monster = monster;
        this.travel = travel;
    }

    public boolean startBattle() {
        // player's turn if player's atkSpeed is greater than or equal to monster's
        boolean playerFirst = player.getAtkSpeed() >= monster.getAtkSpeed();
        while (player.isAlive() && monster.isAlive()) {
            if (playerFirst) {
                // Player's turn
                System.out.println("What would you like to do right now?\n");

                // PRINT HERE: HEALTH BARS
                System.out.print(player.getName() + "'s HP:");
                player.getPlayerHealthBar();

                System.out.print(monster.getName() + "'s HP:");
                monster.getHealthBar();

                boolean validInput = false;
                while (!validInput) {
                    System.out.println("\n// Attack // Sako (Check Inventory) // Parry //");
                    System.out.print("Your Action: ");
                    String input = sc.nextLine();
                    switch (input.toLowerCase()) {
                        case "attack" -> {
                            Weapon weapon = Player.getEquippedWeapon();
                            int weaponDamage = 0;
                            if (weapon != null) {
                                weaponDamage = weapon.getMin()
                                        + (int) (Math.random() * (weapon.getMax() - weapon.getMin() + 1));
                            }
                            int damage = player.attack() + weaponDamage;
                            monster.takeDamage(damage);
                            System.out.println("You attack " + monster.getName() + " for " + damage
                                    + " damage! (Weapon: " + player.getWeaponName() + " dealt " + weaponDamage + ")\n");

                            parryCounter++;

                            if (!monster.isAlive()) {
                                // Monster defeated, skip monster turn
                            } else {
                                monsterTurn();
                            }
                            validInput = true;
                        }
                        case "sako" -> {
                            player.openInventory(true);
                            validInput = true;
                        }
                        case "parry" -> {
                            if (parryCounter >= 3) {
                                System.out
                                        .println("You parried " + monster.getName() + "! The monster skips its turn.");
                                parryCounter = 0;
                                // Attack the monster after parrying
                                Weapon weapon = Player.getEquippedWeapon();
                                int weaponDamage = 0;
                                if (weapon != null) {
                                    weaponDamage = weapon.getMin()
                                            + (int) (Math.random() * (weapon.getMax() - weapon.getMin() + 1));
                                }
                                int damage = player.attack() + weaponDamage;
                                monster.takeDamage(damage);
                                System.out.println("You attack " + monster.getName() + " for " + damage
                                        + " damage! (Weapon: " + player.getWeaponName() + " dealt " + weaponDamage
                                        + ")\n");
                            } else {
                                System.out.println("Parry is not ready yet! (" + (3 - parryCounter) + " turns left)");
                                parryCounter++;
                                monsterTurn();
                            }
                            validInput = true;
                        }
                        default -> {
                            System.out.println("Not a valid input. Please try again.\n");
                            System.out.println(player.getName() + "'s HP:");
                            player.getPlayerHealthBar();

                            System.out.println(monster.getName() + "'s HP:");
                            monster.getHealthBar();
                        }
                    }
                }
            } else {
                monsterTurn();
                if (!player.isAlive()) {
                    break;
                }

                // Player's turn
                System.out.print(player.getName() + "'s HP:");
                player.getPlayerHealthBar();

                System.out.print(monster.getName() + "'s HP:");
                monster.getHealthBar();

                boolean validInput = false;
                while (!validInput) {
                    System.out.println("\nYour turn!");
                    System.out.println("// Attack // Sako (Check Inventory) // Parry //");
                    System.out.print("Your Action: ");
                    String input = sc.nextLine();
                    switch (input.toLowerCase()) {
                        case "attack" -> {
                            Weapon weapon = Player.getEquippedWeapon();
                            int weaponDamage = 0;
                            if (weapon != null) {
                                weaponDamage = weapon.getMin()
                                        + (int) (Math.random() * (weapon.getMax() - weapon.getMin() + 1));
                            }
                            int damage = player.attack() + weaponDamage;
                            monster.takeDamage(damage);
                            System.out.println("You attack " + monster.getName() + " for " + damage
                                    + " damage! (Weapon: " + player.getWeaponName() + " dealt " + weaponDamage + ")\n");
                            parryCounter++;

                            if (!monster.isAlive()) {
                                // Monster defeated, skip monster turn
                            } else {
                                monsterTurn();
                            }
                            validInput = true;
                        }
                        case "sako" -> {
                            player.openInventory(true);
                            validInput = true;
                        }
                        case "parry" -> {
                            if (parryCounter >= 3) {
                                System.out
                                        .println("You parried " + monster.getName() + "! The monster skips its turn.");
                                parryCounter = 0;
                                // Attack the monster after parrying
                                Weapon weapon = Player.getEquippedWeapon();
                                int weaponDamage = 0;
                                if (weapon != null) {
                                    weaponDamage = weapon.getMin()
                                            + (int) (Math.random() * (weapon.getMax() - weapon.getMin() + 1));
                                }
                                int damage = player.attack() + weaponDamage;
                                monster.takeDamage(damage);
                                System.out.println("You attack " + monster.getName() + " for " + damage
                                        + " damage! (Weapon: " + player.getWeaponName() + " dealt " + weaponDamage
                                        + ")\n");
                            } else {
                                System.out.println("Parry is not ready yet! (" + (3 - parryCounter) + " turns left)");
                                parryCounter++;
                                monsterTurn();
                            }
                            validInput = true;
                        }
                        default -> {
                            System.out.println("Not a valid input. Please try again.\n");
                            System.out.print(player.getName() + "'s HP:");
                            player.getPlayerHealthBar();

                            System.out.print(monster.getName() + "'s HP:");
                            monster.getHealthBar();
                        }
                    }
                }
            }
        }

        if (player.isAlive()) {
            System.out.println("You defeated " + monster.getName() + "!");
            player.lootBody(monster.getName());
            return true;
        } else {
            System.out.println("You were defeated by " + monster.getName() + "...");
            gameOver();
            return false;
        }
    }

    public void monsterTurn() {
        monster.getSkill(monster, player);
    }

    public static void gameOver() {
        System.out.println("\nGAME OVER!\n");
        System.out.println("You'll return to the Main Menu.\n");
    }
}
