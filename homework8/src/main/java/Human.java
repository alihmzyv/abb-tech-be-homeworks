import java.util.*;

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
    private Map<DayOfWeek, List<String>> schedule;


    //constructors
    //constructor #1
    public Human(String name, String surname, int year) {
        this.name = name;
        this.surname = surname;
        this.year = year;
    }

    //constructor #2
    public Human(String name, String surname, int year, int iq, Map<DayOfWeek, List<String>> schedule) {
        this.name = name;
        this.surname = surname;
        this.year = year;
        this.iq = iq;
        this.schedule = schedule;
    }

    //constructor #3
    public Human(String name, String surname, Family family, int year, int iq, Map<DayOfWeek, List<String>> schedule) {
        this.name = name;
        this.surname = surname;
        this.family = family;
        family.addChild(this); //since the family is an already created object and his mother and father fields are final, human can be added to family only as child
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

    public Map<DayOfWeek, List<String>> getSchedule() {
        return schedule;
    }

    public void setSchedule(Map<DayOfWeek, List<String>> schedule) {
        this.schedule = schedule;
    }



    //methods
    public void greetPet() {
        //greet all pets
        StringBuilder sb = new StringBuilder("Hello, ");
        Iterator<Pet> petIterator = family.getPet().iterator();

        while (petIterator.hasNext()) {
            sb.append(petIterator.next()).append(",");
        }
        sb.deleteCharAt(sb.length() - 1); //delete last comma

        System.out.printf("Hello, %s\n", sb);
    }

    public void describePet() {
        //describe all pets
        for (Pet pet: family.getPet()) {
            System.out.printf("I have a %s, he is %d years old, he is %s\n",
                            pet.getSpecies(), pet.getAge(), pet.getTrickLevel() > 50 ? "very sly" : "almost not sly");
        }
    }

    public boolean feedPet(boolean itIsTimeForFeeding) {
        //feed all pets
        String feedAll = String.format("Hm... I will feed %s",
                                        family.getPet().toString().replaceAll("\\[\\]", ""));
        String feed = "Hm... I will feed %s"; //%s - nickname
        String doNotFeed = "I think %s is not hungry."; //%s - nickname
        boolean allWereFed = true;

        if (itIsTimeForFeeding) {
            System.out.println(feedAll);
        }
        else {
            for (Pet pet: family.getPet()) {
                if (pet.getTrickLevel() > new Random().nextInt(101)) {
                    System.out.printf(feed+"\n", pet.getNickname());
                }
                else {
                    System.out.printf(doNotFeed+"\n", pet.getNickname());
                    allWereFed = false;
                }
            }
        }

        return allWereFed;
    }

    @Override
    public String toString() {
        /*
        Human{name='Name', surname='Surname', year=1, iq=1, schedule=[[day, task], [day_2, task_2]]}
         */
        return String.format("Human{name=%s, surname=%s, year=%d, iq=%d, schedule=%s}",
                name, surname, year, iq, schedule);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Human human = (Human) o;
        return year == human.year && iq == human.iq && Objects.equals(name, human.name)
                && Objects.equals(surname, human.surname) && Objects.equals(schedule, human.schedule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, family, year, iq, schedule);
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.printf("%s object Garbage Collected: %s\n", this.getClass().getName(), this);
    }
}