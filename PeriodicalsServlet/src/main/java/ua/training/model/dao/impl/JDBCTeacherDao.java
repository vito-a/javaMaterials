package ua.training.model.dao.impl;

import ua.training.model.dao.TeacherDao;
import ua.training.model.entity.Teacher;

import java.sql.Connection;
import java.util.List;

public class JDBCTeacherDao implements TeacherDao {
    private Connection connection;

    public JDBCTeacherDao(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void create(Teacher entity) {

    }

    @Override
    public Teacher findById(int id) {
        return null;
    }

    @Override
    public List<Teacher> findAll() {
        return null;
    }

    @Override
    public void update(Teacher entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close()  {

    }
}
