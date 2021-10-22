package ua.testing.periodicals.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.testing.periodicals.model.entity.Category;

import java.util.List;

/**
 * The Categories repository.
 */
@Repository
public interface CategoriesRepository extends JpaRepository<Category, Long> {
    /**
     * Find by name category.
     *
     * @param name the name
     * @return the category object
     */
    Category findByName(String name);

    /**
     * Search list.
     *
     * @param keyword the keyword
     * @return the category list
     */
    @Query("SELECT c FROM Category c WHERE c.name LIKE %?1%")
    List<Category> search(String keyword);

    /**
     * Find by order by cat id asc list.
     *
     * @return the list
     */
    List<Category> findByOrderByCatIdAsc();
}
