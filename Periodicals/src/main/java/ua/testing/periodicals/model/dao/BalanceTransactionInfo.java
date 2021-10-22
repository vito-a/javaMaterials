package ua.testing.periodicals.model.dao;

/**
 * The Balance transaction info.
 */
public class BalanceTransactionInfo {
    private Long id;
    private Long periodicalId;
    private double amount;

    /**
     * Instantiates a new Balance transaction info.
     *
     * Used in JPA query
     *
     * @param id           the id
     * @param periodicalId the periodical id
     * @param amount       the amount
     *
     */
    public BalanceTransactionInfo(Long id, Long periodicalId, double amount) {
        this.id = id;
        this.periodicalId = periodicalId;
        this.amount = amount;
    }

    /**
     * Gets id.
     *
     * @return the Balance transaction id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the Balance transaction id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets periodical id.
     *
     * @return the periodical id
     */
    public Long getPeriodicalId() {
        return periodicalId;
    }

    /**
     * Sets periodical id.
     *
     * @param periodicalId the periodical id
     */
    public void setPeriodicalId(Long periodicalId) {
        this.periodicalId = periodicalId;
    }

    /**
     * Gets amount.
     *
     * @return the Balance transaction amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Sets amount.
     *
     * @param amount the Balance transaction amount
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }
}
