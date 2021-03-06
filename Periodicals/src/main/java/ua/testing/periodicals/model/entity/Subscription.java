package ua.testing.periodicals.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

import static ua.testing.periodicals.model.constants.Constants.USER_ID;
import static ua.testing.periodicals.model.constants.Constants.PERIODICAL_ID;
import static ua.testing.periodicals.model.constants.Constants.SUBSCRIPTION_ID;

/**
 * The Subscription entity.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
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

    @Override
    public String toString() {
        return "Subscription {" +
                "subId=" + subId +
                ", userId='" + userId + '\'' +
                ", periodicalId='" + periodicalId + '\'' +
                ", startDate='" + startdate + '\'' +
                ", endDate=" + enddate +
                '}';
    }
}
