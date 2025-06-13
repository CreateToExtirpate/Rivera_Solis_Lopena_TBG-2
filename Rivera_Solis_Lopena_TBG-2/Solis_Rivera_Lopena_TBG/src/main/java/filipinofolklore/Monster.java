package filipinofolklore;

import java.util.ArrayList;

public class Monster extends Character {

    private static final MonsterSkill monsterSkill = new MonsterSkill();

    public Monster(String name, int health, int minATK, int maxATK, double speed, int id) {
        super(name, health, minATK, maxATK, speed, id);
        this.name = super.name;
    }

    /*
     * MONSTERS
     * - as area increases, the stats of the monster also increases
     * - will use ARRAYLIST to organize monsters according to their area
     * 
     * TO DO:
     * - change monster stats
     * - add spawn chance
     * - damage implementation
     * - overall implementation (call to main)
     * 
     * Woods: 2 enemies 1 special ability per enemy
     * Swamp: 3 enemies 1 special ability per enemy
     * Village: 4 enemies 1 special ability per enemy
     * 
     * FOR BOSSES:
     * Mountain Boss:
     * Ocean Boss:
     */

    // WOODS
    public static final Monster sigbin = new Monster("Sigbin", 100, 10, 20, 5, 11);
    public static final Monster kapre = new Monster("Kapre", 100, 20, 20, 5, 12);

    // SWAMP

    public static final Monster berberoka = new Monster("Berberoka", 150, 20, 30, 10, 21);
    public static final Monster siyokoy = new Monster("Siyokoy", 150, 20, 30, 10, 22);
    public static final Monster santelmo = new Monster("Santelmo", 150, 20, 30, 15, 23);

    // VILLAGE
    public static final Monster manananggal = new Monster("Manananggal", 200, 30, 40, 15, 31);
    public static final Monster tikbalang = new Monster("Tikbalang", 200, 30, 40, 15, 32);
    public static final Monster tiyanak = new Monster("Tiyanak", 200, 30, 40, 20, 33);
    public static final Monster bruha = new Monster("Bruha", 200, 30, 40, 20, 34);

    public static final Monster minokawa = new Monster("Minokawa", 300, 30, 40, 30, 41);
    public static final Monster bakunawa = new Monster("Bakunawa", 300, 30, 40, 30, 42);
    public static final ArrayList<Monster> woodMonsters = new ArrayList<Monster>() {
        {
            add(sigbin);
            add(kapre);
        }
    };

    public static final ArrayList<Monster> swampMonsters = new ArrayList<Monster>() {
        {
            add(berberoka);
            add(siyokoy);
            add(santelmo);
        }
    };

    public static final ArrayList<Monster> villageMonsters = new ArrayList<Monster>() {
        {
            add(manananggal);
            add(tikbalang);
            add(tiyanak);
            add(bruha);
        }
    };

    public boolean isAlive() {
        return health > 0;
    }

    public int getAtkSpeed() {
        return (int) super.getSpeed();
    }

    public int attack() {
        return getAttack();
    }

    public String getName() {
        return super.getName();
    }

    public void getSkill(Monster monster, Player player) {
        monsterSkill.identifySkill(id, monster, player);
    }

    private int maxHealth = super.getHealth(); 
    
    @Override
    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

}