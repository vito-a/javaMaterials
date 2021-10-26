package ua.training.model.entity;

import javax.persistence.*;
import java.time.LocalDate;

import static ua.training.model.constants.Constants.USER_ID;
import static ua.training.model.constants.Constants.PERIODICAL_ID;
import static ua.training.model.constants.Constants.SUBSCRIPTION_ID;

/**
 * The Subscription entity.
 */
@Entity
@Table( name="subscriptions",
        uniqueConstraints={@UniqueConstraint(columnNames={"sub_id"})})
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = SUBSCRIPTION_ID, nullable = false)
    private Long subId;
    @Column(name = USER_ID, nullable = false)
    private Long userId;
    @Column(name = PERIODICAL_ID, nullable = false)
    private Long periodicalId;
    @Column(name = "startdate", nullable = false)
    private LocalDate startdate;
    @Column(name = "enddate", nullable = false)
    private LocalDate enddate;

    /**
     * Instantiates a new Subscription.
     *
     * @param userId       the user id
     * @param periodicalId the periodical id
     * @param startDate    the Subscription start date
     * @param endDate      the Subscription end date
     */
    public Subscription(Long userId, Long periodicalId, LocalDate startDate, LocalDate endDate) {
        this.userId = userId;
        this.periodicalId = periodicalId;
        this.startdate = startDate;
        this.enddate = endDate;
    }
}
