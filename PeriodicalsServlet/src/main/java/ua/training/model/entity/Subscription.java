package ua.training.model.entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * The Subscription entity.
 */
public class Subscription {
    private Long subId;
    private Long userId;
    private Long periodicalId;
    private LocalDate startDate;
    private LocalDate endDate;

    public Subscription() {
    }

    public Subscription(Long subId, Long userId, Long periodicalId, LocalDate startDate, LocalDate endDate) {
        this.subId = subId;
        this.userId = userId;
        this.periodicalId = periodicalId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public long getSubId() {
        return subId;
    }

    public void setSubId(long subId) {
        this.subId = subId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getPeriodicalId() {
        return periodicalId;
    }

    public void setPeriodicalId(long periodicalId) {
        this.periodicalId = periodicalId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Subscription {" +
                "subId=" + subId +
                ", userId='" + userId + '\'' +
                ", periodicalId='" + periodicalId + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate=" + endDate +
                '}';
    }
}
