package classes;

import java.io.Serial;
import java.util.Set;

public class Fish extends Pet {
    @Serial
    private static final long serialVersionUID = -8675812223352248880L;

    //constructors
    public Fish(String nickname) {
        super(nickname);
        this.setSpecies(Species.FISH);
    }

    public Fish(String nickname, int age, int trickLevel, Set<String> habits) {
        super(nickname, age, trickLevel, habits);
        this.setSpecies(Species.FISH);
    }

    public Fish() {
        this.setSpecies(Species.FISH);
    }



    //override abstract methods of super class
    @Override
    public void respond() {
        System.out.printf("Hello, owner. I am your %s: %s. I miss you!\n",
                getSpecies().toString().toLowerCase(), getNickname().orElse("XXX"));
    }
}
