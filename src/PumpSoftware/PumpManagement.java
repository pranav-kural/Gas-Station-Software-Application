package PumpSoftware;

// import the services to work with Pump's database
import PumpDB.db;

import javax.swing.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Pump Management class to manage the prices of the gases
 */
public class PumpManagement extends JPanel{

    // gas price variables
    private double regularPrice;
    private double plusPrice;
    private double supremePrice;

    // constructor
    public PumpManagement() {
        // get the prices of the gases
        //getGasPrices();
        generateGUI();
    }

    private void generateGUI() {
        add(new JLabel("Success!"));
    }

    // get gas prices from the database
    private void getGasPrices() {

        // get gas prices
        ArrayList<Double> gasPrices = db.getGasPrices();

        // set the gas prices
        regularPrice = gasPrices.get(2);    // price for regular gas type
        plusPrice = gasPrices.get(1);       // price for plus gas type
        supremePrice = gasPrices.get(2);    // price for plus

    }

    // Override toString method to print out gas information
    @Override
    public String toString() {
        // for decimal formatting, getting 2 decimal places
        DecimalFormat df = new DecimalFormat("#.00");
        return String.format("------%s------%n%s: %s%n%s: %s%n%s: %s%n",
                "Gas Prices",
                "Regular", df.format(regularPrice),
                "Plus", df.format(plusPrice),
                "Supreme", df.format(supremePrice));
    }
}
