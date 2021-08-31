package test;

import ua.training.Model;
import org.junit.Assert;
import org.junit.Test;

public class TestGuessingGame2 {
    @Test
    public void testGetIntValue() {
        Model a = new Model();
        int res = a.getIntValue();
        if (res != -1) {
            Assert.fail();
        }
    }
    @Test
    public void testSetIntValue() {
        Model a = new Model();
        a.setIntValue(5);
        Integer res = a.getIntValue();
        if (!(res.equals(5))) {
            Assert.fail();
        }
    }
    @Test
    public void testGetRandomIntValue() {
        Model a = new Model();
        int res = a.getRandomIntValue();
        if (res != -2) {
            Assert.fail();
        }
    }
    @Test
    public void testSetRandomIntValue() {
        Model a = new Model();
        a.setRandomIntValue(10);
        Integer res = a.getRandomIntValue();
        if (!(res.equals(10))) {
            Assert.fail();
        }
    }
}

