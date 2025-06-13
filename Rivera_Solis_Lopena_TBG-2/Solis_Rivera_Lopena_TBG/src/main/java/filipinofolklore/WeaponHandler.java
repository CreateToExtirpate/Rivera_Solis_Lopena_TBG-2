/*
The Weapon Handler class is reponsible for initializing the various preset Weapon objects.
- Each weapon has a set name and pre-established stats (damage range and speed).
- LinkedLists are Weapon Spawn Pools specific to an area.
- weaponSpawn() method spawns a random weapon from area for player to equip.

 */
package filipinofolklore;

import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class WeaponHandler {

    private static final Random randomNum = new Random();
    private static final Scanner scn = new Scanner(System.in);

    private static final LinkedList<Weapon> woodsWeapon = new LinkedList<>(); // for woods weapons
    private static final LinkedList<Weapon> swampWeapon = new LinkedList<>(); // for swamp weapons
    private static final LinkedList<Weapon> villageWeapon = new LinkedList<>(); // for village weapons


    public WeaponHandler() {
        initializeWeapons();
        
    }

    private static void initializeWeapons() {
        // Woods weapon
        Weapon Balisong = new Weapon("Balisong", 15, 30, 10);
        Weapon BoloKnife = new Weapon("Arnis", 10, 20, 15);
        woodsWeapon.add(BoloKnife);
        woodsWeapon.add(Balisong);

        // Swamp weapon
        Weapon SibatSpear = new Weapon("Sibat Spear", 15, 30, 5);
        Weapon Bolo = new Weapon("Bolo Knife", 20, 50, -5);
        swampWeapon.add(SibatSpear);
        swampWeapon.add(Bolo);

        // Village weapon
        Weapon SinawitAxe = new Weapon("Sinawit Axe", 30, 70, -10);
        Weapon Kris = new Weapon("Kris", 40, 50, 10);
        Weapon Kampilan = new Weapon("Kampilan", 60, 80, -20);
        villageWeapon.add(SinawitAxe);
        villageWeapon.add(Kris);
        villageWeapon.add(Kampilan);
    }

    public Weapon weaponSpawn(int area, Weapon current) {
        Weapon chosenWeapon = current;
        if (randomNum.nextDouble() <= 0.25) { // 1 in 4 chances to encounter weapon
            switch (area) {
                case 1 -> {
                    if (!woodsWeapon.isEmpty()) {
                        chosenWeapon = equipWeapon(woodsWeapon, current);
                    }
                }
                case 2 -> {
                    if (!swampWeapon.isEmpty()) {
                        chosenWeapon = equipWeapon(swampWeapon, current);
                    }
                }
                case 3 -> {
                    if (!villageWeapon.isEmpty()) {
                        chosenWeapon = equipWeapon(villageWeapon, current);
                    }
                }
            }
        }
        return chosenWeapon;
    }

    // Equip weapon logic
    private static Weapon equipWeapon(LinkedList<Weapon> weapons, Weapon current) {
        Weapon chosenWeapon = current;

        Weapon found = (Weapon) weapons.get(randomNum.nextInt(weapons.size()));

        System.out.println();
        System.out.println("You found a " + found.getName() + "!");
        System.out.println("The " + found.getName() + " deals " + found.getMin() + "-" + found.getMax() + " damage. " + found.getSpeedMsg());
        System.out.println("Equipped item: " + current.getName());
        System.out.println("The " + current.getName() + " deals " + current.getMin() + "-" + current.getMax() + " damage. " + current.getSpeedMsg());

        boolean equipping = true;
        while (equipping) {
            System.out.print("\nSwitch your weapon? (yes/no): ");
            String choice = scn.nextLine();

            switch (choice.toLowerCase()) {
                case "yes" -> {
                    System.out.println("You equipped the " + found.getName() + " and left your " + current.getName() + ".");
                    chosenWeapon = found;
                    equipping = false;
                }
                case "no" -> {
                    System.out.println("You left the " + found.getName() + ".");
                    equipping = false;
                }
                default ->
                    System.out.println("Invalid input. Please try again.");
            }
        }
        weapons.remove(found);
        return chosenWeapon;
    }

}
