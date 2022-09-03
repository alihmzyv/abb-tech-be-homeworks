package apps.console_app.exceptions;

public class EmptyDatabaseException extends FamilyDaoException {
    //thrown when the database is not initialized or it is just empty
    public EmptyDatabaseException() {
        super();
    }

    public EmptyDatabaseException(String message) {
        super(message);
    }
}
