package Tests;

import PumpDB.db;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Test Class for the Database file
 */
public class dbTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getUserDetails() throws Exception {
        // get user details for user 10000
        String[] userDataRetieved = db.getUserDetails("10000");
        // User data to be matched with
        String[] expectedUserData = { "10000", "123", "n" };
        // Match the user data
        assertArrayEquals(expectedUserData, userDataRetieved);
    }

    @Test
    public void getGasQuantity() throws Exception {
        // get user details for user 10000
        Double[] gasQuanitiesRetrieved = db.getGasQuantity().toArray(new Double[3]);

        // User data to be matched with
        Double[] excpectedGasQuanities = { 1000.0, 1000.0, 1000.0 };
        // Match the user data
        assertEquals(excpectedGasQuanities, gasQuanitiesRetrieved);
    }

    @Test
    public void getGasPrices() throws Exception {
    }

}