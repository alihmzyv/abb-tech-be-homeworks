package apps.console_app.exceptions;

public class IncorrectMenuItem extends IncorrectInputException {
    public IncorrectMenuItem() {
        super();
    }

    public IncorrectMenuItem(String message) {
        super(message);
    }
}
