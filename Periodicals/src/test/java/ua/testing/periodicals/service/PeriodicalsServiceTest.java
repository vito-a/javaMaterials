package ua.testing.periodicals.service;

import junit.framework.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.parameters.P;
import ua.testing.periodicals.model.entity.Periodical;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

import static org.mockito.Mockito.*;

public class PeriodicalsServiceTest extends TestCase {

    final PeriodicalsService periodicalsService = new PeriodicalsService();
    private static final Logger logger = LoggerFactory.getLogger(PeriodicalsServiceTest.class);

    public void testListAllNull() {
        List<Periodical> testList = new ArrayList<Periodical>();

        // Periodical periodical1 = mock(Periodical.class);
        Periodical periodical1 = new Periodical();
        periodical1.setPeriodicalId(1L);
        periodical1.setName("Guardian");
        periodical1.setPrice(200L);
        periodical1.setDescription("Latest news, sport, business, comment, analysis and reviews from the world's leading liberal voice");
        periodical1.setCategoryId(1L);
        testList.add(periodical1);

        // Periodical periodical2 = mock(Periodical.class);
        Periodical periodical2 = new Periodical();
        periodical2.setPeriodicalId(5L);
        periodical2.setName("Janes");
        periodical2.setPrice(600L);
        periodical2.setDescription("The latest defence and security news - the trusted source for defence intelligence");
        periodical2.setCategoryId(5L);
        testList.add(periodical2);

        PeriodicalsService mock = org.mockito.Mockito.mock(PeriodicalsService.class);

        // periodicalsService.listAll() returns "List<Periodical>"
        when(mock.listAll("latest")).thenReturn(testList);

        List<Periodical> result = mock.listAll("latest");

        assertNotNull(result);
        assertEquals(result.size(), 2);

        assertEquals(periodical1.getPeriodicalId(), 1L, 0);
        assertEquals(periodical1.getName(), "Guardian");
        assertEquals(periodical1.getPrice(), 200L,0);
        assertEquals(periodical1.getDescription(), "Latest news, sport, business, comment, analysis and reviews from the world's leading liberal voice");
        assertEquals(periodical1.getCategoryId(), 1L, 0);
        assertEquals(periodical1.toString(),"Periodical{id=1, name='Guardian', description='Latest news, sport, business, comment, analysis and reviews from the world's leading liberal voice', catId='1', price='200'}");
        assertEquals(periodical2.toString(),"Periodical{id=5, name='Janes', description='The latest defence and security news - the trusted source for defence intelligence', catId='5', price='600'}");
    }

    public void testListAllNonNull() {
        List<Periodical> testList = periodicalsService.listAll("liberal");
        assertNull(testList);
    }

    public void testTestListAll() {
    }

    public void testSave() {
    }

    public void testSubscribe() {
    }

    public void testGet() {
    }

    public void testTestGet() {
    }

    public void testDelete() {
    }
}