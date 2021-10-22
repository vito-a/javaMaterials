package ua.testing.periodicals.repotests;

import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;
import ua.testing.periodicals.model.entity.Category;
import ua.testing.periodicals.repository.CategoriesRepository;

import java.util.List;
import java.util.Optional;


/**
 * The Categories repo test.
 */
public class CategoriesRepoTest {

    @Autowired
    private CategoriesRepository categoryRepo;

    /**
     * Test categories repo.
     */
    @Test
    public void testCategoriesRepo() {

        List<Category> categories = categoryRepo.findAll();
        Category category1 = categoryRepo.findByName("IT");
        Optional<Category> category2 = categoryRepo.findById(1L);
        Category category = new Category();

        category.setCatId(1L);
        category.setName("Test category 1");

        Assert.assertEquals(categories.get(0).getCatId(), 1L, 0);
        Assert.assertEquals(category1.getName(), "News");
        Assert.assertEquals(categories.get(0).getCatId(), 1L, 0);
        Assert.assertEquals(category2.get().getCatId(), 2L, 0);
        Assert.assertEquals(category.getCatId(), 1L, 0);
        Assert.assertEquals(category.getName(), "News");
    }
}
