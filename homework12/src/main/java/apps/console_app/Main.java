package apps.console_app;

import classes.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        //testData
        //family1
        Human father1 = new Human("Mammad", "Karimov", "21/09/1976", 100, Map.of(DayOfWeek.MONDAY, List.of("go to work")));
        Human mother1 = new Human();
        Family family1 = new Family(mother1, father1);
        Human child1 = new Human();
        Human child2 = new Human("Anar", "Karimov", "21/09/1999", 101, Map.of(DayOfWeek.MONDAY, List.of("go to school")));
        Human child3 = new Human("Ulkar", "Karimova", "21/09/2000", 99, Map.of(DayOfWeek.MONDAY, List.of("go to party")));

        family1.addChild(child1);
        family1.addChild(child2);
        family1.addChild(child3);

        //add pet
        //String nickname, int age, int trickLevel, Set<String> habits
        Pet pet1 = new Dog("Toplan", 2, 33, Set.of("barking"));
        Pet pet2 = new Fish("Nemo");
        Set<Pet> petSet = Set.of(pet1, pet2);
        family1.setPet(petSet);

        //family2
        Human father2 = new Human("Mammad", "Karimov", "21/09/1978", 100, Map.of(DayOfWeek.MONDAY, List.of("go to work")));
        Human mother2 = new Human();
        Family family2 = new Family(mother2, father2);

        Human child4 = new Human();
        Human child5 = new Human("Anar", "Karimov", "21/09/1999", 101, Map.of(DayOfWeek.MONDAY, List.of("go to school")));
        Human child6 = new Human("Ulkar", "Karimova", "21/09/1999", 99, Map.of(DayOfWeek.MONDAY, List.of("go to party")));

        family2.addChild(child4);
        family2.addChild(child5);
        family2.addChild(child6);


        //family3
        Human father3 = new Human("Mammad", "Nabiyev", "21/09/1973", 100, Map.of(DayOfWeek.MONDAY, List.of("go to work")));
        Human mother3 = new Human();
        Family family3 = new Family(mother3, father3);

        Human child7 = new Human();
        Human child8 = new Human("Anar", "Karimov", "21/09/1999", 101, Map.of(DayOfWeek.MONDAY, List.of("go to school")));

        family3.addChild(child7);
        family3.addChild(child8);

        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("homework12/src/main/java/apps/console_app/test_data.bin"))) {
            objectOutputStream.writeObject(List.of(family1, family2, family3));
        }
    }
}
