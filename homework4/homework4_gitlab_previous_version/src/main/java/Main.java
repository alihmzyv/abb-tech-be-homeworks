public class Main {
    public static void main(String[] args) {
        /*
        * create several families, so that every class uses all the available constructors.
        * Display data about every person.
         */

        //family1
        System.out.println("Family 1:");
        Human human1 = new Human("Lala", "Gafarova", 1981); //Human constructor #1
        System.out.println(human1);
        Human human2 = new Human("Kanan", "Baghirov", 1985);
        System.out.println(human2);
        Human human3 = new Human("Nargiz", "Gafarova", 2010, human1, human2); //Human constructor #2
        System.out.println(human3);
        System.out.println();


        //family2
        /*
        * In the Main class: create a mother, father, child and his pet.
        * Specify all the needed links (for child to his parents and to the pet), in order to form a full family.
        * Call all available methods for the child (including toString() method) and his pet.
         */
        System.out.println("Family 2:");
        Human human4 = new Human(); //Human constructor #4
        System.out.println(human4);
        Human human5 = new Human("Pasha", "Guliyev", 1983);
        System.out.println(human5);
        Pet pet1 = new Pet("Bulldog", "Rock", 2, 52, new String[]{"barking", "sleeping"}); //Pet constructor #2
        System.out.println(pet1);
        pet1.eat();
        pet1.foul();
        pet1.respond();
        Human human6 = new Human("Saadat", "Guliyeva", 2002, 95, pet1,
                                human4, human5, new String[][]{{"Monday", "go to school"}, {"Sunday", "have rest"}}); //Human constructor #3
        System.out.println(human6);
        human6.greetPet();
        human6.describePet();
        human6.feedPet(false);
        System.out.println();


        //family3
        System.out.println("Family 3:");
        Human human7 = new Human();
        System.out.println(human7);
        Human human8 = new Human("Nadir", "Karimov", 1980);
        System.out.println(human8);
        Pet pet2 = new Pet("Haski", "Doggy"); //Pet constructor #1
        Pet pet3 = new Pet(); //Pet constructor #3
        Human human9 = new Human("Gunash", "Karimova", 2001, 80, pet2,
                human7, human8, new String[][]{{"Monday", "go to piano class"}, {"Sunday", "study at home"}});
        System.out.println(human9);
        Human human10 = new Human("Mammad", "Karimov", 2000, 89, pet3,
                human7, human8, new String[][]{{"Monday", "go to cousin's"}, {"Sunday", "go to swim"}});
        System.out.println(human10);
    }
}
