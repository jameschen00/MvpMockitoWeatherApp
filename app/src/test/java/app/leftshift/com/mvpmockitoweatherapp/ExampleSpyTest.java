package app.leftshift.com.mvpmockitoweatherapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by vrushali on 3/6/18.
 */


/*Enable Mockito annotation (such as @Spy, @Mock,
  @RunWith(MockitoJUnitRunner.class)
*/

@RunWith(MockitoJUnitRunner.class)
public  class ExampleSpyTest {


    /*
        1.Spy on real object,
        2. allow us to call all normal method
        3. track every interaction
     */

    @Test
    public void whenSpyingOnList_thenCorrect() {

        List<String> list = new ArrayList<String>();
        List<String> spyList = Mockito.spy(list);

        spyList.add("one");
        spyList.add("two");

        Mockito.verify(spyList).add("one");
        Mockito.verify(spyList).add("two");

        assertEquals(2, spyList.size());
    }


    // The @Spy Annotation
    @Spy
    List<String> spyList = new ArrayList<String>();

    @Test
    public void whenUsingTheSpyAnnotation_thenObjectIsSpied() {
        spyList.add("one");
        spyList.add("two");

        Mockito.verify(spyList).add("one");
        Mockito.verify(spyList).add("two");

        assertEquals(2, spyList.size());
    }

    // Stubbing a Spy

    /*
        1. make spy list return what you want to return
     */

    @Test
    public void whenStubASpy_thenStubbed() {
        List<String> list = new ArrayList<String>();
        List<String> spyList = Mockito.spy(list);

        assertEquals(0, spyList.size());

        Mockito.doReturn(100).when(spyList).size();

        assertEquals(100, spyList.size());
    }



    // Mock Versus Spy in  Mockito


    /*1. When Mockito creates a mock –
         it does so from the Class of an Type, not from an actual instance.*/

    @Test
    public void whenCreateMock_thenCreated() {

        List mockedList = Mockito.mock(ArrayList.class);

        mockedList.add("one");
        Mockito.verify(mockedList).add("one");

        assertEquals(0, mockedList.size());
    }


    /*  1. the spy will wrap an existing instance
        2. It will still behave in the same way as the normal instance */

    @Test
    public void whenCreateSpy_thenCreate() {
        List spyList = Mockito.spy(new ArrayList());

        spyList.add("one");
        Mockito.verify(spyList).add("one");

        assertEquals(1, spyList.size());
    }

}