public final class Man extends Human {
    //constructors
    public Man(String name, String surname, int year) {
        super(name, surname, year);
    }

    public Man(String name, String surname, int year, int iq, String[][] schedule) {
        super(name, surname, year, iq, schedule);
    }

    public Man(String name, String surname, Family family, int year, int iq, String[][] schedule) {
        super(name, surname, family, year, iq, schedule);
    }

    public Man() {
    }


    //methods
    public void repairCar() {
        System.out.println("I repaired the car");
    }


    //override methods
    @Override
    public void greetPet() {
        System.out.printf("Hello, I am a %s, %s\n", getClass().getName().toLowerCase(), getFamily().getPet().getNickname());
    }


}
