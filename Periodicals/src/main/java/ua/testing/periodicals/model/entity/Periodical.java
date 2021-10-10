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
@Table( name="periodicals",
        uniqueConstraints={@UniqueConstraint(columnNames={"name"})})
public class Periodical {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "periodical_id", nullable = false)
    private Long periodical_id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "category_id", nullable = false)
    private String category_id;
    @Column(name = "price", nullable = false)
    private Long price;
}
