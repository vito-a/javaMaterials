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

    /**
     * Instantiates a new Subscription.
     */
    public Subscription() {
    }

    /**
     * Instantiates a new Subscription.
     *
     * @param subId        the sub id
     * @param userId       the user id
     * @param periodicalId the periodical id
     * @param startDate    the start date
     * @param endDate      the end date
     */
    public Subscription(Long subId, Long userId, Long periodicalId, LocalDate startDate, LocalDate endDate) {
        this.subId = subId;
        this.userId = userId;
        this.periodicalId = periodicalId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Gets subscription id.
     *
     * @return the sub id
     */
    public long getSubId() {
        return subId;
    }

    /**
     * Sets subscription id.
     *
     * @param subId the sub id
     */
    public void setSubId(long subId) {
        this.subId = subId;
    }

    /**
     * Gets subscription user id.
     *
     * @return the user id
     */
    public long getUserId() {
        return userId;
    }

    /**
     * Sets subscription user id.
     *
     * @param userId the user id
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

    /**
     * Gets subscription periodical id.
     *
     * @return the periodical id
     */
    public long getPeriodicalId() {
        return periodicalId;
    }

    /**
     * Sets subscription periodical id.
     *
     * @param periodicalId the periodical id
     */
    public void setPeriodicalId(long periodicalId) {
        this.periodicalId = periodicalId;
    }

    /**
     * Gets subscription start date.
     *
     * @return the start date
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Sets subscription start date.
     *
     * @param startDate the start date
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets subscription end date.
     *
     * @return the end date
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Sets subscription end date.
     *
     * @param endDate the end date
     */
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
