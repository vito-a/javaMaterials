package ua.testing.periodicals.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

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
    @Column(name = "sub_id", nullable = false)
    private Long subId;
    @Column(name = "user_id", nullable = false)
    private Long userId;
    @Column(name = "periodical_id", nullable = false)
    private Long periodicalId;
    @Column(name = "startdate", nullable = false)
    private Date startdate;
    @Column(name = "enddate", nullable = false)
    private Date enddate;
}
