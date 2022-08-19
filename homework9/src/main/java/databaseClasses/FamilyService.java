package databaseClasses;

import Classes.*;

import java.util.*;
import java.util.stream.Collectors;

public class FamilyService {
    private FamilyDao familyDao;

    public FamilyService(FamilyDao familyDao) {
        this.familyDao = familyDao;
    }


    //methods
    public List<Family> getAllFamilies() {
        return familyDao.getAllFamilies();
    }

    public void displayAllFamilies() {
        int[] i = {0};
        familyDao.getAllFamilies()
                .forEach(family -> System.out.printf("Family %d:\n%s\n\n", ++i[0], family));
    }

    public List<Family> getAllFamiliesBiggerThan(int count) {
        //get families bigger than specified to return
        List<Family> familiesBigger = familyDao.getAllFamilies().stream()
                .filter(family -> family.countFamily() > count)
                .collect(Collectors.toCollection(ArrayList<Family>::new));

        //display families bigger than specified
        int[] i = {0};
        familiesBigger
                .forEach(family -> System.out.printf("Family %d:\n%s\n\n", ++i[0], family));

        return familiesBigger;
    }

    public List<Family> getAllFamiliesLessThan(int count) {
        //get families less than specified to return
        List<Family> familiesLess = familyDao.getAllFamilies().stream()
                .filter(family -> family.countFamily() < count)
                .collect(Collectors.toCollection(ArrayList<Family>::new));

        //display families less than specified
        int[] i = {0};
        familiesLess
                .forEach(family -> System.out.printf("Family %d:\n%s\n\n", ++i[0], family));

        return familiesLess;
    }

    public long countFamiliesWithMemberNumber(long count) {
        return familyDao.getAllFamilies().stream()
                .filter(family -> family.countFamily() == count)
                .count();
    }

    public void createNewFamily (Human mother, Human father) {
        familyDao.saveFamily(new Family(mother, father));
    }

    public boolean deleteFamilyByIndex(int index) {
        return familyDao.deleteFamily(index);
    }

    public Family bornChild(Family family, String sex) {
        Family familyFound = familyDao.getFamily(family);
        if (!(sex.equals("masculine") || sex.equals("feminine")) ||
                familyFound == null) {
            return null;
        }


        //create Classes.Man or Classes.Woman object based on random sex
        Human child = (sex.equals("masculine")) ? new Man() : new Woman();

        child.setFamily(familyFound); //reference to the current family

        //random name (you need to create a list of names in advance)
        Map<String, List<String>> namesMap = new HashMap<>();
        //put male names
        namesMap.put("masculine", Arrays.asList("Ali", "Farid", "Kanan", "Ramin"));
        //put female names
        namesMap.put("feminine", Arrays.asList("Farida", "Gunel", "Aytac", "Lala"));

        //set child's name randomly
        child.setName(namesMap.get(sex).get(new Random().nextInt(namesMap.get(sex).size())));

        //set child's surname to his or her father's one
        String surname = familyFound.getFather().getSurname();
        child.setSurname(String.format("%s", (sex.equals("masculine")) ? surname : surname + "a")); //a suffix addition to female surnames

        //set child's IQ to average of those of his or her mother and father
        child.setIq((familyFound.getFather().getIq() + familyFound.getMother().getIq()) / 2);

        familyFound.addChild(child);

        return familyFound;
    }

    public Family adoptChild(Family family, Human human) {
        Family familyFound = familyDao.getFamily(family);
        if (familyFound == null) {
            return null;
        }
        familyFound.addChild(human);
        return familyFound;
    }

    public void deleteAllChildrenOlderThan(int age) {
        familyDao.getAllFamilies().
                forEach(family ->
                        family.getChildren().removeIf(child -> child.getYear() < Calendar.getInstance().get(Calendar.YEAR) + 1900 - age));
    }

    public long count() {
        return familyDao.getAllFamilies().size();
    }

    public Family getFamilybyId(int index) {
        return familyDao.getFamilyByIndex(index);
    }

    public List<Pet> getPets(int index) {
        Family familyFound = familyDao.getFamilyByIndex(index);
        return familyFound != null ? familyFound.getPet().stream().toList() : null;
    }

    public boolean addPet(int index, Pet pet) {
        Family familyFound = familyDao.getFamilyByIndex(index);
        if (familyFound == null) {
            return false;
        }
        return familyFound.getPet().add(pet);
    }


}
