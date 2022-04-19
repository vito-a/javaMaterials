package ua.testing.periodicals.service;

import junit.framework.TestCase;
import org.jooq.tools.jdbc.MockConnection;
import org.jooq.tools.jdbc.MockDataProvider;
import org.jooq.tools.jdbc.MockFileDatabase;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import ua.testing.periodicals.model.dao.DBException;
import ua.testing.periodicals.model.entity.Category;
import ua.testing.periodicals.model.entity.Periodical;
import ua.testing.periodicals.repository.PeriodicalsRepository;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class CategoriesServiceTest extends TestCase {

    final CategoriesService categoriesService = new CategoriesService();
    private PeriodicalsRepository periodicalsRepository;

    private static final Logger logger = LoggerFactory.getLogger(CategoriesServiceTest.class);

    public void testListAllNonNull() {
        List<Category> testList = new ArrayList<>();

        // Category category1 = mock(Category.class);
        Category category1 = new Category();
        category1.setCatId(1L);
        category1.setName("News");
        testList.add(category1);

        // Category category2 = mock(Category.class);
        Category category2 = new Category();
        category2.setCatId(2L);
        category2.setName("Medicine");
        testList.add(category2);

        // Category category3 = mock(Category.class);
        Category category3 = new Category();
        category3.setCatId(3L);
        category3.setName("IT");
        testList.add(category3);

        // Category category4 = mock(Category.class);
        Category category4 = new Category();
        category4.setCatId(4L);
        category4.setName("Defence");
        testList.add(category4);

        CategoriesService mock = Mockito.mock(CategoriesService.class);

        List<Category> result = null;
        try {
            when(mock.listAll("latest")).thenReturn(testList);
            result = mock.listAll("latest");
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertNotNull(result);
        assertEquals(result.size(), 4);

        assertEquals(category1.getCatId(), 1L, 0);
        assertEquals(category1.getName(), "News");
        assertEquals(category1.toString(),"Category(catId=1, name=News)");
        assertEquals(category2.toString(),"Category(catId=2, name=Medicine)");
        assertEquals(category3.toString(),"Category(catId=3, name=IT)");
        assertEquals(category4.toString(),"Category(catId=4, name=Defence)");
    }

    public void testListAllNull() {
        List<Category> testList = null;
        try {
            testList = categoriesService.listAll(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertNull(testList);
    }
}