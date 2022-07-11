public class Main {
    public static void main(String[] args) {
        //create several families, so that every class uses all the available constructors.
        //family1
        Human human1 = new Human("Ali", "Mammadov", 1965); //Human constructor #1
        Human human2 = new Human(); //Human constructor #4
        Human human3 = new Human("Mammad", "Mammadov", 1975, human2, human1); //Human constructor #2
        Human human4 = new Human("Nargiz", "Mammadova", 1973);

        //family2
        Pet pet1 = new Pet("Choban iti", "Alabash"); //Pet constructor #1
        //human3
        //human4
        Human human5 = new Human("Namig", "Mammadov", 1999, 100, pet1, human4, human3,
                new String[][]{{"Monday", "Study java"}}); //Human constructor 3


        //family3
        Pet pet2 = new Pet("Pitbull", "Rock",2, 33, new String[]{"barking"}); //Pet constructor #2
        Pet pet3 = new Pet(); //Pet constructor #3
        Human human6 = new Human("Parviz", "Cavadov", 1980, 101, pet2, human2, human1,
                new String[][]{{"Monday", "go to work"}});
        Human human7 = new Human("Jala", "Cavadova", 1981, 103, pet3, human2, human1,
                new String[][]{{"Monday", "go to swim"}});




        //Display data about every person.
        Human[] humanArr = {human1, human2, human3, human4, human5, human6, human7};
        for(var human: humanArr) {
            System.out.println(human);
        }


        /*
        create a mother, father, child and his pet. Specify all the needed links (for child to his parents and to the pet),
        in order to form a full family. Call all available methods for the child (including  toString() method) and his pet.
         */
        //family4
        //human6
        //human7
        Pet pet4 = new Pet("Bulldog", "Rocky",3, 77, new String[]{"sleeping"});
        Human human8 = new Human("Vali", "Cavadov", 2000, 99, pet4, human7, human6,
                new String[][]{{"Monday", "go to swim"}});

        //methods for child
        human8.greetPet();
        human8.describePet();
        System.out.println(human8); //calls toString() method implicitly

        //methods for pet
        pet4.eat();
        pet4.respond();
        pet4.foul();
    }
}