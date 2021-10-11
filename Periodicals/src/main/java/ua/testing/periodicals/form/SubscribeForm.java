package ua.testing.periodicals.form;

public class SubscribeForm {
    private Long fromUserId;
    private Long forPeriodicalId;
    private Double amount;

    public SubscribeForm(Long fromUserId, Long forPeriodicalId, Double amount) {
        this.fromUserId = fromUserId;
        this.forPeriodicalId = forPeriodicalId;
        this.amount = amount;
    }

    public Long getFromUserId() {
        return fromUserId;
    }

    public void setFromAccountId(Long fromAccountId) {
        this.fromUserId = fromAccountId;
    }

    public Long getforPeriodicalId() {
        return forPeriodicalId;
    }

    public void setforPeriodicalId(Long forPeriodicalId) {
        this.forPeriodicalId = forPeriodicalId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
