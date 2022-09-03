package database;

import classes.Family;
import classes.Human;
import classes.Pet;

import java.util.List;
import java.util.Optional;

public class FamilyController {
    private FamilyService familyService;

    public FamilyController(FamilyService familyService) {
        this.familyService = familyService;
    }


    public List<Family> getAllFamilies() {
        return familyService.getAllFamilies();
    }

    public void displayAllFamilies() {
        familyService.displayAllFamilies();
    }

    public List<Family> getAllFamiliesBiggerThan(int count) {
        return familyService.getAllFamiliesBiggerThan(count);
    }

    public List<Family> getAllFamiliesLessThan(int count) {
        return familyService.getAllFamiliesLessThan(count);
    }

    public int countFamiliesWithMemberNumber(int count) {
        return familyService.countFamiliesWithMemberNumber(count);
    }

    public void createNewFamily(Human mother, Human father) {
        familyService.createNewFamily(mother, father);
    }

    public boolean deleteFamilyByIndex(int index) {
        return familyService.deleteFamilyByIndex(index);
    }

    public Optional<Family> bornChild(Family family, String sex) {
        return familyService.bornChild(family, sex);
    }

    public Optional<Family> adoptChild(Family family, Human human) {
        return familyService.adoptChild(family, human);
    }

    public void deleteAllChildrenOlderThan(int age) {
        familyService.deleteAllChildrenOlderThan(age);
    }

    public long count() {
        return familyService.count();
    }

    public Optional<Family> getFamilybyId(int index) {
        return familyService.getFamilyById(index);
    }

    public Optional<List<Pet>> getPets(int index) {
        return familyService.getPets(index);
    }

    public boolean addPet(int index, Pet pet) {
        return familyService.addPet(index, pet);
    }
}
