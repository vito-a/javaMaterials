package ua.training.model.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The User entity.
 */
public class User {
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
    private List<Role> roles = new ArrayList<>();
    private double balance;
    private Date createdDate;
    private Date lastUpdate;
    private int passHash;

    /**
     * The enum Role.
     */
    public enum ROLE {
        /**
         * User role.
         */
        USER,
        /**
         * Admin role.
         */
        ADMIN,
        /**
         * Unknown role.
         */
        UNKNOWN
    }

    private ROLE role;

    /**
     * Instantiates a new User.
     */
    public User() {
    }

    /**
     * Instantiates a new User.
     *
     * @param userId  the user id
     * @param name    the name
     * @param enabled the enabled
     * @param roleId  the role id
     */
    public User(int userId, String name, Boolean enabled, long roleId) {
        this.userId = userId;
        this.name = name;
        this.enabled = enabled;
        this.roleId = roleId;
    }

    /**
     * Gets user id.
     *
     * @return long the user id
     */
    public long getId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setId(long userId) {
        this.userId = userId;
    }

    /**
     * Gets user password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets user password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets user enabled status.
     *
     * @return boolean enabled status
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Sets user enabled status.
     *
     * @param enabled the enabled
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets user fullname.
     *
     * @return the fullname
     */
    public String getFullname() {
        return fullname;
    }

    /**
     * Sets user fullname.
     *
     * @param fullname the fullname
     */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    /**
     * Gets user firstname.
     *
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Sets user firstname.
     *
     * @param firstname the firstname
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Gets user lastname.
     *
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Sets user lastname.
     *
     * @param lastname the lastname
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Gets user email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets user email.
     *
     * @param email the email
     */
    public void setEmail(String email) { this.email = email; }

    /**
     * Gets user account non-locked status.
     *
     * @return boolean the account is non-locked
     */
    public Boolean getAccountNonLocked() {
        return accountNonLocked;
    }

    /**
     * Sets user account non-locked status.
     *
     * @param accountNonLocked boolean the account is non-locked
     */
    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    /**
     * Gets user failed login attempts count.
     *
     * @return the failed attempt
     */
    public int getFailedAttempt() {
        return failedAttempt;
    }

    /**
     * Sets user failed login attempts count.
     *
     * @param failedAttempt the failed attempt
     */
    public void setFailedAttempt(int failedAttempt) {
        this.failedAttempt = failedAttempt;
    }

    /**
     * Gets user lock time.
     *
     * @return the lock time
     */
    public Date getLockTime() {
        return lockTime;
    }

    /**
     * Sets user lock time.
     *
     * @param lockTime the lock time
     */
    public void setLockTime(Date lockTime) {
        this.lockTime = lockTime;
    }

    /**
     * Gets user role id.
     *
     * @return the role id
     */
    public long getRoleId() { return roleId; }

    /**
     * Sets user role id.
     *
     * @param roleId the role id
     */
    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    /**
     * Gets user role enum.
     *
     * @return the role enum
     */
    public ROLE getRoleEnum() { return role; }

    /**
     * Sets user role enum.
     *
     * @param role the role
     */
    public void setRoleEnum(ROLE role) {
        this.role = role;
    }

    /**
     * Gets user balance.
     *
     * @return the balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Sets user balance.
     *
     * @param balance the balance
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Gets user created date.
     *
     * @return the created date
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets user created date.
     *
     * @param createdDate the created date
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Gets user last update date.
     *
     * @return the last update
     */
    public Date getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Sets user last update date.
     *
     * @param lastUpdate the last update
     */
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

    /**
     * Gets user pass hash.
     *
     * @return the pass hash
     */
    public int getPassHash() {
        return passHash;
    }

    /**
     * Sets user pass hash.
     *
     * @param passHash the pass hash
     */
    public void setPassHash(int passHash) {
        this.passHash = passHash;
    }

    /**
     * Gets user roles.
     *
     * @return the roles
     */
    public List<Role> getRoles() {
        return roles;
    }

    /**
     * Sets user roles.
     *
     * @param roles the roles
     */
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
