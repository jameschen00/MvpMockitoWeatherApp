package app.leftshift.com.mvpmockitoweatherapp;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import view.CalculatorService;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by vrushali on 3/8/18.
 */
public class CalculatorMockitoExampleTest {


   /* @Mock
       CalculatorService service;
    */

    //Rule
    @Rule
    public MockitoRule mockitoRule  = MockitoJUnit.rule();


    //Mock CalculatorJunitExample service
    CalculatorService service = mock(CalculatorService.class);

    CalculatorMockitoExample calculator;

    @Before
    public void setUp() throws Exception {
        calculator = new CalculatorMockitoExample(service);
    }


    /*
        1. Test with Junit
        1.  Mock the Service  - which you don't want to test
        2. After Mocking the Service class, It will fail as service class don't know what output to return.
        3. So we have to stub or mock the result
        4. Now how we can verify - service method is calling or not.
        5. Wanted but not invoked: Exception

     */
    @Test
    public void performCalculatorService(){

        when(service.add(2,3)).thenReturn(5);

        assertEquals(5, calculator.performAddition(2,3));

        verify(service).add(2,3);
    }

    @After
    public void tearDown() throws Exception {
    }

}