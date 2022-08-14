import java.util.*;

public class Family implements HumanCreator {

    //fields
    final private Human mother;
    final private Human father;
    private List<Human> children = new ArrayList<>(); //initialized as an empty list as required before (refer to hw 5)
    private Set<Pet> pet;


    //static block
    static {
        System.out.println("Family class is being loaded..");
    }

    //non-static block
    {
        System.out.println("A Family type object is created");
    }


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
    }

    //constructor #2
    public Family(Human mother, Human father, Set<Pet> pet) {
        this.mother = mother;
        this.father = father;
        this.pet = pet;

        this.mother.setFamily(this);
        this.father.setFamily(this);
    }



    //getters and setters (none (setter) for mother and father, since they are final)
    public Human getMother() {
        return mother;
    }

    public Human getFather() {
        return father;
    }

    public List<Human> getChildren() {
        return children;
    }

    public void setChildren(List<Human> children) {
        this.children = children;
    }

    public Set<Pet> getPet() {
        return pet;
    }

    public void setPet(Set<Pet> pet) {
        this.pet = pet;
    }


    //methods
    public boolean addChild(Human human) {
        children.add(human); //add the child
        human.setFamily(this); //sets the families of both the parameter object and element of field array children
        return true; //added
    }

    public boolean deleteChild(int arrIndex) {
        //removes the child at the given index
        //in previous homeworks, i defined this method to delete child at the following (index + 1) index.
        //turns out it was a translation mistake
        //compare:
        // translated version:
        // https://gitlab.com/dan-it/az-groups/az-be5/-/tree/main/en/homework5
        //original russian version, which states "given", instead of "following":
        //https://gitlab.com/dan-it/az-groups/az-be5/-/tree/main/ru/homework5
        try {
            children.get(arrIndex).setFamily(null); //removes the child's link to the family (+ the link of the object that was added as a child, which is of the importance here)
            children.remove(arrIndex);
            return true;
        }
        catch (IndexOutOfBoundsException exc) { //only exception that can be thrown
            System.out.println("There is no child at the given index");
            return false;
        }
    }

    public boolean deleteChild(Human human) {
        int indexOfChild = children.indexOf(human);
        if (indexOfChild != -1) {
            return deleteChild(indexOfChild);
        }
        else {
            return false;
        }
    }

    public int countFamily() {
        return children.size() + 2;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        //append info about parents
        sb.append(String.format("Mother=%s\nFather=%s\n", mother, father));

        //append info about each children
        for (int i = 0; i < children.size(); i++) {
            sb.append(String.format("Child %d=%s\n", i + 1, children.get(i)));
        }

        //append info about pets
        if (pet != null) {
            Iterator<Pet> petIterator = pet.iterator();
            for (int i = 0; i < pet.size(); i++) {
                sb.append(String.format("Pet %d=%s\n", i + 1, petIterator.next()));
            }
        }

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Family family = (Family) o;
        return Objects.equals(mother, family.mother) && Objects.equals(father, family.father) && Objects.equals(children, family.children) && Objects.equals(pet, family.pet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mother, father, children, pet);
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.printf("%s object Garbage Collected: %s\n", this.getClass().getName(), this);
    }

    //HumanCreator implementation
    @Override
    public Human bornChild() {
        //The sex of the child is defined casually with the 50%/50% probability.
        int sex = new Random().nextInt(2); //0 - for Man, 1 - for Woman, 50/50 probability for each sex

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
        child.setSurname(String.format("%s", (sex == 0) ? surname : surname + "a")); //a suffix addition to female surnames

        //set child's IQ to average of those of his or her mother and father
        child.setIq((father.getIq() + mother.getIq()) / 2);

        this.addChild(child);
        return child;
    }
}