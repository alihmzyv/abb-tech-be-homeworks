package Classes;

import java.util.Set;

public class DomesticCat extends Pet implements NastyThings {
    //constructors
    public DomesticCat(String nickname) {
        super(nickname);
        this.setSpecies(Species.DOMESTIC_CAT);
    }

    public DomesticCat(String nickname, int age, int trickLevel, Set<String> habits) {
        super(nickname, age, trickLevel, habits);
        this.setSpecies(Species.DOMESTIC_CAT);
    }

    public DomesticCat() {
        this.setSpecies(Species.DOMESTIC_CAT);
    }



    //override abstract methods of super class
    @Override
    public void respond() {
        System.out.printf("Hello, owner. I am your %s: %s. I miss you!\n", getSpecies().toString().toLowerCase(),
                getNickname().orElse("XXX"));
    }

    //dogs and cats can foul
    @Override
    public void foul() {
        System.out.printf("I am a %s and I need to cover it up\n", getSpecies().toString().toLowerCase());
    }
}
