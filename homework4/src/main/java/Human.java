import java.util.Arrays;

public class Human{
    //declare instance variables
    private String name;
    private String surname;
    private int year;
    private int iq;
    private Pet pet;
    private Human mother;
    private Human father;
    private String[][] schedule;

    //contructor #1
    public Human(String name, String surname, int year) {
        this.name = name;
        this.surname = surname;
        this.year = year;
    }

    //constructor #2
    public Human(String name, String surname, int year, Human mother, Human father) {
        this.name = name;
        this.surname = surname;
        this.year = year;
        this.mother = mother;
        this.father = father;
        //if the human objects outside the class passed as mother and father parameters are changed,
        // the mother and father instance variables of the human object will also change
        // since they reference to the same objects. But it is okay for this case, logically.
    }

    //constructor #3
    public Human(String name, String surname, int year, int iq, Pet pet, Human mother, Human father, String[][] schedule) {
        this.name = name;
        this.surname = surname;
        this.year = year;
        this.iq = iq;
        this.pet = pet;
        //if the pet object outside the class passed pet parameter is changed,
        // pet instance variable of the human object will also change
        // since they reference to the same object. But it is okay for this case, logically.
        this.mother = mother;
        this.father = father;
        this.schedule = Arrays.copyOf(schedule, schedule.length);
        //the measure is taken by creating a new copy object of schedule array so that if the array object outside the class passed schedule parameter is changed,
        // schedule instance variable of the human object will not change.
    }

    //constructor #4
    public Human() {
    }


    //getters and setters: except the "schedules", all the reference type instance variables's references are returned or a reference is assigned to them directly.
    //Thus any change of the objects passed as parameters or operation done on the returned references will affect the variables of human type object.
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

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Human getMother() { return mother; }

    public void setMother(Human mother) {
        this.mother = mother;
    }

    public Human getFather() {
        return father;
    }

    public void setFather(Human father) {
        this.father = father;
    }

    public String[][] getSchedule() {
        return Arrays.copyOf(schedule, schedule.length);
    }

    public void setSchedule(String[][] schedule) {
        this.schedule = Arrays.copyOf(schedule, schedule.length);
    }


    //methods
    public void greetPet() {
        System.out.println("Hello, "+pet.getNickname());
    }

    public void describePet() {
        String slyness = (pet.getTrickLevel()>50) ? "very sly" : "almost not sly";
        System.out.println("I have a "+pet.getSpecies()+
                ", he is "+pet.getAge()+" years old, he is "+slyness);
    }


    //override toString() method
    @Override
    public String toString() {
        return "Human{name='"+name+"',"+
                " surname='"+surname+"',"+
                " year="+year+","+
                " iq="+iq+","+
                " mother="+mother.name+","+
                " father="+father.name+","+
                pet.toString()+"}";
    }

}
