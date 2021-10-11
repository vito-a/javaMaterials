package ua.testing.periodicals.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

import static ua.testing.periodicals.model.constants.Constants.USER_ID;
import static ua.testing.periodicals.model.constants.Constants.PERIODICAL_ID;
import static ua.testing.periodicals.model.constants.Constants.SUBSCRIPTION_ID;

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
    private Date startdate;
    @Column(name = "enddate", nullable = false)
    private Date enddate;
}