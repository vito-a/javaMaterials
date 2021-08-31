package test.java;

import ua.training.Model;
import ua.training.GlobalConstants;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestGuessingGame2 {
    public static final int MAX_TESTS_COUNT = 100;
    public static final int TEST_CHECK_VALUE = 10;
    private final Model testModel = new Model();

    @Test
    public void testSetSecretValue() {
        for (int i = 0; i < MAX_TESTS_COUNT; i++) {
            testModel.setPrimaryBarrier(GlobalConstants.PRIMARY_MIN_BARRIER, GlobalConstants.PRIMARY_MAX_BARRIER);
            testModel.setSecretValue();
            int res = testModel.getSecretValue();
            if ((res < GlobalConstants.PRIMARY_MIN_BARRIER) || (res > GlobalConstants.PRIMARY_MAX_BARRIER)) {
                Assert.fail();
            }
        }
    }
    @Test
    public void testCheckValue() {
        Boolean res = testModel.checkValue(TEST_CHECK_VALUE);
        Assert.assertNotNull(res);
    }
    @Test
    public void testGetSecretValue() {
        testModel.setPrimaryBarrier(GlobalConstants.PRIMARY_MIN_BARRIER, GlobalConstants.PRIMARY_MAX_BARRIER);
        testModel.setSecretValue();
        int res = testModel.getSecretValue();
        Assert.assertTrue((res > GlobalConstants.PRIMARY_MIN_BARRIER) && (res < GlobalConstants.PRIMARY_MAX_BARRIER));
    }
    @Test
    public void testSetPrimaryBarrier() {
        testModel.setPrimaryBarrier(GlobalConstants.PRIMARY_MIN_BARRIER, GlobalConstants.PRIMARY_MAX_BARRIER);
        testModel.setSecretValue();
        int res = testModel.getSecretValue();
        Assert.assertFalse((res < GlobalConstants.PRIMARY_MIN_BARRIER) || (res > GlobalConstants.PRIMARY_MAX_BARRIER));
    }
    @Test
    public void testGetMinBarrier() {
        testModel.setPrimaryBarrier(GlobalConstants.PRIMARY_MIN_BARRIER, GlobalConstants.PRIMARY_MAX_BARRIER);
        int res = testModel.getMinBarrier();
        Assert.assertEquals(res, GlobalConstants.PRIMARY_MIN_BARRIER);
    }
    @Test
    public void testGetMaxBarrier() {
        testModel.setPrimaryBarrier(GlobalConstants.PRIMARY_MIN_BARRIER, GlobalConstants.PRIMARY_MAX_BARRIER);
        int res = testModel.getMaxBarrier();
        Assert.assertEquals(res, GlobalConstants.PRIMARY_MAX_BARRIER);
    }
    @Test
    public void testGetYourWay() {
        List<Integer> expectedResult = new ArrayList<Integer>();
        expectedResult.add(TEST_CHECK_VALUE);
        testModel.checkValue(TEST_CHECK_VALUE);
        List<Integer> res = testModel.getYourWay();
        Assert.assertArrayEquals(expectedResult.toArray(), res.toArray());
    }
}

