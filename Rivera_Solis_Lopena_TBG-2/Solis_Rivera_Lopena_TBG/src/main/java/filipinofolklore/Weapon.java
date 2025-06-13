package filipinofolklore;

/*This file contains the Weapon class. All stats and getters of a Weapon object can be found here*/
public class Weapon {

    int minAtk; //min dmg
    int maxAtk; //max dmg
    int atkSpeed; //speed increase/decrease
    String name; //Weapon name

    //Weapon Constructor
    public Weapon(String name, int min, int max, int speed) {
        this.name = name;
        minAtk = min;
        maxAtk = max;
        atkSpeed = speed;
    }

    //Get weapon name
    public String getName() {
        return name;
    }
    //Get weapon min dmg
    public int getMin() {
        return minAtk;
    }
    //Get weapon max dmg
    public int getMax() {
        return maxAtk;
    }
    //Get weapon speed points
    public int getSpeed() {
        return atkSpeed;
    }
    //Get message of weapon speed
    public String getSpeedMsg() {
        if (atkSpeed > 0) {
            return "It increases your speed by " + atkSpeed + " points.";
                    }
        return "It decreases your speed by " + Math.abs(atkSpeed) + " points.";
    }
}