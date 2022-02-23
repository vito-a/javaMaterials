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
     * TODO: in mocking usually mocks are used, spy's are rare
     * TODO: argumentCaptures are widely used
     * TODO: when you are testing different environments, the main goal is to make sure that these are the same
     * TODO: so testing basically is to make sure they behave similarly
     */
    @Test
    public void testPeriodicalEntity() {
        Periodical periodical = new Periodical();

        assertNotNull(periodical);

        periodical.setPeriodicalId(100L);
        periodical.setName("Forbes");
        periodical.setPrice(150L);
        periodical.setDescription("Test description 1");
        periodical.setCategoryId(1L);

        assertEquals(periodical.getPeriodicalId(), 100L, 0);
        assertEquals(periodical.getName(), "Forbes");
        assertEquals(periodical.getPrice(), 150L,0);
        assertEquals(periodical.getDescription(), "Test description 1");
        assertEquals(periodical.getCategoryId(), 1L, 0);
        assertEquals(periodical.toString(),"Periodical{id=100, name='Forbes', description='Test description 1', catId='1', price='150'}");
    }
}
