public class Fish extends Pet {
    //constructors
    public Fish(String nickname) {
        super(nickname);
    }

    public Fish(String nickname, int age, int trickLevel, String[] habits) {
        super(nickname, age, trickLevel, habits);
    }

    public Fish() {
    }



    //override abstract methods of super class
    @Override
    public void respond() {
        System.out.printf("Hello, owner. I am your %s: %s. I miss you!\n", getSpecies(), getNickname());
    }
}
