package ua.testing.periodicals.model.entity;

import lombok.*;

import javax.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static ua.testing.periodicals.model.constants.Constants.USER_ID;

/**
 * The User entity.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table( name="users",
        uniqueConstraints={@UniqueConstraint(columnNames={"email"})})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = USER_ID, nullable = false)
    private Long userId;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "fullname")
    private String fullName;
    @Column(name = "firstname", nullable = false)
    private String firstName;
    @Column(name = "lastname", nullable = false)
    private String lastName;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "enabled", nullable = false)
    private boolean enabled;
    @Column(name = "account_non_locked")
    private boolean accountNonLocked;
    @Column(name = "failed_attempt")
    private int failedAttempt;
    @Column(name = "lock_time")
    private Date lockTime;
    @Column(name = "balance", nullable = false)
    private Double balance;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", nullable=false, insertable=false)
    )
    private Set<Role> roles = new HashSet<>();
    // Overriding toString() method of String class

    @Override
    public String toString() {
        return "User{" +
                "id=" + userId +
                ", username='" + username + '\'' +
                ", firstname='" + firstName + '\'' +
                ", lastname='" + lastName + '\'' +
                ", email=" + email +
                ", lastUpdate=" + accountNonLocked +
                ", enabled=" + enabled +
                ", balance=" + balance +
                '}';
    }
}
