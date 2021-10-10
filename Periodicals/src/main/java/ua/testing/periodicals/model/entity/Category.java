package ua.testing.periodicals.model.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
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
