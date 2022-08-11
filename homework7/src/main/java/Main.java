public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 200_000; i++) {
            //object reference not assigned to any variable, thus will be probably ready to be garbage collected
            new Human("Ali", "Hamzayev", 2001, 100, new String[][]{{DayOfWeek.MONDAY.name(), "study Java"}, {DayOfWeek.SUNDAY.name(), "go to campus"}});
        }
    }
}

