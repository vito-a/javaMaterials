package ua.testing.periodicals.entitytests;

import org.junit.*;
import ua.testing.periodicals.model.entity.Subscription;

import static org.junit.Assert.*;

/**
 * The Subscription tests.
 * TODO: Subscribe method check DBException
 * TODO: check only subscribe method
 * TODO: second method: with exception
 * TODO: third method: without exception
 */
public class SubscriptionTests {
    /**
     * Test subscription entity.
     */
    @Test
    public void testSubscriptionEntity() {
        Subscription subscription = new Subscription();

        assertNotNull(subscription);

        subscription.setSubId(100L);
        subscription.setUserId(100L);
        subscription.setPeriodicalId(100L);

        assertEquals(subscription.getSubId(), 100L, 0);
        assertEquals(subscription.getUserId(), 100L, 0);
        assertEquals(subscription.getPeriodicalId(), 100L, 0);
        assertEquals(subscription.toString(),"Subscription {subId=100, userId='100', periodicalId='100', startDate='null', endDate=null}");
    }
}
