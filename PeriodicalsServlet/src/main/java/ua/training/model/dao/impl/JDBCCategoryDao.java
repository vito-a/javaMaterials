package ua.training.model.dao.impl;

import ua.training.controller.commands.Login;
import ua.training.model.dao.CategoriesDao;
import ua.training.model.dao.mapper.CategoryMapper;
import ua.training.model.entity.Category;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCCategoryDao implements CategoriesDao {
    private Connection connection;
    private final Logger logger = LogManager.getLogger(Login.class.getName());

    public JDBCCategoryDao(Connection connection) { this.connection = connection; }

    @Override
    public int create (Category entity) { return 0; }

    @Override
    public Category findById(int id) {
        return null;
    }

    @Override
    public List<Category> findAll () {
        List<Category> listCategories = new ArrayList<>();
        ResultSet rs = null;
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM categories ORDER BY cat_id")) {
            rs = ps.executeQuery();
            CategoryMapper mapper = new CategoryMapper();
            while (rs.next()) {
                listCategories.add(mapper.extractFromResultSet(rs));
            }
        } catch (Exception e) {
            logger.error("Cannot get categoris list", e);
            throw new RuntimeException(e);
        }
        return listCategories;
    }

    public List<Category> getAllCategories (Connection connection,int currentPage, int recordsPerPage) throws SQLException {
        List<Category> listCategories = new ArrayList<>();
        ResultSet rs = null;
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM categories LIMIT ?, ?")) {
            int start = currentPage * recordsPerPage - recordsPerPage;
            int i = 1;
            ps.setInt(i++, start);
            ps.setInt(i++, recordsPerPage);
            rs = ps.executeQuery();
            CategoryMapper mapper = new CategoryMapper();
            while (rs.next()) {
                listCategories.add(mapper.extractFromResultSet(rs));
            }
        } catch (Exception e) {
            logger.error("Cannot get categories list", e);
            throw new RuntimeException(e);
        }
        return listCategories;

    }

    @Override
    public int update (Category entity){
        return 0;
    }

    @Override
    public void delete ( int id){

    }

    @Override
    public void close () {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Category> findByName (String name){

        Optional<Category> result = Optional.empty();
        try (PreparedStatement ps = connection.prepareCall("SELECT * FROM categories WHERE name = ?")) {
            ps.setString(1, name);
            ResultSet rs;
            rs = ps.executeQuery();
            CategoryMapper mapper = new CategoryMapper();
            if (rs.next()) {
                result = Optional.of(mapper.extractFromResultSet(rs));
            }//TODO : ask question how avoid two categories with the same name
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return result;
    }
}