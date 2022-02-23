package ua.testing.periodicals.entitytests;

import org.junit.Test;
import ua.testing.periodicals.model.entity.Periodical;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * The CustomUserDetailsTests tests.
 */
public class CustomUserDetailsTests {
    /**
     * Test CustomUserDetailsTests entity.
     */
    @Test
    public void testPeriodicalEntity() {
        Periodical periodical = new Periodical();

        assertNotNull(periodical);

        periodical.setPeriodicalId(100L);
        periodical.setName("Forbes");
        periodical.setPrice(150L);
        periodical.setDescription("Test description 1");
        periodical.setCategoryId(5L);

        assertEquals(periodical.getPeriodicalId(), 100L, 0);
        assertEquals(periodical.getName(), "Forbes");
        assertEquals(periodical.getPrice(), 150L,0);
        assertEquals(periodical.getDescription(), "Test description 1");
        assertEquals(periodical.getCategoryId(), 5L, 0);
        assertEquals(periodical.toString(),"Periodical{id=100, name='Forbes', description='Test description 1', catId='5', price='150'}");
    }
}
