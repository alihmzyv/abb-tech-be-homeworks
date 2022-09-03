package classes;

import java.util.List;
import java.util.Map;

public final class Man extends Human {
    //constructors
    public Man(String name, String surname, int year) {
        super(name, surname, year);
    }

    public Man(String name, String surname, int year, int iq, Map<DayOfWeek, List<String>> schedule) {
        super(name, surname, year, iq, schedule);
    }

    public Man(String name, String surname, Family family, int year, int iq, Map<DayOfWeek, List<String>> schedule) {
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
        super.greetPet();
        System.out.println("I am a man");
    }


}
