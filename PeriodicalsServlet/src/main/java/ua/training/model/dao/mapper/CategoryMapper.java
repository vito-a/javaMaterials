package ua.training.model.dao.mapper;

import ua.training.model.entity.Category;
import ua.training.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * The Category mapper.
 */
public class CategoryMapper implements ObjectMapper<Category> {

    @Override
    public Category extractFromResultSet(ResultSet rs) throws SQLException {
        Category category = new Category();
        category.setCatId(rs.getLong("cat_id"));
        category.setName(rs.getString("name"));
        return category;
    }

    public Category makeUnique(Map<Long, Category> cache,
                               Category category) {
        cache.putIfAbsent(category.getCatId(), category);
        return cache.get(category.getCatId());
    }
}
