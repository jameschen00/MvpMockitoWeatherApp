package app.leftshift.com.mvpmockitoweatherapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Map;

import static junit.framework.Assert.assertEquals;

/**
 * Created by vrushali on 3/8/18.
 */

@RunWith(MockitoJUnitRunner.class)
public class InjectMocksExampleTest {


    /*
        we use @InjectMocks -  to inject the mock wordMap into the MyDictionary dic:
    */

    @Mock
    Map<String, String> wordMap;


    //Inject Mock Fields into the tested object automatically.
    @InjectMocks
    MyDictionary dic = new MyDictionary();

    @Test
    public void whenUseInjectMocksAnnotation_thenCorrect() {

        Mockito.when(wordMap.get("injectMock")).thenReturn("mockAndInjectSameTime");

        assertEquals("mockAndInjectSameTime", dic.getMeaning("injectMock"));
    }

}
