package classes;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 200_000; i++) {
            //object reference not assigned to any variable, thus will be probably ready to be garbage collected
            new Human("Ali", "Hamzayev", "24/03/2001", 100, Map.of(DayOfWeek.MONDAY, List.of("study Java"), DayOfWeek.SUNDAY, List.of("go to campus")));
        }


    }
}

