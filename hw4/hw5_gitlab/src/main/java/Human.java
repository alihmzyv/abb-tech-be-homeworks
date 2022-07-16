import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class Human {

    static {
        System.out.println("Human class is being loaded..");
    }

    //non-static
    {
        System.out.println("A Human type object is created");
    }


    private String name;
    private String surname;
    private Family family;
    private int year;
    private int iq;
    private String[][] schedule;


    //constructors
    //constructor #1
    public Human(String name, String surname, int year) {
        this.name = name;
        this.surname = surname;
        this.year = year;
    }

    //constructor #2
    public Human(String name, String surname, int year, int iq, String[][] schedule) {
        this.name = name;
        this.surname = surname;
        this.year = year;
        this.iq = iq;
        this.schedule = schedule;
    }

    //constructor #3
    public Human(String name, String surname, Family family, int year, int iq, String[][] schedule) {
        this.name = name;
        this.surname = surname;
        this.family = family;
        this.year = year;
        this.iq = iq;
        this.schedule = schedule;
    }

    //constructor #4
    public Human() {
    }


    //getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Family getFamily() {
        return family;
    }

    public void setFamily(Family family) {
        this.family = family;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getIq() {
        return iq;
    }

    public void setIq(int iq) {
        this.iq = iq;
    }

    public String[][] getSchedule() {
        return schedule;
    }

    public void setSchedule(String[][] schedule) {
        this.schedule = schedule;
    }



    //methods
    public void greetPet() {
        System.out.printf("Hello, %s", family.getPet().getNickname());
        System.out.println();
    }

    public void describePet() {
        System.out.printf("I have a %s, he is %d years old, he is %s", family.getPet().getSpecies(), family.getPet().getAge(), family.getPet().getTrickLevel() > 50 ? "very sly" : "almost not sly");
        System.out.println();
    }

    public boolean feedPet(boolean itIsTimeForFeeding) {
        String feed = String.format("Hm... I will feed %s", family.getPet().getNickname());
        String notFeed = String.format("I think %s is not hungry.", family.getPet().getNickname());
        boolean wasFed = false;

        if (itIsTimeForFeeding) {
            System.out.println(feed);
            wasFed = true;
        }
        else {
            if (family.getPet().getTrickLevel() > new Random().nextInt(101)) {
                System.out.println(feed);
                wasFed = true;
            }
            else {
                System.out.println(notFeed);
            }
        }

        return wasFed;
    }

    @Override
    public String toString() {
        /*
        Human{name='Name', surname='Surname', year=1, iq=1, schedule=[[day, task], [day_2, task_2]]}
         */
        return String.format("Human{name=%s, surname=%s, year=%d, iq=%d, schedule=%s}",
                name, surname, year, iq, Arrays.toString(schedule));
    }


    //for equality check: all the fields are taken into account, except family is not directly compared since then family and human comparisons will be recursive.
    //instead only mother and father name and surnames are compared
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Human human = (Human) o;
        return year == human.year && iq == human.iq && Objects.equals(name, human.name) && Objects.equals(surname, human.surname) && Objects.equals(family.getMother().getName(), family.getFather().getName()) && Objects.equals(family.getMother().getSurname(), family.getFather().getSurname()) && Arrays.deepEquals(schedule, human.schedule);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, surname, year, iq, family.getMother().getName(), family.getFather().getName());
        result = 31 * result + Arrays.deepHashCode(schedule);
        return result;
    }
}