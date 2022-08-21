import Classes.*;
import databaseClasses.CollectionFamilyDao;
import databaseClasses.FamilyController;
import databaseClasses.FamilyDao;
import databaseClasses.FamilyService;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        //family1
        Man father1 = new Man("Kanan", "Mammadov", 1978, 100,
                Map.of(DayOfWeek.MONDAY, List.of("go to work", "take kids from school", "go shopping")));
        Woman mother1 = new Woman("Nazrin", "Mammadova", 1979, 101,
                Map.of(DayOfWeek.MONDAY, List.of("go to work", "leave early and go to mall")));
        Family family1 = new Family(mother1, father1);

        //family2
        Man father2 = new Man("Vusal", "Gurbanov", 1993);
        Woman mother2 = new Woman("Aylin", "Gurbanova", 1995, 101,
                Map.of(DayOfWeek.MONDAY, List.of("go to work", "go to gym")));
        Family family2 = new Family(mother2, father2);

        //family3
        Man father3 = new Man("Aydin", "Karimov", 1985, 90,
                Map.of(DayOfWeek.MONDAY, List.of("go shopping")));
        Woman mother3 = new Woman("Aygun", "Karimova", 1983);
        Family family3 = new Family(mother3, father3,
                Set.of(new Dog("Toplan", 2, 33, Set.of("barking"))));

        //family4
        Man father4 = new Man("Aydin", "Najafov", 1981, 90,
                Map.of(DayOfWeek.MONDAY, List.of("go shopping")));
        Woman mother4 = new Woman("Aygun", "Najafova", 1980);
        Family family4 = new Family(mother4, father4);

        FamilyDao familyDao = new CollectionFamilyDao(List.of(family1, family2, family3, family4));
        FamilyService familyService = new FamilyService(familyDao);
        FamilyController familyController = new FamilyController(familyService);
        familyController.bornChild(family1, "masculine");
        familyController.bornChild(family3, "feminine");
        familyController.bornChild(family3, "masculine");
        familyController.bornChild(family4, "masculine");
        familyController.bornChild(family4, "feminine");

//        //getAllFamilies
        familyController.getAllFamilies().forEach(System.out::println);

//        //displayAllFamilies
        familyController.displayAllFamilies();

//        //getAllFamiliesBiggerThan
        familyController.getAllFamiliesBiggerThan(2); //all families except family2

//        //getAllFamiliesLessThan
        familyController.getAllFamiliesLessThan(3); //only family2

//        //countFamiliesWithMemberNumber
        System.out.println(familyController.countFamiliesWithMemberNumber(4)); //family3, family5 = 2

        //createNewFamily
        familyController.createNewFamily(new Woman(), new Man()); //families are 5 now
        familyController.displayAllFamilies();

        //deleteFamilyByIndex
        familyController.deleteFamilyByIndex(4); //deleted the family at index 4
        familyController.displayAllFamilies();

        //bornChild
        System.out.println(familyController.bornChild(family2, "FemiNIne")); //updated family displayed
        System.out.println(familyController.getFamilybyId(1)); //database updated


        System.out.println(familyController.adoptChild(family2, new Man("Ali", "Gurbanov", 2000))); //updated family displayed
        System.out.println(familyController.getFamilybyId(1).getChildren()); //database updated


//        print all children born before 2001
        familyController.getAllFamilies().stream()
                .flatMap(family -> family.getChildren().stream())
                .filter(child -> child.getYear() < 2001)
                .forEach(System.out::println);

        familyController.deleteAllChildrenOlderThan(21); //born before 2001

        System.out.println(familyController.getAllFamilies().stream()
                .flatMap(family -> family.getChildren().stream())
                .filter(child -> child.getYear() < 2001)
                .count()); //0


        //count
        System.out.println(familyController.count()); //4 families

        //getFamilyById
        System.out.println(familyController.getFamilybyId(3));

        //getPet
        System.out.println(familyController.getPets(2)); //toplan

        //addPet
        familyController.addPet(2, new Dog());
        System.out.println(familyController.getFamilybyId(2).getPet());
    }
}
