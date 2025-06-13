package filipinofolklore;

public class Travel {

    public Travel() {
    }

    //Counters to progress through tiles and areas
    protected static int areaCounter = 0; //area of a player
    protected static int tileCounter = 0; //tile of a player

    //Arrays of Areas
    //Starting area: 1 Tile
    private static final String[] spawn = { 
        "You're walking through the woods on your way home.\nYou have forgotten to say \"Tabi-tabi lang po\" and you are now in grave danger.\nThe only way out is to move forward. "};
    //Woods area: 4 Tiles
    private static final String[] woods = {
        "The shadows of the woods loom above you, something is watching you... ",
        "The chilly air sends a chill down your spine... ",
        "Twigs and leaves crunch under your feet as you walk, something hears you... ",
        "The woods are eerily silent as you pass... "};
    //Swamp area: 6 Tiles
    private static final String[] swamp = {
        "The forest thickens, its attempts at keeping you fail as you cut through the branches.\nThe swamp before you smells terrible, and forgotten horrors lurk beneath the surface.\n",
        "Your feet sink slightly with every step. You march on forward with unease. ",
        "You swear you saw something blink from beneath the murky water, but it's gone before you can react... ",
        "Croaking toads and buzzing insects fall into silence as you move... something bigger is listening. ",
        "Half-submerged wooden idols carved with faces line the water's edge. They stare into you... ",
        "A thin mist clings to the swamp like a veil, hiding figures that vanish when you look directly at them... "};
    //Village area: 8 Tiles
    private static final String[] village = {
        "You step into an abandoned village overrun by silence, the kind that screams louder than noise...  ",
        "The nipa huts stand crooked and rotting, something once disrupted this vilage and left, or is still hiding... ",
        "You try not to look back, but you hear footsteps slightly behind your own, only one beat behind... ",
        "A duyan sways gently nearby and you hear faint laughter. You feel no wind blowing... ",
        "The path narrows between the huts as the hairs on your neck begin to rise. You're being watched... ",
        "Each time you pass a hut, you feel as if something inside shifts. Following, watching, waiting... ",
        "Something moves inside one of the huts as you pass by. You look and see that it's empty... ",
        "You see movement in your peripheral vision, but it's gone before you can turn... "};
    //Mountain Area: BOSSFIGHT
    private static final String[] mountain = {
        "Your beloved home lies just beyond the mountain, your sense of relief vanishes as you hear a sickening sound echo from its peak.\nContinuing on could be your death sentence, but you've already come this far... ",
        "BossFight"};
    //Ocean Area: OPTIONAL BOSSFIGHT
    //With the checkBoss() Function in MainGame.java, this array is no longer needed
    private static final String[] cliffs = {
        "You stare into the murky waters of the ocean, giant glowing eyes stare right back into your soul.\nThe horrendous creature from the depths breaches the water's surface and stands face to face with you.", 
        "Exhausted but determined, you brandish your weapom."};
    
    //get size of area array
    private static int getAreaSize() {
        String[] area = getArea();
        return area.length;
    }

    //get Integer representing area
    public int getAreaCounter() {
        return areaCounter;
    }

    public  int getTileCounter() {
        return tileCounter;
    }

    //get array of area
    private static String[] getArea() {
        switch (areaCounter) {
            case 0 -> {
                return spawn;
            }
            case 1 -> {
                return woods;
            }
            case 2 -> {
                return swamp;
            }
            case 3 -> {
                return village;
            }
            case 4 -> {
                return mountain;
            }
            case 5 -> {    
                return cliffs;
            }
            default -> {
                return null;
            }
        }
    }

    //Get message attached to current tile in Area Array
    public String getAreaMessage() {
        String[] area = getArea();
        return area[tileCounter];
    }

    //Get number of tiles finished over total tiles in area
    public String tileCheck() {
        if (areaCounter == 0 || areaCounter == 4 ||areaCounter == 5 ||areaCounter == 6) {
            return "";
        }
        return "(Tile " + (tileCounter + 1) + "/" + getAreaSize() + ")";
    }

    //Progress forward through tiles, reset counter when in new area
    public void proceed() {
        tileCounter++;
        if (tileCounter > getAreaSize() - 1) {
            areaCounter++;
            tileCounter = 0;
        }
    }

    public void resetCounter() {
        areaCounter = 0;
        tileCounter = 0;
    }
}