package PumpDB;

import java.sql.*;
import java.util.HashMap;
import java.util.List;

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
     * Get the prices of the gases from the tblGases
     * @return list of gas prices
     */
    private static List<Double> getGasPrices() {

        // initialize the gasPrices array
        List<Double> gasPrices = null;

        // SQL Query to get the gas prices
        final String QRY = "SELECT gasPrices FROM tblGases";

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
        System.out.println(message);
    }  // handleExceptions

}