import java.util.Arrays;
import java.util.Objects;


public class Pet {


    static {
        System.out.println("Pet class is being loaded..");
    }


    {
        System.out.println("A Pet type object is created");
    }


    private Species species;
    private String nickname;
    private int age;
    private int trickLevel;
    private String[] habits;


    //constructors
    //constructor #1
    public Pet(Species species, String nickname) {
        this.species = species;
        this.nickname = nickname;
    }

    //contructor #2
    public Pet(Species species, String nickname, int age, int trickLevel, String[] habits) {
        this.species = species;
        this.nickname = nickname;
        this.age = age;
        this.trickLevel = trickLevel;
        this.habits = habits;
    }

    //constructor #3
    public Pet() {
    }


    //getters and setters
    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getTrickLevel() {
        return trickLevel;
    }

    public void setTrickLevel(int trickLevel) {
        this.trickLevel = trickLevel;
    }

    public String[] getHabits() {
        return habits;
    }

    public void setHabits(String[] habits) {
        this.habits = habits;
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

    //for equality check: habits are not taken into account
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return age == pet.age && trickLevel == pet.trickLevel && Objects.equals(species, pet.species) && Objects.equals(nickname, pet.nickname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(species, nickname, age, trickLevel);
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.printf("Garbage Collected: %s\n", this);
    }
}


