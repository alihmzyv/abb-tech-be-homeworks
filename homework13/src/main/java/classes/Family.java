package classes;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class Family implements HumanCreator, Serializable {
    @Serial
    private static final long serialVersionUID = -4513371079516044524L;
    //fields
    private static int maxMember = 10; //default = 10, can use setMaxMember to change
    final private Human mother;
    final private Human father;
    private List<Human> children = new ArrayList<>(); //initialized as an empty list as required (refer to hw 5)
    private Set<Pet> pet;


    //static block
    static {
        System.out.println("classes.Family class is being loaded..");
    }

    //non-static block
    {
        System.out.println("A classes.Family type object is created");
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
        //both the classes.Human objects passed as parameters to constructor and instance variable parents
        //to this family object
        this.mother.setFamily(this);
        this.father.setFamily(this);
    }

    //constructor #2
    public Family(Human mother, Human father, Set<Pet> pet) {
        this.mother = mother;
        this.father = father;
        this.pet = new HashSet<>(pet); //get modifiable set

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

    public Optional<Set<Pet>> getPet() {
        return Optional.ofNullable(pet);
    }

    public void setPet(Set<Pet> pet) {
        this.pet = pet;
    }

    public static void setMaxMember(int maxMember) {
        Family.maxMember = maxMember;
    }

    //methods
    public boolean addChild(Human human) {
        if (this.countFamily() == maxMember) {
            throw new FamilyOverFlowException(String.format("Family cannot have more members than %d.\n" +
                    "Did not add the child.", maxMember));
        }
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


    //classes.HumanCreator implementation
    @Override
    public Human bornChild() {
        //The sex of the child is defined casually with the 50%/50% probability.
        int sex = new Random().nextInt(2); //0 - for classes.Man, 1 - for classes.Woman, 50/50 probability for each sex

        //create classes.Man or classes.Woman object based on random sex
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
        String surname = father.getSurname().orElse("XXX");
        child.setSurname(surname);
        if (!surname.equals("XXX") && child instanceof Woman) {
            child.setSurname(surname + "a"); //a suffix addition to female surnames
        }

        //set child's IQ to average of those of his or her mother and father
        child.setIq((father.getIq() + mother.getIq()) / 2);

        this.addChild(child);
        return child;
    }

    public String prettyFormat() {
        //returns a String representing the family in the a concise format
        StringBuilder sb = new StringBuilder();
        sb.append("family: ");

        //append info about parents
        //substring of Humans with beginIndex = 5 in order to get rid of "Human" word
        sb.append(String.format("\n\t\tmother: %s", mother.toString().substring(5)));
        sb.append(String.format("\n\t\tfather: %s", father.toString().substring(5)));
        sb.append("\n");

        //append info about children
        sb.append("\t\tchildren: ");
        children.forEach(child ->
                sb.append(String.format("\n\t\t\t\t%s", child.prettyFormat())));
        if (children.isEmpty()) {
            sb.append("No children");
        }
        sb.append("\n");

        //append info about pets
        sb.append("\t\tpets: ");
        getPet().map(pets ->
                pets.stream()
                        .map(Pet::prettyFormat).collect(Collectors.toSet()))
                .ifPresentOrElse(sb::append, () -> sb.append("No pets"));
        sb.append("\n");

        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        //append info about parents
        sb.append(String.format("Mother=%s\nFather=%s\n", mother, father));

        //append info about each children
        int[] i = {0};
        children.forEach(child -> sb.append(String.format("Child %d=%s\n", ++i[0], child)));

        //append info about pets
        i[0] = 0;
        getPet().ifPresent(pets -> pets
                .forEach(pet -> sb.append(String.format("Pet %d=%s\n", ++i[0], pet))));

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Family family = (Family) o;
        return Objects.equals(mother, family.mother) && Objects.equals(father, family.father) &&
                Objects.equals(children, family.children) && Objects.equals(pet, family.pet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mother, father, children, pet);
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.printf("%s object Garbage Collected: %s\n", this.getClass().getName(), this);
    }
}