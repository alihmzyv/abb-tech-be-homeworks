import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;
import java.util.*;

public class Family implements HumanCreator {

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



    //getters and setters (none for mother and father, since they are final
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
        //to remove Human object at arrIndex + 1
        //create 2 arrays representing children array but the element at arrIndex + 1
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
            System.out.println("There is no child at the index");
            return false;
        }
    }

    public boolean deleteChild(Human human) {
        boolean childRemoved = false;

        for (int i = 0; i < children.length; i++) {
            if(children[i].equals(human)) {
                childRemoved = deleteChild(i - 1); //-1, since deleteChild() deletes child at given index + 1
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
        sb.append(pet == null ? "No pet" : String.format("Pet=%s\n", pet));

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

    @Override
    protected void finalize() throws Throwable {
        System.out.printf("%s object Garbage Collected: %s\n", this.getClass().getName(), this);
    }

    //HumanCreator implementation
    @Override
    public Human bornChild() {
        //The sex of the child is defined casually with the 50%/50% probability.
        Integer sex = new Random().nextInt(2); //0 - for Man, 1 - for Woman, 50/50 probability for each sex

        //create Man or Woman object based on random sex
        Human child = (sex == 0) ? new Man() : new Woman();

        child.setFamily(this); //reference to the current family


        //random name (you need to create a list of names in advance)
        Map<Integer, List<String>> namesMap = new HashMap<>();
        //put male names
        namesMap.put(0, Arrays.asList("Ali", "Farid", "Kanan", "Ramin"));
        //put female names
        namesMap.put(1, Arrays.asList("Farida", "Gunel", "Aytac", "Lala"));

        //set child's name randomly
        child.setName(namesMap.get(sex).get(new Random().nextInt(namesMap.get(sex).size())));

        //set child's surname to his or her father's one
        String surname = father.getSurname();
        child.setSurname(String.format("%s", (sex.equals(0)) ? surname : surname + "a"));

        //set child's IQ to average of those of his or her mother and father
        child.setIq((father.getIq() + mother.getIq()) / 2);

        return child;
    }
}