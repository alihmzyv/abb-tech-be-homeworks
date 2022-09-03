package apps;

import classes.*;
import database.CollectionFamilyDao;
import database.FamilyController;
import database.FamilyDao;
import database.FamilyService;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        //family1
        Man father1 = new Man("Kanan", "Mammadov", "21/03/1975", 100,
                Map.of(DayOfWeek.MONDAY, List.of("go to work", "take kids from school", "go shopping")));
        Woman mother1 = new Woman("Nazrin", "Mammadova", "22/04/1973", 101,
                Map.of(DayOfWeek.MONDAY, List.of("go to work", "leave early and go to mall")));
        Family family1 = new Family(mother1, father1);

        //family2
        Man father2 = new Man("Vusal", "Gurbanov", "11/02/1973");
        Woman mother2 = new Woman("Aylin", "Gurbanova", "31/01/1971", 101,
                Map.of(DayOfWeek.MONDAY, List.of("go to work", "go to gym")));
        Family family2 = new Family(mother2, father2);

        //family3
        Man father3 = new Man("Aydin", "Karimov", "03/05/1983", 90,
                Map.of(DayOfWeek.MONDAY, List.of("go shopping")));
        Woman mother3 = new Woman("Aygun", "Karimova", "06/07/1984");
        Family family3 = new Family(mother3, father3,
                Set.of(new Dog("Toplan", 2, 33, Set.of("barking"))));

        //family4
        Man father4 = new Man("Aydin", "Najafov", "17/09/1989", 90,
                Map.of(DayOfWeek.MONDAY, List.of("go shopping")));
        Woman mother4 = new Woman("Aygun", "Najafova", "31/12/1990");
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
        familyController.getAllFamiliesBiggerThan(2).forEach(System.out::println); //all families except family2

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
        System.out.println(familyController.bornChild(family2, "FemiNIne").get()); //updated family displayed
        System.out.println(familyController.getFamilybyId(1).get()); //database updated


        System.out.println(familyController.adoptChild(family2, new Man("Ali", "Gurbanov", "24/03/2001", 100)).get()); //updated family displayed
        System.out.println(familyController.getFamilybyId(1).get().getChildren()); //database updated


//        print all children older than 21
        System.out.println("All children before deleting the ones older than 21");
        familyController.getAllFamilies().stream()
                .flatMap(family -> family.getChildren().stream())
                .forEach(System.out::println);

        familyController.deleteAllChildrenOlderThan(21);

        System.out.println("after delete, all children");
        familyController.getAllFamilies().stream()
                .flatMap(family -> family.getChildren().stream())
                        .forEach(System.out::println);


        //count
        System.out.println(familyController.count()); //4 families

        //getFamilyById
        System.out.println(familyController.getFamilybyId(3).get());

        //getPet
        System.out.println(familyController.getPets(2).get()); //toplan

        //addPet
        familyController.addPet(2, new Dog());
        System.out.println(familyController.getFamilybyId(2).get().getPet().get());
    }
}
