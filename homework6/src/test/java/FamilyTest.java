import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class FamilyTest {


    @Test
    void deleteChildTest1() {//deleteChild(Human child) version
        //create family
        Human father1 = new Human("Mammad", "Karimov", 1976, 100, new String[][]{{"Monday", "go to work"}});
        Human mother1 = new Human();
        Family family1 = new Family(mother1, father1);
        Human child1 = new Human();
        Human child2 = new Human("Anar", "Karimov", 1999, 101, new String[][]{{"Monday", "go to school"}});
        Human child3 = new Human("Ulkar", "Karimova", 1998, 99, new String[][]{{"Monday", "go to party"}});

        family1.addChild(child1);
        family1.addChild(child2);
        family1.addChild(child3);

        family1.deleteChild(child2);
        assertArrayEquals(family1.getChildren(), new Human[]{child1, child3});
    }

    @Test
    void deleteChildTest2() {//deleteChild(Human child) version
        //create family
        Human father1 = new Human("Mammad", "Karimov", 1976, 100, new String[][]{{"Monday", "go to work"}});
        Human mother1 = new Human();
        Family family1 = new Family(mother1, father1);
        Human child1 = new Human();
        Human child2 = new Human("Anar", "Karimov", 1999, 101, new String[][]{{"Monday", "go to school"}});
        Human child3 = new Human("Ulkar", "Karimova", 1998, 99, new String[][]{{"Monday", "go to party"}});

        family1.addChild(child1);
        family1.addChild(child3); //did not add child2
        Human[] childrenCopy = Arrays.copyOf(family1.getChildren(), family1.getChildren().length); //to check whether the array will change after unsuccessful delete
        //while it is shallow copy, the change can be the removal of an Human object element, thus not reflected on this shallow copy
        //if any Human object of children array would change, the change would be reflected on this copy as well.

        family1.deleteChild(child2); //desirably unsuccessful delete
        assertArrayEquals(childrenCopy, family1.getChildren());
    }

    @Test
    void deleteChildTest3() {
        //create family
        Human father1 = new Human("Mammad", "Karimov", 1976, 100, new String[][]{{"Monday", "go to work"}});
        Human mother1 = new Human();
        Family family1 = new Family(mother1, father1);
        Human child1 = new Human();
        Human child2 = new Human("Anar", "Karimov", 1999, 101, new String[][]{{"Monday", "go to school"}});
        Human child3 = new Human("Ulkar", "Karimova", 1998, 99, new String[][]{{"Monday", "go to party"}});

        family1.addChild(child1);
        family1.addChild(child2);
        family1.addChild(child3);


        assertTrue(family1.deleteChild(0) && Arrays.deepEquals(family1.getChildren(), new Human[]{child1, child3}));
    }

    @Test
    void deleteChildTest4() {
        //create family
        Human father1 = new Human("Mammad", "Karimov", 1976, 100, new String[][]{{"Monday", "go to work"}});
        Human mother1 = new Human();
        Family family1 = new Family(mother1, father1);
        Human child1 = new Human();
        Human child2 = new Human("Anar", "Karimov", 1999, 101, new String[][]{{"Monday", "go to school"}});
        Human child3 = new Human("Ulkar", "Karimova", 1998, 99, new String[][]{{"Monday", "go to party"}});

        family1.addChild(child1);
        family1.addChild(child2);
        family1.addChild(child3);
        Human[] childrenCopy = Arrays.copyOf(family1.getChildren(), family1.getChildren().length); //to check whether the array will change after unsuccessful delete
        //while it is shallow copy, the change can be the removal of a Human object element, thus not reflected on this shallow copy.
        //if any Human object of children array would change, the change would be reflected on this copy as well.

        assertTrue(!family1.deleteChild(2) && Arrays.deepEquals(childrenCopy, family1.getChildren()));
    }

    @Test
    void addChildTest1() {
        Human father1 = new Human("Mammad", "Karimov", 1976, 100, new String[][]{{"Monday", "go to work"}});
        Human mother1 = new Human();
        Family family1 = new Family(mother1, father1);
        Human child1 = new Human("Ulkar", "Karimova", 1998, 99, new String[][]{{"Monday", "go to party"}});
        int lengthChildrenArrBefore = family1.getChildren().length; //length of children array before any addition of children
        family1.addChild(child1); //length to be increased by 1
        int lengthChildrenArrAfter = family1.getChildren().length;

        assertTrue(lengthChildrenArrAfter == lengthChildrenArrBefore + 1 && family1.getChildren()[family1.getChildren().length - 1].equals(child1));
    }


    @Test
    void countFamilyTest1() {
        //create family
        Human father1 = new Human("Mammad", "Karimov", 1976, 100, new String[][]{{"Monday", "go to work"}});
        Human mother1 = new Human();
        Family family1 = new Family(mother1, father1); //count = 2
        Human child1 = new Human();
        Human child2 = new Human("Anar", "Karimov", 1999, 101, new String[][]{{"Monday", "go to school"}});
        Human child3 = new Human("Ulkar", "Karimova", 1998, 99, new String[][]{{"Monday", "go to party"}});

        family1.addChild(child1); //count = 3
        family1.addChild(child2); //count = 4
        family1.deleteChild(child1); //count = 3
        family1.addChild(child3); //count = 4

        family1.deleteChild(child2); //count = 3

        assertEquals(3, family1.countFamily());
    }
}