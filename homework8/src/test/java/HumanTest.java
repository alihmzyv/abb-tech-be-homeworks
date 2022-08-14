import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HumanTest {

    @Test
    void toStringShouldReturnCorrectData() {
        Human father1 = new Human("Mammad", "Karimov", 1976, 100, Map.of(DayOfWeek.MONDAY, List.of("go to work")));

        //expected String to be returned
        String expected = String.format("Human{name=%s, surname=%s, year=%d, iq=%d, schedule=%s}",
                father1.getName(), father1.getSurname(), father1.getYear(), father1.getIq(), father1.getSchedule());

        assertEquals(expected, father1.toString());
    }

    @Test
    void equalsWorksAccordingToContract() {
        //contract1
        // * It is reflexive: for any non-null reference value x, x.equals(x) should return true.

        //create a Human object
        Human human1 = new Human("Mammad", "Karimov", 1976, 100, Map.of(DayOfWeek.MONDAY, List.of("go to work")));

        boolean contract1 = human1.equals(human1); //true

        //contract2
        // * It is symmetric:
        // * for any non-null reference values x and y, x.equals(y) should return true if and only if y.equals(x) returns true.

        //create another Human object
        Human human2 = new Human("Mammad", "Karimov", 1976, 100, Map.of(DayOfWeek.MONDAY, List.of("go to work")));

        boolean contract2 = human2.equals(human1) && human1.equals(human2); //true


        //contract3
        // * It is transitive: for any non-null reference values x, y, and z,
        // if x.equals(y) returns true and y.equals(z) returns true, then x.equals(z) should return true.

        //compare 3 Human objects
        //create another (third) Human
        Human human3 = new Human("Mammad", "Karimov", 1976, 100, Map.of(DayOfWeek.MONDAY, List.of("go to work")));

        boolean contract3 = human1.equals(human2) && human2.equals(human3) && human1.equals(human3); //true


        // contract 4
        // * It is consistent:
        // for any non-null reference values x and y,
        // multiple invocations of x.equals(y) consistently return true or consistently return false,
        // provided no information used in equals comparisons on the objects is modified.
        boolean consistentEquality = human1.equals(human2); //true
        boolean contract4 = true;

        //check consistently 100 times
        for (int i = 0; i < 100; i++) {
            if (!human1.equals(human2)) {
                contract4 = false;
                break;
            }
        }


        //contract 5
        // * For any non-null reference value x, x.equals(null) should return false.
        boolean contract5 = human1.equals(null); //false


        //collect all contracts into one boolean
        boolean actual = contract1 && contract2 && contract3 && contract4 && !contract5;
        assertTrue(actual);
    }

    @Test
    void hashCodeWorksAccordingToContract() {
        //contract1
        // * Whenever it is invoked on the same object more than once during an execution of a Java application,
        // the hashCode method must consistently return the same integer,
        // provided no information used in equals comparisons on the object is modified.
        // This integer need not remain consistent from one execution of an application to another execution of the same application.

        //create Human object
        Human human1 = new Human("Mammad", "Karimov", 1976, 100, Map.of(DayOfWeek.MONDAY, List.of("go to work")));

        int consistentHashCode = human1.hashCode();
        boolean contract1 = true;

        for (int j = 0; j < 100; j++) {
            if (human1.hashCode() != consistentHashCode) {
                contract1 = false;
                break;
            }
        }


        //contract2
        // * If two objects are equal according to the equals(Object) method,
        // then calling the hashCode method on each of the two objects must produce the same integer result.

        //create family2 equal to family1
        Human human2 = new Human("Mammad", "Karimov", 1976, 100, Map.of(DayOfWeek.MONDAY, List.of("go to work")));


        boolean contract2 = (human1.hashCode() == human2.hashCode());


        //contract3 - not e requirement, thus not implemented as test
        // * It is not required that if two objects are unequal according to the equals(java.lang.Object) method,
        // then calling the hashCode method on each of the two objects must produce distinct integer results.
        // However, the programmer should be aware that producing distinct integer results for unequal objects may improve the performance of hash tables.

        //collect contracts into one boolean
        boolean actual = contract1 && contract2;
        assertTrue(actual);
    }
}