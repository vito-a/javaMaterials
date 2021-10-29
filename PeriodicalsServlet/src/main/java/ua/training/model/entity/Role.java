package ua.training.model.entity;

public class Role {
    private long roleId;
    private String name;

    public Role() {
    }

    public Role(Long roleId, String name) {
        this.name = name;
        this.roleId = roleId;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
