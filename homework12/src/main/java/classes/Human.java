package classes;

import java.io.Serial;
import java.io.Serializable;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Consumer;

public class Human implements Serializable {

    @Serial
    private static final long serialVersionUID = -2613003581474728026L;
    //fields
    private String name;
    private String surname;
    private Family family;
    private long birthDate;
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
    public Human(String name, String surname, String birthDate) {
        this.name = name;
        this.surname = surname;
        this.birthDate = convertToEpochMilli(birthDate);
    }

    //constructor #2
    public Human(String name, String surname, String birthDate, int iq) {
        this.name = name;
        this.surname = surname;
        this.birthDate = convertToEpochMilli(birthDate);
        this.iq = iq;
    }

    //constructor #3
    public Human(String name, String surname, String birthDate, int iq, Map<DayOfWeek, List<String>> schedule) {
        this.name = name;
        this.surname = surname;
        this.birthDate = convertToEpochMilli(birthDate);
        this.iq = iq;
        this.schedule = schedule;
    }

    //constructor #4
    public Human(String name, String surname, Family family, String birthDate, int iq,
                 Map<DayOfWeek, List<String>> schedule) {
        this.name = name;
        this.surname = surname;
        this.family = family;
        family.addChild(this); //since the family is an already created object and his mother and father fields are final, human can be added to family only as child
        this.birthDate = convertToEpochMilli(birthDate);
        this.iq = iq;
        this.schedule = schedule;
    }

    //constructor #5
    public Human() {
    }


    //getters and setters
    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public Optional<String> getSurname() {
        return Optional.ofNullable(surname);
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Optional<Family> getFamily() {
        return Optional.ofNullable(family);
    }

    public void setFamily(Family family) {
        this.family = family;
    }

    public LocalDate getBirthDate() {
        return convertToLocalDate(birthDate);
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = convertToEpochMilli(birthDate);
    }

    public int getIq() {
        return iq;
    }

    public void setIq(int iq) {
        this.iq = iq;
    }

    public Optional<Map<DayOfWeek, List<String>>> getSchedule() {
        return Optional.ofNullable(schedule);
    }

    public void setSchedule(Map<DayOfWeek, List<String>> schedule) {
        this.schedule = schedule;
    }




    //methods
    public String describeAge() {
        Period period = Period.between(this.getBirthDate(), LocalDate.now());
        return String.format("Years: %d, Months: %d, Days: %d",
                period.getYears(), period.getMonths(), period.getDays());
    }

    public void greetPet() {
        //now pet is Set<classes.Pet>, modified to greet all the pets
        Optional<Set<Pet>> pets = family.getPet();
        //action if pets isPresent
        Consumer<Set<Pet>> action = petsSet -> {
            System.out.print("Hello");
            petsSet.forEach(pet -> System.out.printf(", %s\n", pet.getNickname().orElse("XXX")));
        };
        //action if pets isEmpty
        Runnable emptyAction = () -> System.out.println("Hello, I have no pet.");
        pets.ifPresentOrElse(action, emptyAction);
    }

    public void describePet() {
        //now pet is Set<classes.Pet>, modified to describe all the pets
        Optional<Set<Pet>> pets = family.getPet();
        //action if pets isPresent
        Consumer<Set<Pet>> action = petsSet -> {
            System.out.print("I have a ");
            petsSet.forEach(pet -> System.out.printf("%s, he is %d years old, he is %s;\n",
                    pet.getSpecies(),
                    pet.getAge(),
                    pet.getTrickLevel() > 50 ? "very sly" : "almost not sly"));
        };
        //action if pets isEmpty
        Runnable emptyAction = () -> System.out.println("Hello, I have no pet.");
        pets.ifPresentOrElse(action, emptyAction);
    }

    public boolean feedPet(boolean itIsTimeForFeeding) {
        //returns true if there are pets of family and all the pets were fed, otherwise false
        String feed = "Hm... I will feed %s\n";
        String doNotFeed = "I think %s is not hungry.\n";
        Optional<Set<Pet>> pets = family.getPet();
        int[] countOnesFed = new int[]{0};
        //action if pets isPresent
        Consumer<Set<Pet>> action = petsSet -> petsSet.forEach(pet -> {
                    String nicknameOfPet = pet.getNickname().orElse("XXX");
                    if (pet.getTrickLevel() > new Random().nextInt(101)) {
                        System.out.printf(feed, nicknameOfPet);
                        countOnesFed[0]++;
                    }
                    else {
                            System.out.printf(doNotFeed, nicknameOfPet);
                    }
                    });
        //action if pets isEmpty
        Runnable emptyAction = () -> System.out.println("I have no pet.");
        pets.ifPresentOrElse(action, emptyAction);
        return pets.filter(petsSet -> countOnesFed[0] == petsSet.size()).isPresent();
    }

    public boolean isMale() {
        return this instanceof Man;
    }

    public boolean isFemale() {
        return this instanceof Woman;
    }

    private long convertToEpochMilli(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                .atStartOfDay().toInstant(ZoneId.systemDefault().getRules().getOffset(LocalDateTime.now()))
                .toEpochMilli();
    }

    private LocalDate convertToLocalDate(long epochMilli) {
        return LocalDate.ofInstant(Instant.ofEpochMilli(birthDate), ZoneId.systemDefault());
    }

    @Override
    public String toString() {
        /*
        classes.Human{name='Name', surname='Surname', birthday=1, iq=1, schedule=[[day, task], [day_2, task_2]]}
         */
        return String.format("Human{name=%s, surname=%s, birthdate=%s, iq=%d, schedule=%s}",
                getName().orElse("XXX"),
                getSurname().orElse("XXX"),
                LocalDate.ofInstant(Instant.ofEpochMilli(birthDate), ZoneId.systemDefault()).
                        format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                iq, getSchedule().orElseGet(HashMap::new));
    }

    public String prettyFormat() {
        return String.format("%s: %s",
                this.isMale() ? "boy" : this.isFemale() ? "girl" : "Sex not known",
                this.toString().substring(5));
    }


    //for equality check: all the fields are taken into account, except family is not directly compared since then family and human comparisons will be recursive.
    //instead only mother and father name and surnames are compared
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Human human = (Human) o;
        return birthDate == human.birthDate && iq == human.iq && Objects.equals(name, human.name) &&
                Objects.equals(surname, human.surname) && Objects.equals(schedule, human.schedule) &&
                (family == null || (Objects.equals(family.getMother().name, human.family.getMother().name) &&
                        Objects.equals(family.getFather().name, human.family.getFather().name)));
    }


    @Override
    public int hashCode() {
        int result = Objects.hash(name, surname, birthDate, iq,
                getFamily().flatMap(family -> family.getMother().getName()).orElse(null),
                getFamily().flatMap(family -> family.getMother().getName()).orElse(null),
                schedule);
        return result;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.printf("%s object Garbage Collected: %s\n", this.getClass().getName(), this);
    }
}