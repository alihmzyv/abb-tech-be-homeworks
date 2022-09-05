package database;

import classes.Family;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FileFamilyDao implements FamilyDao {

    final private File familyData;

    public FileFamilyDao(File familyData) {
        this.familyData = familyData;
    }

    @Override
    public boolean setData(List<Family> families) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(familyData))) {
            oos.writeObject(families);
            return true;
        }
        catch (FileNotFoundException exc) {
            System.out.println("Could not find familyData file.");
        }
        catch (IOException exc) {
            System.out.println("Could not read familyData file.");
        }
        return false;
    }

    @Override
    public List<Family> getAllFamilies() {
        //returns the list of all the families
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(familyData))) {
            return (List<Family>) ois.readObject();
        }
        catch (FileNotFoundException exc) {
            System.out.println("Could not find familyData file.");
        }
        catch (IOException exc) {
            System.out.println("Could not read familyData file.");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not deserialize families.");
        }
        return new ArrayList<>();
    }

    @Override
    public boolean loadData(List<Family> families) {
        List<Family> updatedData = getAllFamilies();
        updatedData.addAll(families);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(familyData))) {
            oos.writeObject(updatedData);
            return true;
        }
        catch (FileNotFoundException exc) {
            System.out.println("Could not find familyData file.");
        }
        catch (IOException exc) {
            System.out.println("Could not read familyData file.");
        }
        return false;
    }

    @Override
    public Optional<Family> getFamilyByIndex(int index) {
        //returns the Optional of family at index if index is in range, otherwise empty Optional
        if (!isIndex(index)) {
            return Optional.empty();
        }
        return Optional.ofNullable(getAllFamilies().get(index));
    }

    @Override
    public Optional<Family> getFamily(Family family) {
        //if family is found in list, returns the Optional of the family found first, otherwise empty Optional;
        int index = getAllFamilies().indexOf(family);
        if (!isIndex(index)) {
            return Optional.empty();
        }
        return Optional.ofNullable(getAllFamilies().get(index));
    }

    @Override
    public boolean deleteFamily(int index) {
        //deletes the family at index if index is in range and returns true, otherwise false
        if (!isIndex(index)) {
            return false;
        }
        List<Family> updatedData = getAllFamilies();
        updatedData.remove(index);
        return setData(updatedData);
    }

    @Override
    public boolean deleteFamily(Family family) {
        //deletes the family if match is found and returns true, otherwise returns false
        List<Family> updatedData = getAllFamilies();
        return (updatedData.remove(family) || setData(updatedData));
    }

    @Override
    public void saveFamily(Family family) {
        //if family found in the list, update the family with the given family, otherwise add to the end of list
        List<Family> updatedData = getAllFamilies();
        int index = updatedData.indexOf(family);
        if (isIndex(index)) {
            updatedData.set(index, family);
            setData(updatedData);
            return;
        }
        updatedData.add(family);
        setData(updatedData);
    }

    @Override
    public boolean isIndex(int index) {
        return !(getAllFamilies().isEmpty() || index < 0 || index >= getAllFamilies().size());
    }
}
