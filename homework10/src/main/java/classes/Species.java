package classes;

public enum Species {
        DOG(false, 4, true),
        BIRD(true, 2, true),
        FISH(false, 0, false),
        ROBO_CAT(false, 4, false),
        DOMESTIC_CAT(false, 4, true),
        UNKNOWN(false, 0, false);


        public final boolean canFly; //except canFly, features can be altered
        public int numberOfLegs;
        public boolean hasFur;

        Species(boolean canFly, int numberOfLegs, boolean hasFur) {
                this.canFly = canFly;
                this.numberOfLegs = numberOfLegs;
                this.hasFur = hasFur;
        }
}
