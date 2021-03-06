package PumpDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Utility class to handle exceptions
import PumpSoftware.PumpManagement;
import Utilities.ExceptionHandler;

/**
 * Database class to retrieve and write data to/from database
 */
public class db {

    // Database URL
    private static final String DB_URL = "jdbc:mysql://sql.computerstudi.es:3306/gc200333253";

    // Declaration for variables
    private static Connection connection = null;
    private static Statement statement = null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;

    /**
     * Retrieving data from database based on a Query
     * @param QRY SQL query to retrieve the data
     * @throws Exception exception in connection with database or SQL query execution
     */
    private static void getDataFromDB(final String QRY) throws Exception {
        // setting up connection
        connection = DriverManager.getConnection(DB_URL, "gc200333253", "3C^TL5rT");
        // create the statement
        statement = connection.createStatement();
        // retrieve the results
        resultSet = statement.executeQuery(QRY);
    } // connectionWithDB

    /**
     * Get user details for a specific user from the databse
     * @param userid id of the user for which the data needs to be retrieved
     * @return the user data
     */
    public static String[] getUserDetails(String userid) {

        try {

            // get the user data from database running the SQL query
            getDataFromDB("SELECT * FROM tblUsers WHERE userid = " + userid);

            // get the number of column to iterate over
            int colCount = resultSet.getMetaData().getColumnCount();

            // Array to hold to data retrieved
            String[] userData = new String[colCount];

            // Set the resultSet to first row retrieved
            resultSet.next();

            // Iterate over user data and add to the userData array
            for (int i = 1; i <= colCount; i++) {
                userData[i-1] = resultSet.getObject(i).toString();
            }

            // return the user data
            return userData;

        } catch (Exception e) {
            handleExceptions(e.getMessage());
            return null; // if error in retrieving user data
        }


    }

    /**
     * Get the prices of the gases from the tblGases
     * @return list of gas prices
     */
    public static ArrayList<Double> getGasPrices() {

        // initialize the gasPrices array
        ArrayList<Double> gasPrices = new ArrayList<>();

        // SQL Query to get the gas prices
        final String QRY = "SELECT gasPrice FROM tblGases ORDER BY 1";

        try {

            // Get the gas prices data
            getDataFromDB(QRY);

            // while loop to iterate the result set
            while (resultSet.next()) {
                // populate the gasPrices array with the price value obtained
                try {
                    gasPrices.add(Double.parseDouble(resultSet.getObject(1).toString()));
                } catch (NumberFormatException | NullPointerException e) {
                    handleExceptions("Error parsing prices retrieved from database to double. Error info: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            handleExceptions("Error connecting to the database or executing the query. Error Info: " + e.getMessage());
        } finally {
            // close the connection
            closeTheConn();
        }

        // return the retrieved gas prices
        return gasPrices;
    } // getGasPrices

    /**
     * Update the prices of the gases from the tblGases
     * @return
     */
    public static boolean updateGasPrices(Double regular, Double plus, Double supreme) {

        // SQL Query to get the gas prices
        final String QRY = "UPDATE tblGases SET gasPrice = ? WHERE gasName = ?";

        try {

            // setting up connection
            connection = DriverManager.getConnection(DB_URL, "gc200333253", "3C^TL5rT");

            PreparedStatement statement = connection.prepareStatement(QRY);

            statement.setDouble(1, regular);
            statement.setString(2, "Regular");
            statement.executeUpdate();

            statement.setDouble(1, plus);
            statement.setString(2, "Plus");
            statement.executeUpdate();

            statement.setDouble(1, supreme);
            statement.setString(2, "Supreme");
            statement.executeUpdate();

        } catch (Exception e) {
            handleExceptions("Error connecting to the database or executing the query. Error Info: " + e.getMessage());
        } finally {
            // close the connection
            closeTheConn();
        }

        return true;
    }// updateGasPrices

    public static ArrayList<Double> getGasQuantity() {

        // initialize an array to store quantity data
        ArrayList<Double> gasQuantity = new ArrayList<>();

        // SQL Query to get the gas quantity
        final String QRY = "SELECT gasQuantity FROM tblGases ORDER BY gasID";

        try {
            // Get the gas quantity data
            getDataFromDB(QRY);

            // while loop to iterate through the result set
            while (resultSet.next()) {
                // populate the gasQuantity array with the quantity values obtained
                try {
                    gasQuantity.add(Double.parseDouble(resultSet.getObject(1).toString()));
                } catch (NumberFormatException | NullPointerException e) {
                    handleExceptions("Error parsing quantity data retrieved from database to double. Error info: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            handleExceptions("Error connecting to the database or executing the query. Error Info: " + e.getMessage());
        } finally {
            // close the connection
            closeTheConn();
        }
        // return the retrieved gas prices
        return gasQuantity;
    } // getGasPrices

    // Close the database connection
    private static void closeTheConn() {
        try {
            // if resultSet wasn't instantiated or used (in case of writing to DB)
            if (resultSet != null ) {
                resultSet.close();
            }
            // close statement and connection
            statement.close();
            connection.close();
        } catch (Exception e) {
            handleExceptions("Error while closing the connection. Error Info: " + e.getMessage());
        }
    } // closeTheConn

    private static void handleExceptions(String message) {
        ExceptionHandler.printOut(message);
    }  // handleExceptions

}