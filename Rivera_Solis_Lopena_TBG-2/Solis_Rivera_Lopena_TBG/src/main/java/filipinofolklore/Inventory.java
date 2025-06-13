package filipinofolklore;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class Inventory {

    private final int maxHealth;
    private static int currentHealth;
    private static final Random randomNum = new Random();
    private static final Scanner scn = new Scanner(System.in); // Scanner for input
    private static final HashMap<String, Stack<Integer>> items = new HashMap<>(); // Hashmap for Item Name and Health
                                                                                  // Points

    public Inventory(int maxHealth) {
        initializeItems();
        this.maxHealth = maxHealth;
    }

    // Inserts all items to the HashMap when inventory is initialized
    private void initializeItems() {
        items.put("suman", createStack(20));
        items.put("maruya", createStack(30));
        items.put("bingka", createStack(40));
        items.put("turon", createStack(50));
        items.put("atis", createStack(60));
    }

    // Creates stacks automatically, pushes the passed integer
    private static Stack<Integer> createStack(int hp) {
        Stack<Integer> food = new Stack<>();
        food.push(hp);
        return food;
    }

    // Shows prompts to interact with inventory
    // When finished, returns with player's updated or same health.
    public int showInventory(int playerHealth, HealthBar hpBar, boolean inBattle) {
        currentHealth = playerHealth;

        boolean inSako = true;
        while (inSako) {

            System.out.println("\nItems Inside Sako: ");
            int i = 1;
            for (String item : items.keySet()) {
                System.out.println(i + ". " + capitalize(item) + " " + "(" + items.get(item).size() + ")");
                i++;
            }

            System.out.println("\nWhat will you do with your inventory?");
            System.out.println("|| Check || Eat || Back ||");
            System.out.print(">");
            String choice = scn.next();

            switch (choice.toLowerCase()) {
                case "check" -> {
                    System.out.println("\nWhich item?");
                    System.out.print(">");
                    String item = scn.next();
                    System.out.println();
                    check(readChoice(item));
                }
                case "eat" -> {
                    System.out.println("\nWhich item?");
                    System.out.print(">");
                    String item = scn.next();
                    scn.nextLine();
                    System.out.println();
                    use(readChoice(item), hpBar);

                    if (inBattle) {
                        inSako = false;
                    }
                }
                case "back" ->
                    inSako = false;
                default -> {
                    System.out.println("Invalid input, try again.");
                }
            }
        }
        return currentHealth;
    }

    // Checks if user's choice matches with a key to avoid exceptions
    private String readChoice(String choice) {
        String itemName = null;

        if (choice.equalsIgnoreCase("suman")
                || choice.equalsIgnoreCase("maruya")
                || choice.equalsIgnoreCase("bingka")
                || choice.equalsIgnoreCase("turon")
                || choice.equalsIgnoreCase("atis")) {
            itemName = choice.toLowerCase();
        }
        return itemName;
    }

    // To check how much hp an item will restore, the hp point of an item is peeked
    // and displayed.
    private void check(String itemName) {
        if (itemName == null) {
            System.out.println("Invalid item choice.");
            return;
        }

        Stack<Integer> food = items.get(itemName);
        if (food != null && !food.isEmpty()) {
            System.out.println(capitalize(itemName) + " will restore " + food.peek() + " health.");
        } else {
            System.out.println("No more " + capitalize(itemName) + " left!");
        }
    }

    // To regain health, item hp are popped from stack and added to player's health
    private void use(String itemName, HealthBar hpBar) {
        if (itemName == null) {
            System.out.println("Invalid item choice.");
            return;
        }

        if (currentHealth == maxHealth) {
            System.out.println("Cannot consume. You are at full heath!");
            return;
        }

        Stack<Integer> food = items.get(itemName);

        if (food != null && !food.isEmpty()) {
            System.out.println(capitalize(itemName) + " consumed! You restored " + food.peek() + " health.");
            currentHealth += food.pop();

            if (currentHealth > maxHealth) {
                currentHealth = maxHealth;
            }
            System.out.print("Your health is now at " + currentHealth);
            hpBar.displayHealth(currentHealth);

        } else {
            System.out.println("No more " + capitalize(itemName) + " left!");
        }
    }

    public void spawnLoot(String monsterName) {
        if (randomNum.nextDouble() > 0.2) { // 80% chance to get an item
            int lootSpawn = randomNum.nextInt(5);

            String randomItem = null;
            int itemHp = 0;
            switch (lootSpawn) {
                case 0 -> {
                    randomItem = "suman";
                    itemHp = 20;
                }
                case 1 -> {
                    randomItem = "maruya";
                    itemHp = 30;
                }
                case 2 -> {
                    randomItem = "bingka";
                    itemHp = 40;
                }
                case 3 -> {
                    randomItem = "turon";
                    itemHp = 50;
                }
                case 4 -> {
                    randomItem = "atis";
                    itemHp = 60;
                }
            }

            System.out.println("\nThe " + monsterName + " dropped one " + randomItem + ". ");
            if (items.get(randomItem).size() >= 5) {
                System.out.print("Unfortunately, your sako cannot hold any more " + randomItem + ".");
                return;
            }

            System.out.print(capitalize(randomItem) + " was added to your sako!");
            items.get(randomItem).push(itemHp);
        }
    }

    // To show item names, the first letter of the key name is capitalized.
    private String capitalize(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }
}
