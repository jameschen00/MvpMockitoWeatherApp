package app.leftshift.com.mvpmockitoweatherapp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by vrushali on 3/4/18.
 */
public class CalculatorJunitExampleTest {


    CalculatorJunitExample calculator;

    @Before
    public void setUp(){
        calculator = new CalculatorJunitExample();
    }

    /*   objective :- Call calculator add
        check if it's returning correct value
    */

    @Test
    public void addTwoIntegers() throws Exception{
        assertEquals(5, calculator.add(2,3));
    }


    @After
    public void tearDown() throws Exception {
    }
}