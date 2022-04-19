package ua.testing.periodicals.service;

import junit.framework.TestCase;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.testing.periodicals.model.entity.Subscription;
import ua.testing.periodicals.repository.PeriodicalsRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class SubscriptionsServiceTest extends TestCase {

    final SubscriptionsService subscriptionsService = new SubscriptionsService();

    private static final Logger logger = LoggerFactory.getLogger(SubscriptionsServiceTest.class);

    public void testListAllNonNull() {
        List<Subscription> testList = new ArrayList<>();

        // Subscription subscription1 = mock(Subscription.class);
        Subscription subscription1 = new Subscription();
        subscription1.setSubId(1L);
        subscription1.setPeriodicalId(1L);
        subscription1.setUserId(1L);
        testList.add(subscription1);

        // Subscription subscription2 = mock(Subscription.class);
        Subscription subscription2 = new Subscription();
        subscription2.setSubId(2L);
        subscription2.setPeriodicalId(2L);
        subscription2.setUserId(2L);
        testList.add(subscription2);

        SubscriptionsService mock = Mockito.mock(SubscriptionsService.class);

        List<Subscription> result = null;
        try {
            when(mock.listAll("latest")).thenReturn(testList);
            result = mock.listAll("latest");
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertNotNull(result);
        assertEquals(result.size(), 2);

        assertEquals(subscription1.getSubId(), 1L, 0);
        assertEquals(subscription1.getPeriodicalId(), 1L, 0);
        assertEquals(subscription1.toString(),"Subscription {subId=1, userId='1', periodicalId='1', startDate='null', endDate=null}");
        assertEquals(subscription2.toString(),"Subscription {subId=2, userId='2', periodicalId='2', startDate='null', endDate=null}");
    }

    public void testListAllNull() {
        List<Subscription> testList = null;
        try {
            testList = subscriptionsService.listAll(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertNull(testList);
    }
}