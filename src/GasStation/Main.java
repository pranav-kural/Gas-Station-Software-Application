package GasStation;

// import PumpSoftware classes required to run the GasStation
import PumpSoftware.*;

/**
 * Main Class to run the Gas Station and utilize all of the application logic
 */
public class Main {

  // Main method
    public static void main(String[] args) {

        // Instantiate a new instance of the PumpManagement Class to test the printing out of gas prices
        PumpManagement pumpManagementHub = new PumpManagement();

        // print out the gas prices information
        System.out.println(pumpManagementHub);
    }

}
