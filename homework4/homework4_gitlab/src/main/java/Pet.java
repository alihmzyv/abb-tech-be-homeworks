import java.beans.FeatureDescriptor;
import java.util.Arrays;

public class Pet {
    String species;
    String nickname;
    int age;
    int trickLevel;
    String[] habits;


    //constructors
    //constructor #1
    public Pet(String species, String nickname) {
        this.species = species;
        this.nickname = nickname;
    }

    //contructor #2
    public Pet(String species, String nickname, int age, int trickLevel, String[] habits) {
        this.species = species;
        this.nickname = nickname;
        this.age = age;
        this.trickLevel = trickLevel;
        this.habits = habits;
    }

    //constructor #3
    public Pet() {
    }


    //methods
    public void eat() {
        System.out.println("I am eating");
    }

    public void respond() {
        System.out.printf("Hello, owner. I am %s. I miss you!", nickname);
        System.out.println();
    }

    public void foul() {
        System.out.println("I need to cover it up");
    }

    @Override
    public String toString() {
        return String.format("%s{nickname=%s, age=%d, tricklevel=%d, habits=%s}", species, nickname, age, trickLevel, Arrays.toString(habits));
    }


}

