package app.leftshift.com.mvpmockitoweatherapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

/**
 * Created by vrushali on 3/6/18.
 */

@RunWith(MockitoJUnitRunner.class)
public class ArgumentCaptorTest {

    @Mock
    List mockList;

    /*
        1. ArgumentCaptor is a special implementation of an argument matcher.
        2. captures argument values for further assertions.
        3. Captor is not useful if you need argument matching for stubbing */

    @Captor
    ArgumentCaptor argumentCaptor;

    @Test
    public void useCaptorAnnotationForStoringValue() throws Exception{

        mockList.add("one");

        //First verify the mocklist and then captor the value.
        verify(mockList).add(argumentCaptor.capture());

        assertEquals("one",argumentCaptor.getValue());
    }

}
