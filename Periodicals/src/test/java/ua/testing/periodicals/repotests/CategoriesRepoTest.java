package ua.testing.periodicals.repotests;

import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;
import ua.testing.periodicals.model.entity.Category;
import ua.testing.periodicals.repository.CategoriesRepository;
import ua.testing.periodicals.service.CategoriesService;
import ua.testing.periodicals.service.UsersService;

import java.util.List;
import java.util.Optional;


/**
 * The Categories repo test.
 * TODO: Ctrl+Shift+T IDEA will create the packages properly in the folders
 * TODO: autowiring the categoryRepo is a bad practice
 * TODO: it is needed to pass the mocked Repos
 * TODO: repo tests are not unit tests
 */
public class CategoriesRepoTest {

    @Autowired
    private CategoriesRepository categoryRepo;

    private CategoriesService categoriesService;

    /**
     * Test categories repo.
     */
    @Test
    public void testCategoriesRepo() {
        categoriesService = new CategoriesService();
        List<Category> categories = categoriesService.listAll("");
        // List<Category> categories = categoryRepo.findAll();

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
