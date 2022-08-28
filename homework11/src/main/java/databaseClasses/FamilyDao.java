package databaseClasses;

import Classes.Family;

import java.util.List;
import java.util.Optional;

public interface FamilyDao {
    List<Family> getAllFamilies();
    Optional<Family> getFamilyByIndex(int index);
    Optional<Family> getFamily(Family family);
    boolean deleteFamily(int index);
    boolean deleteFamily(Family family);
    void saveFamily(Family family);
}
