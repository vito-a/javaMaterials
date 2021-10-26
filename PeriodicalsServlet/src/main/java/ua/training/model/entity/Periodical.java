package ua.training.model.entity;

import javax.persistence.*;

import static ua.training.model.constants.Constants.PERIODICAL_ID;
import static ua.training.model.constants.Constants.CATEGORY_ID;

/**
 * The Periodical entity.
 */
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
