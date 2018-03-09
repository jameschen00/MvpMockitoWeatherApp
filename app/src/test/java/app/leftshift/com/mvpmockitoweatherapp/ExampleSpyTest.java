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


/*
  enable Mockito annotation (such as @Spy, @Mock,
  1. Call the method --> MockitoAnnotations.initMocks(this) to initialize annotated fields.
  2.Use the built-in runner @RunWith(MockitoJUnitRunner.class)

*/

@RunWith(MockitoJUnitRunner.class)
public  class ExampleSpyTest {


    //Simple Spy Object

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

    @Test
    public void whenStubASpy_thenStubbed() {
        List<String> list = new ArrayList<String>();
        List<String> spyList = Mockito.spy(list);

        assertEquals(0, spyList.size());

        Mockito.doReturn(100).when(spyList).size();

        assertEquals(100, spyList.size());
    }


   // Mock VS Spy in  Mockito


    /*
        1. When Mockito creates a mock – it does so from the Class of an Type, not from an actual instance.
        2. The mock simply creates a bare-bones shell instance of the Class,

     */
    @Test
    public void whenCreateMock_thenCreated() {
        List mockedList = Mockito.mock(ArrayList.class);  //bare-bones shell instantce of the class

        mockedList.add("one");
        Mockito.verify(mockedList).add("one");

        assertEquals(0, mockedList.size());
    }


    /*
        1. the spy will wrap an existing instance
        2. It will still behave in the same way as the normal instance */

    @Test
    public void whenCreateSpy_thenCreate() {
        List spyList = Mockito.spy(new ArrayList());

        spyList.add("one");
        Mockito.verify(spyList).add("one");

        assertEquals(1, spyList.size());
    }

}