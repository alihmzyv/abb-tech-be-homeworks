package database;

import classes.*;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class FamilyServiceTest {
    //family1
    Man father1 = new Man("Kanan", "Mammadov", "21/09/1978", 100,
            Map.of(DayOfWeek.MONDAY, List.of("go to work", "take kids from school", "go shopping")));
    Woman mother1  = new Woman("Nazrin", "Mammadova", "22/03/1977", 101,
            Map.of(DayOfWeek.MONDAY, List.of("go to work", "leave early and go to mall")));
    Family family1 = new Family(mother1, father1);

    //family2
    Man father2 = new Man("Vusal", "Gurbanov", "11/10/1971");
    Woman mother2  = new Woman("Aylin", "Gurbanova", "21/09/1979", 101,
            Map.of(DayOfWeek.MONDAY, List.of("go to work", "go to gym")));
    Family family2 = new Family(mother2, father2);

    //family3
    Man father3 = new Man("Aydin", "Karimov", "21/09/1970", 90,
            Map.of(DayOfWeek.MONDAY, List.of("go shopping")));
    Woman mother3  = new Woman("Aygun", "Karimova", "21/09/1970");
    Family family3 = new Family(mother3, father3,
            Set.of(new Dog("Toplan", 2, 33, Set.of("barking"))));

    //family4
    Man father4 = new Man("Aydin", "Najafov", "21/01/1970", 90,
            Map.of(DayOfWeek.MONDAY, List.of("go shopping")));
    Woman mother4  = new Woman("Aygun", "Najafova", "13/12/1979");
    Family family4 = new Family(mother4, father4);

    FamilyDao familyDao = new CollectionFamilyDao(List.of(family1, family2, family3, family4));
    FamilyService familyService = new FamilyService(familyDao);

    {
        familyService.bornChild(family1, "masculine");
        familyService.bornChild(family3, "feminine");
        familyService.bornChild(family3, "masculine");
        familyService.bornChild(family4, "masculine");
        familyService.bornChild(family4, "feminine");
    }

    @Test
    void getAllFamiliesTest1() {
        assertEquals(List.of(family1, family2, family3, family4), familyService.getAllFamilies());
    }

    @Test
    void displayAllFamiliesTest1() {
        familyService.displayAllFamilies();
    }

    @Test
    void getAllFamiliesBiggerThanTest1() {
        assertEquals(List.of(family1, family3, family4), familyService.getAllFamiliesBiggerThan(2));
    }

    @Test
    void getAllFamiliesBiggerThanTest2() {
        assertEquals(List.of(), familyService.getAllFamiliesBiggerThan(5));
    }

    @Test
    void getAllFamiliesLessThanTest1() {
        assertEquals(List.of(family2), familyService.getAllFamiliesLessThan(3));
    }

    @Test
    void getAllFamiliesLessThanTest2() {
        assertEquals(List.of(), familyService.getAllFamiliesLessThan(2));
    }

    @Test
    void countFamiliesWithMemberNumberTest1() {
        assertEquals(1, familyService.countFamiliesWithMemberNumber(3));
    }

    @Test
    void countFamiliesWithMemberNumberTest2() {
        assertEquals(2, familyService.countFamiliesWithMemberNumber(4));
    }

    @Test
    void countFamiliesWithMemberNumberTest3() {
        assertEquals(0, familyService.countFamiliesWithMemberNumber(1));
    }

    @Test
    void createNewFamilyTest1() {
        Man tempFather = new Man("Akif", "Namazov", "21/10/1941");
        Woman tempMother = new Woman("Aynur", "Namazova", "21/09/1945");
        Family tempFamily = new Family(tempMother, tempFather);
        familyService.createNewFamily(tempMother, tempFather);
        assertEquals(List.of(family1, family2, family3, family4, tempFamily), familyService.getAllFamilies());
    }

    @Test
    void createNewFamilyTest2() {
        familyService.createNewFamily(mother2, father2); //should update family2, not add to end
        assertEquals(List.of(family1, family2, family3, family4), familyService.getAllFamilies());
    }

    @Test
    void deleteFamilyByIndex() {
        familyService.deleteFamilyByIndex(1);
        assertEquals(List.of(family1, family3, family4), familyService.getAllFamilies());
    }

    @Test
    void bornChild() {
        familyService.bornChild(family3, "masculine"); //member number = 5
        assertEquals(List.of(family3), familyService.getAllFamiliesBiggerThan(4));
    }

    @Test
    void adoptChild() {
        familyService.adoptChild(family3, new Man("Child", "Temp", "11/11/2000")); //member number = 5
        assertEquals(List.of(family3), familyService.getAllFamiliesBiggerThan(4));
    }

    @Test
    void deleteAllChildrenOlderThan() {
        familyService.deleteAllChildrenOlderThan(0); //all children are older than 0 in familyService
        assertEquals(4, familyService.countFamiliesWithMemberNumber(2));
    }

    @Test
    void count() {
        assertEquals(4, familyService.count());
    }

    @Test
    void getFamilyByIdTest1() {
        assertEquals(family3, familyService.getFamilyById(2).orElse(null));
    }

    @Test
    void getFamilyByIdTest2() {
        assertNull(familyService.getFamilyById(4).orElse(null));
    }

    @Test
    void getFamilyByIdTest3() {
        assertNull(familyService.getFamilyById(- 1).orElse(null));
    }


    @Test
    void getPets() {
        assertEquals(List.of(new Dog("Toplan", 2, 33, Set.of("barking"))),
                familyService.getPets(2).orElse(null));
    }

    @Test
    void addPet() {
        DomesticCat domesticCat = new DomesticCat("Rock", 1, 35, Set.of("sleeping"));
        familyService.addPet(2, domesticCat);
        List<Pet> pets = List.of(domesticCat, new Dog("Toplan", 2, 33, Set.of("barking")));
        assertEquals(pets,
                     familyService.getPets(2).orElse(null));
    }
}