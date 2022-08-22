package databaseClasses;

import Classes.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class FamilyService {
    final private FamilyDao familyDao;

    public FamilyService(FamilyDao familyDao) {
        this.familyDao = familyDao;
    }


    //methods
    public List<Family> getAllFamilies() {
        //returns the list of all the families
        return familyDao.getAllFamilies();
    }

    public void displayAllFamilies() {
        //prints the data about all families
        int[] i = {0};
        familyDao.getAllFamilies()
                .forEach(family -> System.out.printf("Family %d:\n%s\n\n", ++i[0], family));
    }

    public List<Family> getAllFamiliesBiggerThan(int count) {
        // prints the info about families whose number of members are greater than count parameter
        // and returns the list of those families
        List<Family> familiesBigger = familyDao.getAllFamilies().stream()
                .filter(family -> family.countFamily() > count)
                .collect(Collectors.toList()); //may return unmodifiable list

//        display families bigger than specified
        int[] i = {0};
        familiesBigger
                .forEach(family -> System.out.printf("Family %d:\n%s\n\n", ++i[0], family));

        return familiesBigger;
    }

    public List<Family> getAllFamiliesLessThan(int count) {
        // prints the info about families whose number of members are less than count parameter
        // and returns the list of those families
        List<Family> familiesLess = familyDao.getAllFamilies().stream()
                .filter(family -> family.countFamily() < count)
                .collect(Collectors.toList()); //may return unmodifiable list

        //display families less than specified
        int[] i = {0};
        familiesLess
                .forEach(family -> System.out.printf("Family %d:\n%s\n\n", ++i[0], family));

        return familiesLess;
    }

    public int countFamiliesWithMemberNumber(int count) {
        //returns the number of families whose number of members are equal to count parameter
        return (int) familyDao.getAllFamilies().stream()
                .filter(family -> family.countFamily() == count)
                .count();
    }

    public void createNewFamily (Human mother, Human father) {
        //creates and adds a new family with given mother and father to database
        familyDao.saveFamily(new Family(mother, father));
    }

    public boolean deleteFamilyByIndex(int index) {
        //deletes the family at index if index is in range and returns true, otherwise false
        return familyDao.deleteFamily(index);
    }

    public Family bornChild(Family family, String sex) {
        // returns null if family is not found in database or sex is not correctly typed and passed
        // otherwise creates new Human with random name based on passed sex and the parents from family found
        // updates the family by adding child
        // returns updated family

        sex = sex.trim().toLowerCase(); //extra spaces, case-insensitivity is allowed
        Family familyFound = familyDao.getFamily(family);

        if (!(sex.equals("masculine") || sex.equals("feminine")) ||
                familyFound == null) {
            return null;
        }
        Human mother = familyFound.getMother();
        Human father = familyFound.getFather();

        //create Classes.Man or Classes.Woman object based on random sex
        Human child = (sex.equals("masculine")) ? new Man() : new Woman();

        child.setFamily(familyFound); //reference to the current family

        //random name
        Map<String, List<String>> namesMap = new HashMap<>();
        //put male names
        namesMap.put("masculine", Arrays.asList("Ali", "Farid", "Kanan", "Ramin"));
        //put female names
        namesMap.put("feminine", Arrays.asList("Farida", "Gunel", "Aytac", "Lala"));

        //set child's name randomly but based on sex
        child.setName(namesMap.get(sex).get(new Random().nextInt(namesMap.get(sex).size())));

        //set child's surname to that of his or her father's
        String surname = father.getSurname();
        child.setSurname(String.format("%s", (sex.equals("masculine")) ? surname : surname + "a")); //add a suffix if girl

        //set birthDate to a legal minimum = younger parent birthdate + 18 years
        LocalDate earlierBirthDate = mother.getBirthDate().compareTo(father.getBirthDate()) > 0
                                                                        ? father.getBirthDate() : mother.getBirthDate();
        child.setBirthDate(earlierBirthDate.plusYears(18).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        //set child's IQ to average of those of his or her mother and father
        child.setIq((father.getIq() + mother.getIq()) / 2); //based on parents data

        familyFound.addChild(child); //update family

        return familyFound;
    }

    public Family adoptChild(Family family, Human human) {
        // if family is not found in database, returns null
        // otherwise updates family found by adding human as child and returns updated family
        Family familyFound = familyDao.getFamily(family);
        if (familyFound == null) {
            return null;
        }
        familyFound.addChild(human);
        return familyFound;
    }

    public void deleteAllChildrenOlderThan(int age) {
        //deletes all the children in database older than given age if it finds any
        familyDao.getAllFamilies().
                forEach(family ->
                        family.getChildren().removeIf(child ->
                                child.getBirthDate().isBefore(LocalDate.now().minusYears(age))));
    }

    public int count() {
        // returns the number of families in database
        return familyDao.getAllFamilies().size();
    }

    public Family getFamilyById(int index) {
        return familyDao.getFamilyByIndex(index);
    }

    public List<Pet> getPets(int index) {
        // returns the pets of family found at given index as unmodifiable list if index is correct
        // otherwise returns null
        Family familyFound = familyDao.getFamilyByIndex(index);
        return familyFound != null ? familyFound.getPet().stream().toList() : null;
    }

    public boolean addPet(int index, Pet pet) {
        // adds the pet to the pets of family found at given index if index is correct and returns true
        // otherwise returns false
        Family familyFound = familyDao.getFamilyByIndex(index);
        if (familyFound == null) {
            return false;
        }
        return familyFound.getPet().add(pet);
    }


}
