package classes;

import java.util.*;

public class Human {

    //fields
    private String name;
    private String surname;
    private Family family;
    private int year;
    private int iq;
    private Map<DayOfWeek, List<String>> schedule;


    static {
        System.out.println("classes.Human class is being loaded..");
    }

    //non-static
    {
        System.out.println("A classes.Human type object is created");
    }



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
        //now pet is Set<classes.Pet>, modified to greet all the pets
        Set<Pet> pets = family.getPet();
        if (pets.isEmpty()) {
            System.out.println("Hello, I have no pet.");
            return;
        }
        List<String> namesOfPets = new ArrayList<>();
        for (Pet pet: pets) {
            namesOfPets.add(pet.getNickname());
        }
        System.out.printf("Hello, %s\n", namesOfPets.toString().replaceAll("\\[\\]", ""));
    }

    public void describePet() {
        //now pet is Set<classes.Pet>, modified to describe all the pets
        Set<Pet> pets = family.getPet();
        if (pets.isEmpty()) {
            System.out.println("Hello, I have no pet.");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("I have a ");

        for (Pet pet: pets) {
            sb.append(String.format("%s, he is %d years old, he is %s;\n",
                    pet.getSpecies(), pet.getAge(), pet.getTrickLevel() > 50 ? "very sly" : "almost not sly"));
        }

        System.out.println(sb);
    }

    public boolean feedPet(boolean itIsTimeForFeeding) {
        //returns true if all the pets were fed, otherwise false
        String feed = "Hm... I will feed %s";
        String doNotFeed = "I think %s is not hungry.";
        int countOnesFed = 0;
        Set<Pet> pets = family.getPet();

        if (itIsTimeForFeeding) {
            countOnesFed = family.getPet().size();
            System.out.println("Hm... I will feed all the pets");
        }
        else {
            for (Pet pet: pets) {
                String nicknameOfPet = pet.getNickname();
                if (pet.getTrickLevel() > new Random().nextInt(101)) {
                    System.out.printf(feed + "\n", nicknameOfPet);
                    countOnesFed++;
                }
                else {
                        System.out.printf(doNotFeed + "\n", nicknameOfPet);
                }
            }
        }

        return countOnesFed == pets.size();
    }

    @Override
    public String toString() {
        /*
        classes.Human{name='Name', surname='Surname', year=1, iq=1, schedule=[[day, task], [day_2, task_2]]}
         */
        return String.format("Human{name=%s, surname=%s, year=%d, iq=%d, schedule=%s}",
                name, surname, year, iq, schedule);
    }


    //for equality check: all the fields are taken into account, except family is not directly compared since then family and human comparisons will be recursive.
    //instead only mother and father name and surnames are compared
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Human human = (Human) o;
        return year == human.year && iq == human.iq && Objects.equals(name, human.name) && Objects.equals(surname, human.surname) && Objects.equals(schedule, human.schedule) &&
                (family == null || (Objects.equals(family.getMother().name, human.family.getMother().name) && Objects.equals(family.getFather().name, human.family.getFather().name)));
    }


    @Override
    public int hashCode() {
        int result = Objects.hash(name, surname, year, iq,
                (family != null ? family.getMother().name: null),
                (family != null ? family.getFather().name: null), schedule);
        return result;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.printf("%s object Garbage Collected: %s\n", this.getClass().getName(), this);
    }
}