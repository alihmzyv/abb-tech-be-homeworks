package apps.console_app;

import apps.console_app.exceptions.IncorrectMenuItem;
import classes.Family;
import classes.Human;
import classes.Man;
import classes.Woman;
import database.CollectionFamilyDao;
import database.FamilyController;
import database.FamilyService;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class ConsoleApp {
    private static String menu;

    private static FamilyController fc;

    public static void main(String[] args) {
        //try to load the menu
        try {
            loadMenu();
        }
        catch (FileNotFoundException exc) {
            System.out.println("Could not find menu.txt file in the app directory. Please add.");
            System.exit(1);
        }
        catch (IOException exc) {
            System.out.println("Could not read the menu");
            System.exit(1);
        }

        //display the menu
        displayMenu();


        try (Scanner sn = new Scanner(System.in)) {
            while (true) {
                //ask for input
                String command; //can be menu item or "exit"
                int menuItemNum;
                try {
                    System.out.println("Please select an item from the menu and enter its number or enter \"exit\"");
                    command = sn.nextLine();
                    if (command.equals("exit")) {
                        System.out.println("Exiting the application...");
                        break;
                    }
                    menuItemNum = Integer.parseInt(command);
                }
                catch (NumberFormatException exc) {
                    System.out.println("Please select an item from the menu and enter its number or enter \"exit\".\nTry again.");
                    continue;
                }
                catch (NoSuchElementException exc) { //no line was found
                    System.out.println("Please do not enter empty input. Try again.");
                    continue;
                }

                try {
                    switch (menuItemNum) {
                        case 1 -> fillWithTestData();
                        case 2 -> displayAllFamilies();
                        case 3 -> displayAllFamiliesBiggerThan();
                        case 4 -> displayAllFamiliesLessThan();
                        case 5 -> displayCountOfFamiliesWithMemberNumber();
                        case 6 -> createNewFamily();
                        case 7 -> deleteFamilyByIndex();
                        case 8 -> editFamilyByIndex();
                        case 9 -> deleteAllChildrenOlderThan();
                        default -> throw new IncorrectMenuItem("There is no menu item matching your input. Please try again");
                    }
                }
                catch (IncorrectMenuItem exc) { //menu item was incorrect
                    System.out.println(exc.getMessage());
                }
                catch (NullPointerException exc) {
                    warnDBIsEmpty();
                }
            }
        }
    }

    private static void loadMenu() throws IOException {
        try (BufferedReader br = new BufferedReader(
                new FileReader("homework12/src/main/java/apps/console_app/menu.txt"))) {
            StringBuilder sb = new StringBuilder();
            br.lines().forEach(line -> sb.append(line).append("\n"));
            menu = sb.toString();
        }
    }

    private static void displayMenu() {
        System.out.println(menu);
    }

    private static Optional<FamilyController> getFC() {
        return Optional.ofNullable(fc);
    }

    private static void warnDBIsEmpty() {
        System.out.println("""
                Database is empty.
                Firstly fill with test data or create a new family.
                Then try again""");
    }

    private static boolean fillWithTestData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("homework12/src/main/java/apps/console_app/test_data.bin"))) {
            fc = new FamilyController(new FamilyService(new CollectionFamilyDao((List<Family>) ois.readObject())));
            System.out.println("Filled database with test data");
            return true;
        }
        catch (FileNotFoundException exc) {
            System.out.println("Could not find test_data.bin file in the app directory. Please add.");
            return false;
        }
        catch (IOException exc) {
            System.out.println("Could not load the data.");
            return false;
        }
        catch (ClassNotFoundException exc) {
            System.out.println(exc.getMessage());
            return false;
        }
    }

    private static void displayAllFamilies() {
        fc.displayAllFamilies();
    }

    private static void displayAllFamiliesBiggerThan() {
        System.out.println("Enter the number of members: ");
        int countMember = getPositiveIntInput();
        fc.getAllFamiliesBiggerThan(countMember);
    }

    private static void displayAllFamiliesLessThan() {
        System.out.println("Enter the number of members: ");
        int countMember = getPositiveIntInput();
        fc.getAllFamiliesLessThan(countMember);
    }

    private static void displayCountOfFamiliesWithMemberNumber() {
        System.out.println("Enter the number of members: ");
        int countMember = getPositiveIntInput();
        System.out.println(fc.countFamiliesWithMemberNumber(countMember));
    }

    private static void createNewFamily() {
        System.out.println("Enter information about the mother:");
        Woman mother = (Woman) getHuman();
        System.out.println("Enter information about the father:");
        Man father = (Man) getHuman();
        fc.createNewFamily(mother, father);
    }

    private static void deleteFamilyByIndex() {
        int index = getCorrectIndexInput();
        fc.deleteFamilyByIndex(index);
    }

    private static void editFamilyByIndex() {
        String subMenu =
                """
                        - 1. Give birth to a baby
                            - request family identifier (ID)
                            - request the necessary data (what name to give the boy, what name to girl)
                          - 2. Adopt a child
                            - request family identifier (ID)
                            - request Required data (full name, year of birth, intelligence)
                          - 3. Return to main menu
                        """;
        System.out.println(subMenu);
        int command;
        while (true) {
            command = getPositiveIntInput();
            if (command <= 3) {
                break;
            }
            System.out.println("Please enter a command from the submenu.");
        }

        switch (command) {
            case 1 -> giveBirth();
            case 2 -> adoptChild();
            case 3 -> {
                return;
            }
        }
    }

    private static void giveBirth() {
        Family family = fc.getFamilybyId(getCorrectIndexInput()).get();
        System.out.println("Boy or girl? (e.g. \"boy\" or \"girl\")");
        String sex = getStringInput();
        while (!(sex.equals("boy") || sex.equals("girl"))) {
            System.out.println("Please enter sex as described.");
            System.out.println("Boy or girl? (e.g. \"boy\" or \"girl\")");
            sex = getStringInput();
        }

        System.out.println("Name?:");
        String name = getStringInput();

        String birthDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        if (sex.equals("boy")) {
            family.addChild(new Man(name, family.getFather().getSurname().orElse("XXX"), birthDate, 0));
        }
        else {
            family.addChild(new Woman(name, family.getFather().getSurname().orElse("XXX"), birthDate, 0));
        }
    }

    private static void adoptChild() {
        int index = getCorrectIndexInput();
        Human child = getHuman();
        fc.adoptChild(fc.getFamilybyId(index).get(), child);
    }

    private static void deleteAllChildrenOlderThan() {
        int age = getPositiveIntInput();
    }

    private static Human getHuman() {
        System.out.println("Enter name:");
        String name = getStringInput();
        System.out.println("Enter surname:");
        String surname = getStringInput();
        System.out.println("Enter birth year: (e.g. 1999)");
        String birthYear = getStringInput();
        System.out.println("Enter birth month: [1, 12]");
        String birthMonth = getStringInput();
        System.out.println("Enter birth day: [1-31(28, 29)]");
        System.out.println("Enter iq value:");
        int iq = getPositiveIntInput();
        while (true) {
            try {
                String birthDay = getStringInput();
                String birthDate = String.join("/", birthDay, birthMonth, birthYear);
                return new Human(name, surname, birthDate, iq);
            }
            catch (DateTimeParseException exc) {
                System.out.println("Please enter birth date details as described. Try again.");
            }
        }
    }

    private static String getStringInput() {
        try (Scanner sn = new Scanner(System.in)) {
            while (true) {
                try {
                    return sn.nextLine();
                }
                catch (NoSuchElementException exc) {
                    System.out.println("Please do not enter empty input. Try again.");
                }
            }
        }
    }

    private static int getPositiveIntInput() {
        int countMember;
        try (Scanner sn = new Scanner(System.in)) {
            while (true) {
                try {
                    countMember = sn.nextInt();
                    if (countMember < 0) {
                        throw new InputMismatchException();
                    }
                    return countMember;
                }
                catch (InputMismatchException exc) {
                    System.out.println("Please enter a positive integer");
                }
            }
        }
    }

    private static int getCorrectIndexInput() {
        int index;
        while (true) {
            index = getPositiveIntInput();
            if (fc.getFamilyService().getFamilyDao().isIndex(index)) {
                return index;
            }
            System.out.printf("Please enter index in range: [%d, %d]\n", 1, fc.count() + 1);
        }
    }
}