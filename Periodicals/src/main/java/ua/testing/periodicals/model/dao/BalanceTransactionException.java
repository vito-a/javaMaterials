package ua.testing.periodicals.model.dao;

/**
 * The Balance transaction exception.
 */
public class BalanceTransactionException extends Exception {
    private static final long serialVersionUID = -3128681006635769411L;

    /**
     * Instantiates a new Balance transaction exception.
     *
     * @param message the message
     */
    public BalanceTransactionException(String message) {
        super(message);
    }
}
