package database;

import classes.Family;

import java.util.List;
import java.util.Optional;

public interface FamilyDao {
    List<Family> getAllFamilies();
    boolean loadData(List<Family> families);
    boolean setData(List<Family> families);
    Optional<Family> getFamilyByIndex(int index);
    Optional<Family> getFamily(Family family);
    boolean deleteFamily(int index);
    boolean deleteFamily(Family family);
    void saveFamily(Family family);
    boolean isIndex(int index);
}
