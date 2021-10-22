package ua.testing.periodicals.model.dao;

/**
 * The type Db exception.
 */
public class DBException extends Exception {

    /**
     * Instantiates a new Db exception.
     *
     * @param message the exception message
     * @param cause   the exception cause
     */
    public DBException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Db exception.
     *
     * @param message the message
     */
    public DBException(String message) {
        super(message);
    }
}
