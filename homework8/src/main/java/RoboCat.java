import java.util.Set;

public class RoboCat extends Pet implements NastyThings {
    //constructors
    public RoboCat(String nickname) {
        super(nickname);
        this.setSpecies(Species.ROBO_CAT);
    }

    public RoboCat(String nickname, int age, int trickLevel, Set<String> habits) {
        super(nickname, age, trickLevel, habits);
        this.setSpecies(Species.ROBO_CAT);
    }

    public RoboCat() {
        this.setSpecies(Species.ROBO_CAT);
    }



    //override abstract methods of super class
    @Override
    public void respond() {
        System.out.printf("Hello, owner. I am your %s: %s. I miss you!\n", getSpecies(), getNickname());
    }

    //dogs and cats can foul
    @Override
    public void foul() {
        System.out.printf("I am a %s and I need to cover it up\n", getSpecies());
    }

}
