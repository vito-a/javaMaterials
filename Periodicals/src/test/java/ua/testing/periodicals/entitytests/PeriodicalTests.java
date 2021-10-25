package ua.testing.periodicals.entitytests;

import org.junit.*;
import ua.testing.periodicals.model.entity.Periodical;

import static org.junit.Assert.*;

/**
 * The Periodical tests.
 */
public class PeriodicalTests {
    /**
     * Test periodical entity.
     */
    @Test
    public void testPeriodicalEntity() {
        Periodical periodical = new Periodical();

        assertNotNull(periodical);

        periodical.setPeriodicalId(100L);
        periodical.setName("Forbes");
        periodical.setPrice(150L);
        periodical.setDescription("Test description 1");
        periodical.setCategoryId("News");

        assertEquals(periodical.getPeriodicalId(), 100L, 0);
        assertEquals(periodical.getName(), "Пізнайко");
        assertEquals(periodical.getPrice(), 150L,0);
        assertEquals(periodical.getDescription(), "Test description 1");
        assertEquals(periodical.getCategoryId(), "News");
        assertEquals(periodical.toString(),"Publication [name=Forbes,price=150,description=Test description1,CategoryId=News,PeriodicalId=100];");
    }
}