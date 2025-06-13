package filipinofolklore;

import java.util.ArrayList;

public class MonsterHandler {

    private static final ArrayList<Monster> woodMonsters = new ArrayList<>();
    private static final ArrayList<Monster> swampMonsters = new ArrayList<>();
    private static final ArrayList<Monster> villageMonsters = new ArrayList<>();

    public MonsterHandler() {
        initializeMonsters();
    }

    private static void initializeMonsters() {
        // WOODS
        Monster sigbin = new Monster("Sigbin", 100, 10, 20, 5, 11);
        Monster kapre = new Monster("Kapre", 100, 20, 20, 5, 12);

        // SWAMP
        Monster berberoka = new Monster("Berberoka", 150, 20, 30, 10, 21);
        Monster siyokoy = new Monster("Siyokoy", 150, 20, 30, 10, 22);
        Monster santelmo = new Monster("Santelmo", 150, 20, 30, 15, 23);

        // VILLAGE
        Monster manananggal = new Monster("Manananggal", 200, 30, 40, 15, 31);
        Monster tikbalang = new Monster("Tikbalang", 200, 30, 40, 15, 32);
        Monster tiyanak = new Monster("Tiyanak", 200, 30, 40, 20, 33);
        Monster bruha = new Monster("Bruha", 200, 30, 40, 20, 34);

        woodMonsters.add(sigbin);
        woodMonsters.add(kapre);

        swampMonsters.add(berberoka);
        swampMonsters.add(siyokoy);
        swampMonsters.add(santelmo);

        villageMonsters.add(manananggal);
        villageMonsters.add(tikbalang);
        villageMonsters.add(tiyanak);
        villageMonsters.add(bruha);
    }

    public ArrayList<Monster> getSpawnPool(int area) {
        return switch (area) {
            case 1 ->
                woodMonsters;
            case 2 ->
                swampMonsters;
            case 3 ->
                villageMonsters;
            default ->
                null;
        };
    }
}
