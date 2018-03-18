package app.leftshift.com.mvpmockitoweatherapp;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
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

    /*1. helps keep tests clean
      2. Rule will not report - stubbing warnings during test execution
      OR- Use @RunWith(JExample.class)
    */

    @Rule
    public MockitoRule mockitoRule  = MockitoJUnit.rule();

    /*1. Mock calculatorService class
      2. Use @Mock Annotation
    */
    CalculatorService calculatorService = mock(CalculatorService.class);

     /*@Mock
     CalculatorService calculatorService;*/

    CalculatorMockitoExample calculator;

    @Before
    public void setUp() throws Exception {
        calculator = new CalculatorMockitoExample(calculatorService);
    }

    /* Objective :-
        1.  Mock the Service  - which you don't want to test
        Check Service method is called */


    @Test
    public void testThatAdditionIsPerformed(){

        //given
        when(calculatorService.add(2,3)).thenReturn(5);

        //When
        assertEquals(5, calculator.performAddition(2,3));

        //Then
        verify(calculatorService).add(2,3);
    }

    @After
    public void tearDown() throws Exception {
    }

}