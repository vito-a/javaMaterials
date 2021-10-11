package ua.testing.periodicals.model.dao;

public class BalanceTransactionInfo {
    private Long id;
    private Long periodicalId;
    private double amount;

    // Used in JPA query.
    public BalanceTransactionInfo(Long id, Long periodicalId, double amount) {
        this.id = id;
        this.periodicalId = periodicalId;
        this.amount = amount;
    }

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
