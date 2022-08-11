public interface NastyThings {
    //not every animal can do nasty things
    //except fish, the rest can do it
    default void foul() {
        System.out.println("I need to cover it up");
    }
}
