package Test;

import Calculation.Arithmetics;
import org.junit.Assert;
import org.junit.Test;

public class TestArithmetics {
    @Test
    public void testAdd() {
        Arithmetics a = new Arithmetics();
        double res = a.add(3,7 );
        if (res != 10) {
            Assert.fail();
        }
    }
    @Test
    public void testMult() {
        Arithmetics a = new Arithmetics();
        double res = a.mult(3,7 );
        if (res != 21) {
            Assert.fail();
        }
    }
    @Test
    public void testDeduct() {
        Arithmetics a = new Arithmetics();
        double res = a.deduct(3,7 );
        if (res != -4) {
            Assert.fail();
        }
    }
    @Test
    public void testDiv() {
        Arithmetics a = new Arithmetics();
        double res = a.div(10,5 );
        if (res != 2.0) {
            Assert.fail();
        }
    }
}
