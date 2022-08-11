import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class FamilyTest {

    @Test
    void bornChildTest1() {
        //String nickname, int age, int trickLevel, String[] habits
        Pet pet1 = new RoboCat("Mastan", 1, 55, new String[]{"meowing", "catching mouse"});
        //String name, String surname, int year, int iq, String[][] schedule
        Human father1 = new Man("Kanan", "Mammadov", 1987, 100,
                                new String[][]{{DayOfWeek.MONDAY.name(), "go to work"}});
        Human mother1 = new Woman("Lala", "Mammadova", 1989, 102,
                new String[][]{{DayOfWeek.MONDAY.name(), "go to work"}});
        Family family1 = new Family(mother1, father1, pet1);
        family1.addChild(family1.bornChild());
        System.out.println(family1);
    }
}