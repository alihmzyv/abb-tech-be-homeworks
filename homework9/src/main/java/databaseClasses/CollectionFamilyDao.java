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
        return familyData;
    }

    @Override
    public Family getFamilyByIndex(int index) {
        if (!isIndex(index)) {
            return null;
        }
        return familyData.get(index);
    }

    @Override
    public boolean deleteFamily(int index) {
        if (!isIndex(index)) {
            return false;
        }
        familyData.remove(index);
        return true;
    }

    @Override
    public boolean deleteFamily(Family family) {
        if (familyData.contains(family)) {
            familyData.remove(family);
            return true;
        }
        return false;
    }

    @Override
    public void saveFamily(Family family) {
        int index = familyData.indexOf(family);
        if (isIndex(index)) {
            familyData.set(index, family);
            return;
        }
        familyData.add(family);
    }

    @Override
    public Family getFamily(Family family) {
        int index = familyData.indexOf(family);
        if (index == - 1) {
            return null;
        }
        return familyData.get(index);
    }

    private boolean isIndex(int index) {
        return familyData.isEmpty() || index < 0 || index >= familyData.size();
    }
}
