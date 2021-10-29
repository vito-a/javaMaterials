package ua.training.model.dao.impl;

import ua.training.model.dao.RoleDao;
import ua.training.model.entity.Role;

import java.sql.Connection;
import java.util.List;

public class JDBCRoleDao implements RoleDao {
    private Connection connection;

    public JDBCRoleDao(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void create(Role entity) {

    }

    @Override
    public Role findById(int id) {
        return null;
    }

    @Override
    public List<Role> findAll() {
        return null;
    }

    @Override
    public void update(Role entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close()  {

    }
}
