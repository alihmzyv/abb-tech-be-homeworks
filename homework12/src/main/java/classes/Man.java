package classes;

import java.util.List;
import java.util.Map;

public final class Man extends Human {
    //constructors
    public Man(String name, String surname, String birthDate) {
        super(name, surname, birthDate);
    }

    public Man(String name, String surname, String birthDate, int iq) {
        super(name, surname, birthDate, iq);
    }

    public Man(String name, String surname, String birthDate, int iq, Map<DayOfWeek, List<String>> schedule) {
        super(name, surname, birthDate, iq, schedule);
    }

    public Man(String name, String surname, Family family, String birthDate, int iq,
               Map<DayOfWeek, List<String>> schedule) {
        super(name, surname, family, birthDate, iq, schedule);
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
