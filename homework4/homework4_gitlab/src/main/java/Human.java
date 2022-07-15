public class Human {
    String name;
    String surname;
    int year;
    int iq;
    Pet pet;
    Human mother;
    Human father;
    String[][] schedule;


    //constructors
    //constructor #1
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
    }

    //constructor #3
    public Human(String name, String surname, int year, int iq, Pet pet, Human mother, Human father, String[][] schedule) {
        this.name = name;
        this.surname = surname;
        this.year = year;
        this.iq = iq;
        this.pet = pet;
        this.mother = mother;
        this.father = father;
        this.schedule = schedule;
    }

    //constructor #4
    public Human() {
    }


    //methods
    public void greetPet() {
        System.out.printf("Hello, %s", pet.nickname);
        System.out.println();
    }

    public void describePet() {
        System.out.printf("I have a %s, he is %d years old, he is %s", pet.species, pet.age, pet.trickLevel > 50 ? "very sly" : "almost not sly");
        System.out.println();
    }

    @Override
    public String toString() {
        return String.format("Human{name=%s, surname=%s, year=%d, iq=%d, mother=%s, father=%s, pet=%s}",
                                name, surname, year, iq,
                                mother == null ? "No data" : mother.name + " " + mother.surname,
                                father == null ? "No data" : father.name + " " + father.surname,
                                pet == null ? "No data" : pet.toString());
    }


}
