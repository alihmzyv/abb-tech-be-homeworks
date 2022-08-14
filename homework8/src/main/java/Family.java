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
    private List<Human> children;
    private Set<Pet> pets;


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
        children = new ArrayList<>();
    }

    //constructor #2
    public Family(Human mother, Human father, Set<Pet> pets) {
        this.mother = mother;
        this.father = father;
        this.pets = pets;

        this.mother.setFamily(this);
        this.father.setFamily(this);
        children = new ArrayList<>();
    }



    //getters and setters (none for mother and father, since they are final
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
        return pets;
    }

    public void setPet(Set<Pet> pets) {
        this.pets = pets;
    }


    //methods
    public boolean addChild(Human human) {
        children.add(human);
        human.setFamily(this); //sets the families of both the parameter object and element of field array children
        return true; //added
    }

    public boolean deleteChild(int arrIndex) { //removes the child at the following index
        //to remove Human object at arrIndex + 1
        //create 2 arrays representing children array but the element at arrIndex + 1
        //then join both arrays and assign it to children array variable

        try {
            children.get(arrIndex + 1).setFamily(null);
            children.remove(arrIndex + 1);
            return true;
        }
        catch (IndexOutOfBoundsException exc) { //only exception that can be thrown
            System.out.println("There is no child at the index");
            return false;
        }
    }

    public boolean deleteChild(Human human) {
        if (children.indexOf(human) >= 0 && children.indexOf(human) < children.size()) {
            children.get(children.indexOf(human)).setFamily(null);
            return children.remove(human);
        }
        return false;
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
        sb.append(String.format("Children:\n%s\n", children));

        //append info about pet
        sb.append(String.format("Pets=%s\n", pets));

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Family family = (Family) o;
        return Objects.equals(mother, family.mother) && Objects.equals(father, family.father)
                && Objects.equals(children, family.children) && Objects.equals(pets, family.pets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mother, father, children, pets);
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
        child.setSurname(String.format("%s", (sex == 0) ? surname : surname + "a"));

        //set child's IQ to average of those of his or her mother and father
        child.setIq((father.getIq() + mother.getIq()) / 2);
        children.add(child); //add child to children list

        return child;
    }
}