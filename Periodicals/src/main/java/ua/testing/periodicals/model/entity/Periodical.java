package ua.testing.periodicals.model.entity;

import lombok.*;

import javax.persistence.*;

import static ua.testing.periodicals.model.constants.Constants.PERIODICAL_ID;
import static ua.testing.periodicals.model.constants.Constants.CATEGORY_ID;

/**
 * The Periodical entity.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table( name="periodicals",
        uniqueConstraints={@UniqueConstraint(columnNames={"name"})})
public class Periodical {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = PERIODICAL_ID, nullable = false)
    private Long periodicalId;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = CATEGORY_ID, nullable = false)
    private String categoryId;
    @Column(name = "price", nullable = false)
    private Long price;
}
