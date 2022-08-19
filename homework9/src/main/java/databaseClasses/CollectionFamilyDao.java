package databaseClasses;


import Classes.Family;

import java.util.List;

public class CollectionFamilyDao implements FamilyDao {
    private List<Family> familyData;

    public CollectionFamilyDao(List<Family> familyData) {
        this.familyData = familyData;
    }

    //implementation of Classes.Family Dao
    @Override
    public List<Family> getAllFamilies() {
        //returns the list of all the families
        return familyData;
    }

    @Override
    public Family getFamilyByIndex(int index) {
        //returns the family at index if index is in range, otherwise null
        if (!isIndex(index)) {
            return null;
        }
        return familyData.get(index);
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
    public Family getFamily(Family family) {
        //if family is found in list, returns the family found, otherwise null;
        int index = familyData.indexOf(family);
        if (!isIndex(index)) {
            return null;
        }
        return familyData.get(index);
    }

    private boolean isIndex(int index) {
        return !(familyData.isEmpty() || index < 0 || index >= familyData.size());
    }
}
