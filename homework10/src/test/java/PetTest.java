import Classes.Dog;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PetTest {

    @Test
    void toStringShouldReturnCorrectData() {
        Dog pet1 = new Dog("Toplan", 2, 33, Set.of("barking"));

        //expected String to be returned
        String expected = String.format("%s{nickname=%s: Can fly: %s, Has %d legs, Has fur: %s; age=%d, tricklevel=%d, habits=%s}",
                pet1.getSpecies(), pet1.getNickname(), pet1.getSpecies().canFly, pet1.getSpecies().numberOfLegs, pet1.getSpecies().hasFur,
                pet1.getAge(), pet1.getTrickLevel(), pet1.getHabits());

        assertEquals(expected, pet1.toString());
    }

    @Test
    void equalsWorksAccordingToContract() {
        //contract1
        // * It is reflexive: for any non-null reference value x, x.equals(x) should return true.

        //create Dog object
        Dog pet1 = new Dog( "Toplan", 2, 33, Set.of("barking"));

        boolean contract1 = pet1.equals(pet1); //true

        //contract2
        // * It is symmetric:
        // * for any non-null reference values x and y, x.equals(y) should return true if and only if y.equals(x) returns true.

        //create another Dog object
        Dog pet2 = new Dog("Toplan", 2, 33, Set.of("barking"));
        boolean contract2 = pet2.equals(pet1) && pet1.equals(pet2); //true


        //contract3
        // * It is transitive: for any non-null reference values x, y, and z,
        // if x.equals(y) returns true and y.equals(z) returns true, then x.equals(z) should return true.

        //compare 3 Dog objects
        //create another (third) Dog
        Dog pet3 = new Dog("Toplan", 2, 33, Set.of("barking"));
        boolean contract3 = pet1.equals(pet2) && pet2.equals(pet3) && pet1.equals(pet3); //true


        // contract 4
        // * It is consistent:
        // for any non-null reference values x and y,
        // multiple invocations of x.equals(y) consistently return true or consistently return false,
        // provided no information used in equals comparisons on the objects is modified.
        boolean consistentEquality = pet1.equals(pet2); //true
        boolean contract4 = true;

        //check consistently 100 times
        for (int i = 0; i < 100; i++) {
            if (!pet1.equals(pet2)) {
                contract4 = false;
                break;
            }
        }


        //contract 5
        // * For any non-null reference value x, x.equals(null) should return false.
        boolean contract5 = pet1.equals(null); //false


        //collect all contracts in one boolean
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

        //create Dog object
        Dog pet1 = new Dog("Toplan", 2, 33, Set.of("barking"));
        int consistentHashCode = pet1.hashCode();
        boolean contract1 = true;

        for (int j = 0; j < 100; j++) {
            if (pet1.hashCode() != consistentHashCode) {
                contract1 = false;
                break;
            }
        }


        //contract2
        // * If two objects are equal according to the equals(Object) method,
        // then calling the hashCode method on each of the two objects must produce the same integer result.

        //create family2 equal to family1
        Dog pet2 = new Dog("Toplan", 2, 33, Set.of("barking"));

        boolean contract2 = (pet1.hashCode() == pet2.hashCode());


        //contract3 - not e requirement, thus not implemented as test
        // * It is not required that if two objects are unequal according to the equals(java.lang.Object) method,
        // then calling the hashCode method on each of the two objects must produce distinct integer results.
        // However, the programmer should be aware that producing distinct integer results for unequal objects may improve the performance of hash tables.

        //collect contracts into one boolean
        boolean actual = contract1 && contract2;
        assertTrue(actual);
    }
}