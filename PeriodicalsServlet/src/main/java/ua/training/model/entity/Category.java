package ua.training.model.entity;

import javax.persistence.*;

/**
 * The Category entity.
 */
@Entity
@Table( name="categories",
        uniqueConstraints={@UniqueConstraint(columnNames={"name"})})
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cat_id", nullable = false)
    private Long catId;
    @Column(name = "name", nullable = false)
    private String name;
}
