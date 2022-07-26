import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class HumanTest {

    @Test
    void toStringShouldReturnCorrectData() {
        Human father1 = new Human("Mammad", "Karimov", 1976, 100, new String[][]{{DayOfWeek.MONDAY.name(), "go to work"}});

        //expected String to be returned
        String expected = String.format("Human{name=%s, surname=%s, year=%d, iq=%d, schedule=%s}",
                father1.getName(), father1.getSurname(), father1.getYear(), father1.getIq(), Arrays.deepToString(father1.getSchedule()));

        assertEquals(expected, father1.toString());
    }


}