import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class FamilyTest {

    //test for overrided methods
    @Test
    void toStringShouldReturnCorrectData() {
        //create a Family object
        Human father1 = new Human("Mammad", "Karimov", 1976, 100, new String[][]{{DayOfWeek.MONDAY.name(), "go to work"}});
        Human mother1 = new Human();
        Family family1 = new Family(mother1, father1);
        Human child1 = new Human();
        Human child2 = new Human("Anar", "Karimov", 1999, 101, new String[][]{{DayOfWeek.MONDAY.name(), "go to school"}});
        Human child3 = new Human("Ulkar", "Karimova", 1998, 99, new String[][]{{DayOfWeek.MONDAY.name(), "go to party"}});

        family1.addChild(child1);
        family1.addChild(child2);
        family1.addChild(child3);

        //build expected String to be returned
        StringBuilder sb = new StringBuilder();

        //append info about parents
        sb.append(String.format("Mother=%s\nFather=%s\n", mother1, father1));

        //append info about each children
        for (int i = 0; i < family1.getChildren().length; i++) {
            sb.append(String.format("Child %d=%s\n", i, family1.getChildren()[i].toString()));
        }

        //append info about pet
        sb.append("No pet");

        String expected = sb.toString();
        assertEquals(expected, family1.toString());
    }



    // * Make tests for equals() and hashcode():  equals() should return true/false, handle all cases according to the equals contract
    // * contracts can be found: https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html#equals(java.lang.Object)
    @Test
    void equalsWorksAccordingToContract() {
        //contract1
        // * It is reflexive: for any non-null reference value x, x.equals(x) should return true.

        //create a Family object
        Human father1 = new Human("Mammad", "Karimov", 1976, 100, new String[][]{{DayOfWeek.MONDAY.name(), "go to work"}});
        Human mother1 = new Human();
        Family family1 = new Family(mother1, father1);

        Human child1 = new Human();
        Human child2 = new Human("Anar", "Karimov", 1999, 101, new String[][]{{DayOfWeek.MONDAY.name(), "go to school"}});
        Human child3 = new Human("Ulkar", "Karimova", 1998, 99, new String[][]{{DayOfWeek.MONDAY.name(), "go to party"}});

        family1.addChild(child1);
        family1.addChild(child2);
        family1.addChild(child3); //currently children array = {child1, child2, child3}

        boolean contract1 = family1.equals(family1); //true

        //contract2
        // * It is symmetric:
        // * for any non-null reference values x and y, x.equals(y) should return true if and only if y.equals(x) returns true.

        //create a Family object
        Human father2 = new Human("Mammad", "Karimov", 1976, 100, new String[][]{{DayOfWeek.MONDAY.name(), "go to work"}});
        Human mother2 = new Human();
        Family family2 = new Family(mother2, father2);

        Human child4 = new Human();
        Human child5 = new Human("Anar", "Karimov", 1999, 101, new String[][]{{DayOfWeek.MONDAY.name(), "go to school"}});
        Human child6 = new Human("Ulkar", "Karimova", 1998, 99, new String[][]{{DayOfWeek.MONDAY.name(), "go to party"}});

        family2.addChild(child4);
        family2.addChild(child5);
        family2.addChild(child6);

        boolean contract2 = family1.equals(family2) && family2.equals(family1); //true


        //contract3
        // * It is transitive: for any non-null reference values x, y, and z,
        // if x.equals(y) returns true and y.equals(z) returns true, then x.equals(z) should return true.

        //compare 3 families
        //create a Family object
        Human father3 = new Human("Mammad", "Karimov", 1976, 100, new String[][]{{DayOfWeek.MONDAY.name(), "go to work"}});
        Human mother3 = new Human();
        Family family3 = new Family(mother3, father3);

        Human child7 = new Human();
        Human child8 = new Human("Anar", "Karimov", 1999, 101, new String[][]{{DayOfWeek.MONDAY.name(), "go to school"}});
        Human child9 = new Human("Ulkar", "Karimova", 1998, 99, new String[][]{{DayOfWeek.MONDAY.name(), "go to party"}});

        family3.addChild(child7);
        family3.addChild(child8);
        family3.addChild(child9);

        boolean contract3 = family1.equals(family2) && family2.equals(family3) && family1.equals(family3); //true


        // contract 4
        // * It is consistent:
        // for any non-null reference values x and y,
        // multiple invocations of x.equals(y) consistently return true or consistently return false,
        // provided no information used in equals comparisons on the objects is modified.
        boolean consistentEquality = family1.equals(family2); //true
        boolean contract4 = true;

        //check consistently 100 times, provided no information used in equals comparisons changed
        for (int i = 0; i < 100; i++) {
            if (!family1.equals(family2)) {
                contract4 = false;
                break;
            }
        }


        //contract 5
        // * For any non-null reference value x, x.equals(null) should return false.
        boolean contract5 = family1.equals(null); //false


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

        //create a Family object
        Human father1 = new Human("Mammad", "Karimov", 1976, 100, new String[][]{{DayOfWeek.MONDAY.name(), "go to work"}});
        Human mother1 = new Human();
        Family family1 = new Family(mother1, father1);
        Human child1 = new Human();
        Human child2 = new Human("Anar", "Karimov", 1999, 101, new String[][]{{DayOfWeek.MONDAY.name(), "go to school"}});
        Human child3 = new Human("Ulkar", "Karimova", 1998, 99, new String[][]{{DayOfWeek.MONDAY.name(), "go to party"}});

        family1.addChild(child1);
        family1.addChild(child2);
        family1.addChild(child3);

        int consistentHashCode = family1.hashCode();
        boolean contract1 = true;

        for (int j = 0; j < 100; j++) {
            if (family1.hashCode() != consistentHashCode) {
                contract1 = false;
                break;
            }
        }


        //contract2
        // * If two objects are equal according to the equals(Object) method,
        // then calling the hashCode method on each of the two objects must produce the same integer result.

        //create family2 equal to family1
        Human father2 = new Human("Mammad", "Karimov", 1976, 100, new String[][]{{DayOfWeek.MONDAY.name(), "go to work"}});
        Human mother2 = new Human();
        Family family2 = new Family(mother2, father2);

        Human child4 = new Human();
        Human child5 = new Human("Anar", "Karimov", 1999, 101, new String[][]{{DayOfWeek.MONDAY.name(), "go to school"}});
        Human child6 = new Human("Ulkar", "Karimova", 1998, 99, new String[][]{{DayOfWeek.MONDAY.name(), "go to party"}});

        family2.addChild(child4);
        family2.addChild(child5);
        family2.addChild(child6);

        boolean contract2 = (family1.hashCode() == family2.hashCode());


        //contract3 - not e requirement, thus not implemented as test
        // * It is not required that if two objects are unequal according to the equals(java.lang.Object) method,
        // then calling the hashCode method on each of the two objects must produce distinct integer results.
        // However, the programmer should be aware that producing distinct integer results for unequal objects may improve the performance of hash tables.

        //collect all contracts into one boolean
        boolean actual = contract1 && contract2;
        assertTrue(actual);
    }

    @Test
    void deleteChildTest1() {//deleteChild(Human child) version
        /*
        checking if the child is actually being removed from the children array
        (if you pass an object that is equivalent to at least one element of the array);
         */

        //create family
        Human father1 = new Human("Mammad", "Karimov", 1976, 100, new String[][]{{DayOfWeek.MONDAY.name(), "go to work"}});
        Human mother1 = new Human();
        Family family1 = new Family(mother1, father1);
        Human child1 = new Human();
        Human child2 = new Human("Anar", "Karimov", 1999, 101, new String[][]{{DayOfWeek.MONDAY.name(), "go to school"}});
        Human child3 = new Human("Ulkar", "Karimova", 1998, 99, new String[][]{{DayOfWeek.MONDAY.name(), "go to party"}});

        family1.addChild(child1);
        family1.addChild(child2);
        family1.addChild(child3); //currently children array = {child1, child2, child3}

        family1.deleteChild(child2); //child2 matches, thus children arr = {child1, child3};
        assertArrayEquals(family1.getChildren(), new Human[]{child1, child3});
    }

    @Test
    void deleteChildTest2() {//deleteChild(Human child) version
        /*
        check if the children array remains unchanged (if you pass an object that is not equivalent to any array element)
         */
        //create family
        Human father1 = new Human("Mammad", "Karimov", 1976, 100, new String[][]{{DayOfWeek.MONDAY.name(), "go to work"}});
        Human mother1 = new Human();
        Family family1 = new Family(mother1, father1);
        Human child1 = new Human();
        Human child2 = new Human("Anar", "Karimov", 1999, 101, new String[][]{{DayOfWeek.MONDAY.name(), "go to school"}});
        Human child3 = new Human("Ulkar", "Karimova", 1998, 99, new String[][]{{DayOfWeek.MONDAY.name(), "go to party"}});

        family1.addChild(child1);
        family1.addChild(child3); //did not add child2

        //create a copy of children arr to check whether the array will remain the same after an unsuccessful delete
        //while it is a shallow copy, since the change can be the removal of a Human object element, thus will not be reflected on this shallow copy
        //but if any field of a Human object of children arr would change, the change would be reflected on this copy as well.
        Human[] childrenCopy = Arrays.copyOf(family1.getChildren(), family1.getChildren().length);


        family1.deleteChild(child2); //desirably unsuccessful delete

        assertArrayEquals(childrenCopy, family1.getChildren());
    }

    @Test
    void deleteChildTest3() { //deleteChild(int index) version
        /*
        check that the child is actually being removed from the children array and the method returns the correct value;
         */
        //create family
        Human father1 = new Human("Mammad", "Karimov", 1976, 100, new String[][]{{DayOfWeek.MONDAY.name(), "go to work"}});
        Human mother1 = new Human();
        Family family1 = new Family(mother1, father1);
        Human child1 = new Human();
        Human child2 = new Human("Anar", "Karimov", 1999, 101, new String[][]{{DayOfWeek.MONDAY.name(), "go to school"}});
        Human child3 = new Human("Ulkar", "Karimova", 1998, 99, new String[][]{{DayOfWeek.MONDAY.name(), "go to party"}});

        family1.addChild(child1);
        family1.addChild(child2);
        family1.addChild(child3);

        // * family1.deleteChild(0) removes the child at the index = 1, returns true
        // * children arr will be updated to arr = {child1, child3}
        assertTrue(family1.deleteChild(0) && Arrays.equals(family1.getChildren(), new Human[]{child1, child3}));
    }

    @Test
    void deleteChildTest4() { //deleteChild(int index) version
        /*
        check that the children array remains unchanged (if you pass an index outside the index range) and
        the method returns the correct value;
         */
        //create family
        Human father1 = new Human("Mammad", "Karimov", 1976, 100, new String[][]{{DayOfWeek.MONDAY.name(), "go to work"}});
        Human mother1 = new Human();
        Family family1 = new Family(mother1, father1);
        Human child1 = new Human();
        Human child2 = new Human("Anar", "Karimov", 1999, 101, new String[][]{{DayOfWeek.MONDAY.name(), "go to school"}});
        Human child3 = new Human("Ulkar", "Karimova", 1998, 99, new String[][]{{DayOfWeek.MONDAY.name(), "go to party"}});

        family1.addChild(child1);
        family1.addChild(child2);
        family1.addChild(child3);

        //create a copy of children arr to check whether the array will remain the same after an unsuccessful delete
        //while it is a shallow copy, since the change can be the removal of a Human object element, thus will not be reflected on this shallow copy
        //but if any field of a Human object of children arr would change, the change would be reflected on this copy as well.
        Human[] childrenCopy = Arrays.copyOf(family1.getChildren(), family1.getChildren().length);


        // * family1.deleteChild(2) tries to remove child at index = 2 + 1 which is out of bounds of children array,
        // thus returns false according to method
        // * children arr remains the same
        assertTrue(!family1.deleteChild(2) && Arrays.equals(childrenCopy, family1.getChildren()));
    }

    @Test
    void addChildTest1() {
        /*
        check that the  children array increases by one element and
        that this element is the passed object with all the necessary references;
         */

        //create family
        Human father1 = new Human("Mammad", "Karimov", 1976, 100, new String[][]{{DayOfWeek.MONDAY.name(), "go to work"}});
        Human mother1 = new Human();
        Family family1 = new Family(mother1, father1);
        Human child1 = new Human("Ulkar", "Karimova", 1998, 99, new String[][]{{DayOfWeek.MONDAY.name(), "go to party"}});


        int lengthChildrenArrBefore = family1.getChildren().length; //family1.getChildren().length = 0
        family1.addChild(child1); //family1.getChildren().length = 1
        int lengthChildrenArrAfter = family1.getChildren().length;


        // * assert length of children arr increased by 1
        // && the last added child in the arr equals the child added
        assertTrue(lengthChildrenArrAfter == lengthChildrenArrBefore + 1 && family1.getChildren()[family1.getChildren().length - 1].equals(child1));
    }


    @Test
    void countFamilyTest1() {
        //create family
        Human father1 = new Human("Mammad", "Karimov", 1976, 100, new String[][]{{DayOfWeek.MONDAY.name(), "go to work"}});
        Human mother1 = new Human();
        Family family1 = new Family(mother1, father1); //count = 2
        Human child1 = new Human();
        Human child2 = new Human("Anar", "Karimov", 1999, 101, new String[][]{{DayOfWeek.MONDAY.name(), "go to school"}});
        Human child3 = new Human("Ulkar", "Karimova", 1998, 99, new String[][]{{DayOfWeek.MONDAY.name(), "go to party"}});

        family1.addChild(child1); //count = 3
        family1.addChild(child2); //count = 4
        family1.deleteChild(child1); //count = 3
        family1.addChild(child3); //count = 4

        family1.deleteChild(child2); //count = 3

        assertEquals(3, family1.countFamily());
    }

}