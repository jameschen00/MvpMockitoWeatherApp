package TestDoubles;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import app.leftshift.com.mvpmockitoweatherapp.Customer;
import model.City;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * Created by vrushali on 3/5/18.
 */

@RunWith(MockitoJUnitRunner.class)
public class TestDummy {

    //Dummy Object
    public Customer createDummyCustomer() {
        Customer customer = new Customer("john", "dobie", "Domlur");
        return customer;
    }

   /* 1. Also The code below uses a lot of code to create the customer which is not important to the test.*/
    @Test
    public void addCustomerTest() {
        Customer dummy = createDummyCustomer();
        AddressBook addressBook = new AddressBook();
        addressBook.addCustomer(dummy);
        assertEquals(1, addressBook.getNumberOfCustomers());
    }
    /*
            1. We actually don't care about the contents of customer object - but it is required.
            2. You would expect some kind of exception to be thrown
    */
    @Test(expected=Exception.class)
    public void addNullCustomerTest() {
        Customer dummy = null;
        AddressBook addressBook = new AddressBook();
        addressBook.addCustomer(dummy);
    }

    /*
            1.  To avoid this we can use a simple Mockito dummy ->  desired behaviour
            2.  Don't be fooled by the mock syntax - the role being played here is that of a dummy, not a mock.
    */
    @Test
    public void addCustomerWithDummyTest() {
        Customer dummy = mock(Customer.class); // -- > Creates a dummy object to be passed into the call.
        AddressBook addressBook = new AddressBook();
        addressBook.addCustomer(dummy);
        Assert.assertEquals(1, addressBook.getNumberOfCustomers());
    }

    private class AddressBook {
        ArrayList<Customer> customers = new ArrayList<>();
        public void addCustomer(Customer dummy) {
            customers.add(dummy);
        }
        public int getNumberOfCustomers() {
            return customers.size();
        }
    }



}
