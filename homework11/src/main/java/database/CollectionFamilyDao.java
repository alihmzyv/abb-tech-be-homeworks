package database;


import classes.Family;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CollectionFamilyDao implements FamilyDao {
    final private List<Family> familyData;

    public CollectionFamilyDao(List<Family> familyData) {
        this.familyData = new ArrayList<>(familyData); //get modifiable list in any case
    }

    //implementation of classes.Family Dao
    @Override
    public List<Family> getAllFamilies() {
        //returns the list of all the families
        return familyData;
    }

    @Override
    public Optional<Family> getFamilyByIndex(int index) {
        //returns the Optional of family at index if index is in range, otherwise empty Optional
        if (!isIndex(index)) {
            return Optional.empty();
        }
        return Optional.ofNullable(familyData.get(index));
    }

    @Override
    public boolean deleteFamily(int index) {
        //deletes the family at index if index is in range and returns true, otherwise false
        if (!isIndex(index)) {
            return false;
        }
        familyData.remove(index);
        return true;
    }

    @Override
    public boolean deleteFamily(Family family) {
        //deletes the family if match is found and returns true, otherwise returns false
        return familyData.remove(family);
    }

    @Override
    public void saveFamily(Family family) {
        //if family found in the list, update the family with the given family, otherwise add to the end of list
        int index = familyData.indexOf(family);
        if (isIndex(index)) {
            familyData.set(index, family);
            return;
        }
        familyData.add(family);
    }

    @Override
    public Optional<Family> getFamily(Family family) {
        //if family is found in list, returns the Optional of the family found first, otherwise empty Optional;
        int index = familyData.indexOf(family);
        if (!isIndex(index)) {
            return Optional.empty();
        }
        return Optional.ofNullable(familyData.get(index));
    }

    private boolean isIndex(int index) {
        return !(familyData.isEmpty() || index < 0 || index >= familyData.size());
    }
}
