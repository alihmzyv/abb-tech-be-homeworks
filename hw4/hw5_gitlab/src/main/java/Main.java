import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Human human1 = new Human("Ali", "Hamzayev", 1999, 100, new String[][]{{"Monday", "go to school"}});
        Human human2 = new Human("Ali", "Hamzayev", 1999, 100, new String[][]{{"Monday", "go to school"}});
        System.out.println(human2.hashCode());
    }
}
