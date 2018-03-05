package app.leftshift.com.mvpmockitoweatherapp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by vrushali on 3/4/18.
 */
public class CalculatorTest {


    //Calculator calculator = new Calculator();

    Calculator calculator = null;

    @Before
    public void setUp() throws Exception {
        calculator = new Calculator();
    }

    @Test
    public void addTwoIntegers() throws Exception{
        assertEquals(5, calculator.add(2,3));
    }

    @After
    public void tearDown() throws Exception {
    }

}