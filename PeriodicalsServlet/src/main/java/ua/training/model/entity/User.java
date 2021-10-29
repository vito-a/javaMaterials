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
    private long userId;
    private String username;
    private String fullname;
    private String firstname;
    private String lastname;
    private String email;
    private boolean accountNonLocked;
    private int failedAttempt;
    private Date lockTime;
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

    public User(int userId, String name, Boolean enabled, long roleId) {
        this.userId = userId;
        this.name = name;
        this.enabled = enabled;
        this.roleId = roleId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getId() {
        return userId;
    }

    public void setId(long userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String name) {
        this.email = email;
    }

    public Boolean getAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public int getFailedAttempt() {
        return failedAttempt;
    }

    public void setFailedAttempt(int failedAttempt) {
        this.failedAttempt = failedAttempt;
    }

    public Date getLockTime() {
        return lockTime;
    }

    public void setLockTime(Date lockTime) {
        this.lockTime = lockTime;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public ROLE getRoleEnum() { return role; }

    public void setRoleEnum(ROLE role) {
        this.role = role;
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
                "id=" + userId +
                ", username='" + username + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", enabled=" + enabled +
                ", roleId=" + roleId +
                ", balance=" + balance +
                ", createdDate=" + createdDate +
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
public class User {
    JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();
}
*/
