package test.java;

import ua.training.Model;
import ua.training.GlobalConstants;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class TestGuessingGame2 {
    public static final int MAX_TESTS_COUNT = 100;
    public static final int TEST_CHECK_VALUE = 10;
    private Model testModel = new Model();

    @Test
    public void testSetSecretValue() {
        for (int i = 0; i < this.MAX_TESTS_COUNT; i++) {
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
        Boolean res = testModel.checkValue(this.TEST_CHECK_VALUE);
        if (res.equals(null)) {
            Assert.fail();
        }
    }
    @Test
    public void testGetSecretValue() {
        int res = testModel.getSecretValue();
        if ((res < GlobalConstants.PRIMARY_MIN_BARRIER) || (res > GlobalConstants.PRIMARY_MAX_BARRIER)) {
            Assert.fail();
        }
    }
    @Test
    public void testSetPrimaryBarrier() {
        testModel.setPrimaryBarrier(GlobalConstants.PRIMARY_MIN_BARRIER, GlobalConstants.PRIMARY_MAX_BARRIER);
        testModel.setSecretValue();
        int res = testModel.getSecretValue();
        if ((res < GlobalConstants.PRIMARY_MIN_BARRIER) || (res > GlobalConstants.PRIMARY_MAX_BARRIER)) {
            Assert.fail();
        }
    }
    @Test
    public void testGetMinBarrier() {
        testModel.setPrimaryBarrier(GlobalConstants.PRIMARY_MIN_BARRIER, GlobalConstants.PRIMARY_MAX_BARRIER);
        int res = testModel.getMinBarrier();
        if (res != GlobalConstants.PRIMARY_MIN_BARRIER) {
            Assert.fail();
        }
    }
    @Test
    public void testGetMaxBarrier() {
        testModel.setPrimaryBarrier(GlobalConstants.PRIMARY_MIN_BARRIER, GlobalConstants.PRIMARY_MAX_BARRIER);
        int res = testModel.getMaxBarrier();
        if (res != GlobalConstants.PRIMARY_MAX_BARRIER) {
            Assert.fail();
        }
    }
    @Test
    public void testGetYourWay() {
        List<Integer> res = testModel.getYourWay();
        String resClass = res.getClass().getName();
        if (!resClass.equals("java.util.ArrayList")) {
            Assert.fail();
        }
    }
}

