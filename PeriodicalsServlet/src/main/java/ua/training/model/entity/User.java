package ua.training.model.entity;

import javax.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.io.Serializable;

import static ua.training.model.constants.Constants.USER_ID;

/**
 * The User entity.
 */
public class User implements Serializable {
    private static final long serialVersionUID = 2054602555563947985L;
    private long id;
    private String login;
    private String password;
    private String name;
    private boolean enabled;
    private long roleId;
    private double balance;
    private Date createdDate;
    private Date lastUpdate;
    private int passHash;

    public enum ROLE {
        USER, ADMIN, UNKNOWN
    }

    private ROLE role;

    public User() {
    }

    public User(int id, String name, Boolean enabled, long roleId) {
        this.id = id;
        this.name = name;
        this.enabled = enabled;
        this.roleId = roleId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", enabled=" + enabled +
                ", roleId=" + roleId +
                ", balance=" + balance +
                ", createDate=" + createdDate +
                ", lastUpdate=" + lastUpdate +
                '}';
    }

    public int getPassHash() {
        return passHash;
    }

    public void setPassHash(int passHash) {
        this.passHash = passHash;
    }

}

/*
( name="users",
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
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();
}
 */
