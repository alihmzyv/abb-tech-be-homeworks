import java.util.Arrays;
import java.util.Objects;


public abstract class Pet {


    static {
        System.out.println("Pet class is being loaded..");
    }


    {
        species = Species.UNKNOWN;
        System.out.println("A Pet type object is created");
        System.out.println("Species set to UNKNOWN");
    }


    private Species species;
    private String nickname;
    private int age;
    private int trickLevel;
    private String[] habits;


    //constructors
    //constructor #1
    public Pet(String nickname) {
        this.nickname = nickname;
    }

    //contructor #2
    public Pet(String nickname, int age, int trickLevel, String[] habits) {
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

    public abstract void respond();

    public void foul() {
        System.out.println("I need to cover it up");
    }

    @Override
    public String toString() {
        return String.format("%s{nickname=%s: Can fly: %s, Has %d legs, Has fur: %s; age=%d, tricklevel=%d, habits=%s}",
                species, nickname, species.canFly, species.numberOfLegs, species.hasFur, age, trickLevel, Arrays.toString(habits));
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
        System.out.printf("%s object Garbage Collected: %s\n", this.getClass().getName(), this);
    }
}


