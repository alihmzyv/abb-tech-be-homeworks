package classes;

import java.util.List;
import java.util.Map;

public final class Woman extends Human {
    //constructors
    public Woman(String name, String surname, String birthDate) {
        super(name, surname, birthDate);
    }

    public Woman(String name, String surname, String birthDate, int iq) {
        super(name, surname, birthDate, iq);
    }

    public Woman(String name, String surname, String birthDate, int iq, Map<DayOfWeek, List<String>> schedule) {
        super(name, surname, birthDate, iq, schedule);
    }

    public Woman(String name, String surname, Family family, String birthDate, int iq, Map<DayOfWeek, List<String>> schedule) {
        super(name, surname, family, birthDate, iq, schedule);
    }

    public Woman() {
    }

    //methods
    public void makeup() {
        System.out.println("I applied makeup");
    }

    //override methods
    @Override
    public void greetPet() {
        super.greetPet();
        System.out.println("I am a man");
    }
}
