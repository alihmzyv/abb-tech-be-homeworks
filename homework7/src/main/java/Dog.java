public class Dog extends Pet implements NastyThings {

    //constructors
    public Dog(String nickname) {
        super(nickname);
        this.setSpecies(Species.DOG);
    }

    public Dog(String nickname, int age, int trickLevel, String[] habits) {
        super(nickname, age, trickLevel, habits);
        this.setSpecies(Species.DOG);
    }

    public Dog() {
        this.setSpecies(Species.DOG);
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
