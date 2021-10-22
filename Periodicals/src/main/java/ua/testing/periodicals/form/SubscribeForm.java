package ua.testing.periodicals.form;

/**
 * The Subscribe form.
 */
public class SubscribeForm {
    private Long fromUserId;
    private Long forPeriodicalId;
    private Double amount;

    /**
     * Instantiates a new Subscribe form.
     *
     * @param fromUserId      the from user id
     * @param forPeriodicalId the for periodical id
     * @param amount          the payment amount
     */
    public SubscribeForm(Long fromUserId, Long forPeriodicalId, Double amount) {
        this.fromUserId = fromUserId;
        this.forPeriodicalId = forPeriodicalId;
        this.amount = amount;
    }

    /**
     * Gets the user id current payment is sent from.
     *
     * @return the from user id
     */
    public Long getFromUserId() {
        return fromUserId;
    }

    /**
     * Sets the account id current payment is sent from.
     *
     * @param fromAccountId the from account id
     */
    public void setFromAccountId(Long fromAccountId) {
        this.fromUserId = fromAccountId;
    }

    /**
     * Gets periodical id the current payment is sent for.
     *
     * @return the periodical id
     */
    public Long getforPeriodicalId() {
        return forPeriodicalId;
    }

    /**
     * Sets periodical id the current payment is sent for.
     *
     * @param forPeriodicalId the for periodical id
     */
    public void setforPeriodicalId(Long forPeriodicalId) {
        this.forPeriodicalId = forPeriodicalId;
    }

    /**
     * Gets amount the current payment is sent for.
     *
     * @return the amount
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * Sets amount.
     *
     * @param amount the amount
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
