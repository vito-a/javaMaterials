package ua.training.model.entity;

/**
 * The Role entity.
 */
public class Role {
    private long roleId;
    private String name;

    /**
     * Instantiates a new Role.
     */
    public Role() {
    }

    /**
     * Instantiates a new Role.
     *
     * @param roleId the role id
     * @param name   the name
     */
    public Role(Long roleId, String name) {
        this.name = name;
        this.roleId = roleId;
    }

    /**
     * Gets role id.
     *
     * @return the role id
     */
    public long getRoleId() {
        return roleId;
    }

    /**
     * Sets role id.
     *
     * @param roleId the role id
     */
    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    /**
     * Gets role name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets role name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }
}
