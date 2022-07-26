public enum Species {
        CAT(false, 4, true),
        DOG(false, 4, true),
        BIRD(true, 2, true),
        FISH(false, 0, false);

        final boolean canFly; //except canFly, features can be altered
        int numberOfLegs;
        boolean hasFur;
        private
        Species(boolean canFly, int numberOfLegs, boolean hasFur) {
                this.canFly = canFly;
                this.numberOfLegs = numberOfLegs;
                this.hasFur = hasFur;
        }
}
