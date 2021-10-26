package ua.training.model.entity;

import javax.persistence.*;

/**
 * The Role entity.
 */
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;

    /**
     * Gets id.
     *
     * @return the role id
     */
    public Integer getId() {
        return id;
    }
}