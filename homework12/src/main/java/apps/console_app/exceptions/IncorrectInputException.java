package apps.console_app.exceptions;

public class IncorrectInputException extends Exception {
    public IncorrectInputException() {
        super();
    }

    public IncorrectInputException(String message) {
        super(message);
    }
}
