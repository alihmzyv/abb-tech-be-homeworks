import java.util.List;
import java.util.Map;

public final class Woman extends Human {
    //constructors
    public Woman(String name, String surname, int year) {
        super(name, surname, year);
    }

    public Woman(String name, String surname, int year, int iq, Map<DayOfWeek, List<String>> schedule) {
        super(name, surname, year, iq, schedule);
    }

    public Woman(String name, String surname, Family family, int year, int iq, Map<DayOfWeek, List<String>> schedule) {
        super(name, surname, family, year, iq, schedule);
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
        ;
    }
}
