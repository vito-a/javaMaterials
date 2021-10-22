package ua.testing.periodicals.entitytests;

import org.junit.*;
import ua.testing.periodicals.model.entity.Category;

import static org.junit.Assert.*;

/**
 * The Category tests.
 */
public class CategoryTests {
    /**
     * Test category entity.
     */
    @Test
    public void testCategoryEntity() {
        Category category = new Category();

        assertNotNull(category);

        category.setCatId(100L);
        category.setName("News");

        assertEquals(category.getCatId(), 100L, 0);
        assertEquals(category.getName(), "News");
        assertEquals(category.toString(),"Category[CatId=100,name=News]");
    }
}
