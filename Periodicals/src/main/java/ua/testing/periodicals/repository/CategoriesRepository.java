package ua.testing.periodicals.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.testing.periodicals.model.entity.Category;

import java.util.List;

@Repository
public interface CategoriesRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);
    @Query("SELECT c FROM Category c WHERE c.name LIKE %?1%")
    List<Category> search(String keyword);
    List<Category> findByOrderByCatIdAsc();
}
