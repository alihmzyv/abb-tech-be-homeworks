package classes;

import java.io.Serial;
import java.util.Set;

public class Dog extends Pet implements NastyThings {

    @Serial
    private static final long serialVersionUID = 3421735145484713552L;

    //constructors
    public Dog(String nickname) {
        super(nickname);
        this.setSpecies(Species.DOG);
    }

    public Dog(String nickname, int age, int trickLevel, Set<String> habits) {
        super(nickname, age, trickLevel, habits);
        this.setSpecies(Species.DOG);
    }

    public Dog() {
        this.setSpecies(Species.DOG);
    }



    //override abstract methods of super class
    @Override
    public void respond() {
        System.out.printf("Hello, owner. I am your %s: %s. I miss you!\n",
                getSpecies().toString().toLowerCase(), getNickname().orElse("XXX"));
    }

    //dogs and cats can foul
    @Override
    public void foul() {
        System.out.printf("I am a %s and I need to cover it up\n", getSpecies().toString().toLowerCase());
    }
}
