package Tests;

import PumpDB.db;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
        String[] actualUserData = { "10000", "123", "n" };
        // Match the user data
        assertArrayEquals(userDataRetieved, actualUserData);
    }

    @Test
    public void getGasPrices() throws Exception {
    }

}