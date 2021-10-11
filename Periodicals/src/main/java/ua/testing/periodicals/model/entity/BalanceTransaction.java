package ua.testing.periodicals.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPeriodicalId() {
        return periodicalId;
    }

    public void setPeriodicalId(Long periodicalId) {
        this.periodicalId = periodicalId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
