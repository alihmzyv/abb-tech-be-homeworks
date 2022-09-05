package apps.console_app;

import apps.console_app.exceptions.EmptyDatabaseException;
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
import java.util.stream.IntStream;

public class ConsoleApp {
    private static String mainMenu;

    private static String subMenu1;

    private static FamilyController fc;

    final private static Scanner sn = new Scanner(System.in); //all the methods use the same Scanner

    static { //static block to initialize mainMenu and subMenu1
        //mainMenu loading
        try {
            mainMenu = loadMenu();
        } catch (FileNotFoundException exc) {
            System.out.println("Could not find menu.txt file in the application directory.");
            System.exit(1);
        } catch (IOException exc) {
            System.out.println("Could not read the menu.");
            System.exit(1);
        }

        //subMenu loading
        try {
            subMenu1 = loadSubMenu();
        } catch (IOException e) {
            System.out.println("Could not load the submenu.");
            System.exit(1);
        }
    }

    public static Optional<FamilyController> getFc() {
        return Optional.ofNullable(fc);
    }

    public static void main(String[] args) {
        String command = "";
        int menuItemNum;

        try (sn) {
            while (true) {
                //ask for input - either menu item or "exit"
                try {
                    displayMenu();
                    System.out.println("Please enter the number of an item from the menu to proceed or " +
                            "enter \"exit\". to exit.");
                    command = sn.nextLine(); //can be "exit" or menu item
                    menuItemNum = Integer.parseInt(command);
                } catch (NumberFormatException exc) {
                    if (command.equals("exit")) { //exit the loop
                        System.out.println("Exiting the application...");
                        break;
                    } else {
                        System.out.println("Incorrect input. Try again.");
                        continue;
                    }
                } catch (NoSuchElementException exc) { //no line was found
                    System.out.println("Please do not enter empty input. Try again.");
                    continue;
                }

                //call the corresponding method
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
                        default -> System.out.println("Incorrect menu item. Try again.");
                    }
                }
                catch (EmptyDatabaseException exc) {
                    System.out.println(exc.getMessage());
                }
            }
        }
    }

    private static String loadMenu() throws IOException {
        //tries to initialize mainMenu static field
        //menu.txt file should be located in the path:
        //\abb-tech-be-homeworks\homework12\src\main\java\apps\console_app\menu.txt
        try (BufferedReader br = new BufferedReader(
                new FileReader("homework12/src/main/java/apps/console_app/menu_files/menu.txt"))) {
            StringBuilder sb = new StringBuilder();
            br.lines().forEach(line -> sb.append(line).append("\n"));
            return sb.toString();
        }
    }

    private static String loadSubMenu() throws IOException {
        //tries to return the giveBirth submenu of the Main Menu
        //sub_menu.txt.txt file should be located in the path:
        //\abb-tech-be-homeworks\homework12\src\main\java\apps\console_app\sub_menu.txt
        try (BufferedReader br = new BufferedReader(
                new FileReader("homework12/src/main/java/apps/console_app/menu_files/sub_menu.txt"))) {
            StringBuilder sb = new StringBuilder();
            br.lines().forEach(line -> sb.append(line).append("\n"));
            return sb.toString();
        }
    }

    private static void displayMenu() {
        //prints mainMenu static field to the console
        System.out.println(mainMenu);
    }

    private static void displaySubmenu1() {
        System.out.println(subMenu1);
    }

    private static boolean fillWithTestData() {
        //tries to fill initialize FamilyController fc with the data from the path:
        //\abb-tech-be-homeworks\homework12\src\main\java\apps\console_app\menu.txt

        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("homework12/src/main/java/apps/console_app/test_files/test_data.bin"))) {
            fc = new FamilyController(new FamilyService(new CollectionFamilyDao((List<Family>) ois.readObject())));
            System.out.println("Filled database with test data");
            return true;
        } catch (FileNotFoundException exc) {
            System.out.println("Could not find test_data.bin file in the application directory/test_files");
            return false;
        } catch (IOException exc) {
            System.out.println("Could not read the data.");
            return false;
        } catch (ClassNotFoundException exc) {
            System.out.println(exc.getMessage());
            return false;
        }
    }

    private static void displayAllFamilies() throws EmptyDatabaseException {
        //displays all the families
        ConsoleApp.requiresNonEmpty().displayAllFamilies();
    }

    private static void displayAllFamiliesBiggerThan() throws EmptyDatabaseException {
        //displays all the families whose number of members is greater than the positive integer input by user
        ConsoleApp.requiresNonEmpty().getFamilyService().displayIndexed(fc.getAllFamiliesBiggerThan(getFamilyMemberNumber()));
    }

    private static void displayAllFamiliesLessThan() throws EmptyDatabaseException {
        //displays all the families whose number of members is less than the positive integer input by user
        ConsoleApp.requiresNonEmpty().getFamilyService().displayIndexed(fc.getAllFamiliesLessThan(getFamilyMemberNumber()));
    }

    private static void displayCountOfFamiliesWithMemberNumber() throws EmptyDatabaseException {
        //displays all the families whose number of members is equal to the positive integer input by user
        System.out.println(ConsoleApp.requiresNonEmpty().countFamiliesWithMemberNumber(getFamilyMemberNumber()));
    }

    private static void createNewFamily() {
        //creates new Family by taking the data about mother and father
        //adds to the database
        System.out.println("Enter information about the mother:");
        Woman mother = Woman.of(getHuman());
        System.out.println("Enter information about the father:");
        Man father = Man.of(getHuman());
        if (getFc().isEmpty()) { //if database is not initialized, initialize first
            fc = new FamilyController(new FamilyService(new CollectionFamilyDao(new ArrayList<>())));
        }
        fc.createNewFamily(mother, father);
        System.out.println("new Family was created and added to the database.");
    }

    private static void deleteFamilyByIndex() throws EmptyDatabaseException {
        //deletes the family at the index input by user
        ConsoleApp.requiresNonEmpty().deleteFamilyByIndex(getCorrectIndexInput() - 1);
        System.out.println("Family deleted.");
    }

    private static void editFamilyByIndex() throws EmptyDatabaseException {
        //edit the family at the index by input or return to main Menu
        //can edit family by:
        //1 -> giveBirth
        //2 -> adoptChild
        //or 3 -> return to main Menu
        while (true) {
            displaySubmenu1();
            System.out.println("Please enter the number of an item from the submenu displayed.");
            int command = getPositiveIntInput();

            //call the corresponding method
            switch (command) {
                case 1 -> {
                    giveBirth();
                    System.out.println("The child was born into the family.");
                    continue;
                }
                case 2 -> {
                    adoptChild();
                    System.out.println("The family adopted the child.");
                    continue;
                }
                case 3 -> {
                }
                default -> {
                    System.out.println("Incorrect submenu item. Try again.");
                    continue;
                }
            }
            break; //returns to mainMethod (main Menu)
        }
    }

    private static void giveBirth() throws EmptyDatabaseException {
        //adds child to the family at the index input by user
        //sex and the name of the child are also input by user
        //date of birth is set to the date the method called
        Family family = ConsoleApp.requiresNonEmpty().getFamilybyId(getCorrectIndexInput() - 1).get();

        String sex = "xxx";
        while (!(sex.equals("boy") || sex.equals("girl"))) {
            System.out.println("Please enter sex as described.");
            System.out.println("Boy or girl? (e.g. \"boy\" or \"girl\")");
            sex = getStringInput();
        }

        System.out.println("Name?:");
        String name = getStringInput();

        String birthDate = LocalDate.now().format(DateTimeFormatter.ofPattern("d/M/yyyy"));


        Human child;
        if (sex.equals("boy")) {
            child = new Man(name, family.getFather().getSurname().orElse("XXX"), birthDate, 0);
        }
        else {
            child = new Woman(name, family.getFather().getSurname().orElse("XXX"), birthDate, 0);
            if (child.isFemale()) {
                child.setSurname(child.getSurname().get() + "a"); //a suffix to females
            }
        }

        fc.adoptChild(family, child);
    }

    private static void adoptChild() throws EmptyDatabaseException {
        //adds a Human as child to the family at the index input by user
        //details of child as a Human are input by user
        ConsoleApp.requiresNonEmpty().adoptChild(fc.getFamilybyId(getCorrectIndexInput() - 1).get(), getHuman());
    }

    private static void deleteAllChildrenOlderThan() throws EmptyDatabaseException {
        ConsoleApp.requiresNonEmpty();
        System.out.println("Enter the age:");
        int age = getNonNegativeIntInput();
        fc.deleteAllChildrenOlderThan(age);
        System.out.printf("Children over age %s were deleted.\n", age);
    }

    private static FamilyController requiresNonEmpty() throws EmptyDatabaseException {
        if (getFc().isEmpty() || getFc().get().count() == 0) {
            throw new EmptyDatabaseException("There is no family in the database.\n" +
                    "Please fill with test data (1, main menu) or create new family (6, main menu).");
        }
        else {
            return getFc().get();
        }
    }

    private static Human getHuman() {
        //creates and returns a Human whose details are based on the user input
        System.out.println("Enter name:");
        String name = getStringInput();
        System.out.println("Enter surname:");
        String surname = getStringInput();
        System.out.println("Enter iq value:");
        int iq = getNonNegativeIntInput();
        while (true) {
            try {
                System.out.println("Enter birth year: (e.g. 1999)");
                String birthYear = getStringInput();
                System.out.println("Enter birth month: [1, 12]");
                String birthMonth = getStringInput();
                System.out.println("Enter birth day: [1-31(28, 29)]");
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
        //used to get a general String input
        while (true) {
            try {
                return sn.nextLine();
            }
            catch (NoSuchElementException exc) {
                System.out.println("Please do not enter empty input. Try again.");
            }
        }
    }

    private static int getNonNegativeIntInput() {
        //used to get a general non-negative integer input
        int input;
        while (true) {
            try {
                input = sn.nextInt();
                if (input < 0) {
                    System.out.println("Please enter a non-negative integer. Try again.");
                    continue;
                }
                sn.nextLine();
                return input;
            }
            catch (InputMismatchException exc) {
                System.out.println("Please enter an integer. Try again.");
                sn.nextLine();
            }
        }
    }

    private static int getPositiveIntInput() {
        //used to get a general positive integer input
        int input;
        while (true) {
            input = getNonNegativeIntInput();
            if (input == 0) {
                System.out.println("Please enter a positive integer. Try again.");
                continue;
            }
            return input;
        }
    }

    private static int getFamilyMemberNumber() {
        //used to get family member number input for the methods requiring as parameters
        System.out.println("Enter the number of members:");
        return getNonNegativeIntInput();
    }

    private static int getCorrectIndexInput() {
        //used to get a correct family index input according to the database size
        int index;
        while (true) {
            System.out.println("Enter the index of family:");
            index = getPositiveIntInput();
            if (fc.getFamilyService().getFamilyDao().isIndex(index - 1)) {
                return index;
            }
            else {
                System.out.printf("Please enter index in range: [%d, %d]\n", 1, fc.count());
            }
        }
    }
}