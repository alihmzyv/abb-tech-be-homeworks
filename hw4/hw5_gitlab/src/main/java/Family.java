import java.util.Arrays;
import java.util.Objects;

public class Family {

    static {
        System.out.println("Family class is being loaded..");
    }

    //non-static
    {
        System.out.println("A Family type object is created");
    }

    final private Human mother;
    final private Human father;
    private Human[] children;
    private Pet pet;


    //constructors: at least mother and father should be given, children array is created empty
    //constructor #1
    public Family(Human mother, Human father) {
        /*
        The only condition for creating a new family is the presence of two parents,
        with the parents being referred to the current new family and
        the family being created with an empty array of children.
         */
        this.mother = mother;
        this.father = father;

        //sets the family fields of
        //both the Human objects passed as parameters to constructor and instance variable parents
        //to this family object
        this.mother.setFamily(this);
        this.father.setFamily(this);
        children = new Human[0]; //initialized the children array to an empty array
        //empty array def. from oracle: https://docs.oracle.com/javase/specs/jls/se7/html/jls-10.html
        /*
        An array object contains a number of variables.
        The number of variables may be zero, in which case the array is said to be empty.

         */
    }

    //constructor #2
    public Family(Human mother, Human father, Pet pet) {
        this.mother = mother;
        this.father = father;
        this.pet = pet;

        this.mother.setFamily(this);
        this.father.setFamily(this);
        children = new Human[0];
    }



    //getters and setters (none for mother and father, since they should be final
    public Human getMother() {
        return mother;
    }

    public Human getFather() {
        return father;
    }

    public Human[] getChildren() {
        return children;
    }

    public void setChildren(Human[] children) {
        this.children = children;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }


    //methods
    public boolean addChild(Human human) {
        children = Arrays.copyOf(children, children.length + 1); //increase length by 1
        children[children.length-1] = human; //add the child
        children[children.length-1].setFamily(this); //sets the families of both the parameter object and element of field array children
        return true; //added
    }

    public boolean deleteChild(int arrIndex) { //removes the child at the following index
        //create 2 arrays representing children array but the element at given index + 1
        //then join both arrays and assign it to children array variable

        try {
            children[arrIndex + 1].setFamily(null); //removes the child's link to the family (+ the link of the object that was added as a child, which is of the importance here)
            Human[] arr1 = Arrays.copyOfRange(children, 0, arrIndex + 1); //till element at index + 1
            Human[] arr2 = Arrays.copyOfRange(children, arrIndex + 2, children.length); //after element at index + 1
            Human[] arr3 = new Human[children.length - 1];
            System.arraycopy(arr1, 0, arr3, 0, arr1.length);
            System.arraycopy(arr2, 0, arr3, arrIndex + 1, arr2.length);
            children = arr3;
            return true;
        }
        catch (ArrayIndexOutOfBoundsException exc) { //only exception that can be thrown
            System.out.println(exc);
            return false;
        }
    }

    public boolean deleteChild(Human human) {
        boolean childRemoved = false;

        for (int i = 0; i < children.length; i++) {
            if(children[i].equals(human)) {
                childRemoved = deleteChild(i - 1);
            }
        }

        return childRemoved;
    }

    public int countFamily() {
        return children.length + 2;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        //append info about parents
        sb.append(String.format("Mother=%s\nFather=%s\n", mother.toString(), father.toString()));

        //append info about each children
        for (int i = 0; i < children.length; i++) {
            sb.append(String.format("Child %d=%s\n", i, children[i].toString()));
        }

        //append info about pet
        sb.append(pet == null ? "No pet" : String.format("Pet=%s\n", pet.toString()));

        return sb.toString();
    }

    //for equality: all the fields are taken into account
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Family family = (Family) o;
        return mother.equals(family.mother) && father.equals(family.father) && Arrays.equals(children, family.children) && Objects.equals(pet, family.pet);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(mother, father, pet);
        result = 31 * result + Arrays.hashCode(children);
        return result;
    }
}