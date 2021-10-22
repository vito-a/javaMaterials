package ua.testing.periodicals.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The Balance transaction entity.
 */
@Entity
@Table(name = "balance_transactions" )
public class BalanceTransaction {
    @Id
    @GeneratedValue
    @Column(name = "trans_id", nullable = false)
    private Long id;

    @Column(name = "periodical_id", nullable = false)
    private Long periodicalId;

    @Column(name = "amount", nullable = false)
    private double amount;

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
