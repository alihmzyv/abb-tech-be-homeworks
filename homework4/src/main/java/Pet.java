import java.util.Arrays;

public class Pet{
    //declare instance variables private unless otherwise is required
    private String species;
    private String nickname;
    private int age; //in terms of storage usage efficiency; age, trickLevel variables could have been a smaller numerical type
    private int trickLevel;
    private String[] habits;

    //constructor #1
    public Pet(String species, String nickname) {
        this.species = species;
        this.nickname = nickname;
    }

    //constructor #2
    public Pet(String species, String nickname, int age, int trickLevel, String[] habits) {
        this.species = species;
        this.nickname = nickname;
        this.age = age;
        this.trickLevel = trickLevel;
        /*
        habits is a reference type variable, thus assigning the reference that habits has to this.habits
        is not okay, since when the object passed as an argument to constructor is muted, the instance variable of the object would
        change as well. For Strings, it is okay, since they are immutable. Thus, habits is copied and returned reference
        to a new object is assigned to habits.
         */
        this.habits = Arrays.copyOf(habits, habits.length);
    }

    //constructor #3
    public Pet() {
    }



    //getters and setters
    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
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
        return Arrays.copyOf(habits, habits.length); //again, do not return the reference to instance var
    }

    public void setHabits(String[] habits) {
        this.habits = Arrays.copyOf(habits, habits.length); //again, do not assign the reference to an array object outside the class to instance var
    }




    //methods
    public void eat() {
        System.out.println("I am eating");
    }

    public void respond() {
        System.out.println("Hello, owner. I am "+getNickname()+". I miss you!");
    }

    public void foul() {
        System.out.println("I need to cover it up");
    }



    //override toString() method
    @Override
    public String toString() {
        return getSpecies()+
                "{nickname='"+nickname+ "'," +
                " age="+age+ "," +
                " tricklevel="+trickLevel+ ","+
                " habits="+Arrays.toString(habits)+"}";

    }


}
