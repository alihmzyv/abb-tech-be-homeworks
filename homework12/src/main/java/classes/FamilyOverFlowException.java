package classes;

public class FamilyOverFlowException extends RuntimeException {
    //thrown when the number of family members is maximum and child was tried to be added
    public FamilyOverFlowException() {
        super();
    }

    public FamilyOverFlowException(String message) {
        super(message);
    }
}
