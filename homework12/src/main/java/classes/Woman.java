package classes;

import java.io.Serial;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public final class Woman extends Human {
    @Serial
    private static final long serialVersionUID = 2372906671425885172L;

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

    public Woman(String name, String surname, Family family, String birthDate, int iq,
                 Map<DayOfWeek, List<String>> schedule) {
        super(name, surname, family, birthDate, iq, schedule);
    }

    public Woman() {
    }

    public static Woman of(Human human) {
        return new Woman(human.getName().orElse("XXX"), human.getSurname().orElse("XXX"),
                human.getBirthDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), human.getIq());
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
