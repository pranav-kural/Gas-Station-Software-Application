package PumpSoftware;

// import the services to work with Pump's database
import PumpDB.db;

import javax.swing.*;
import java.awt.*;
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

    // panels
    private JPanel pnlCenterMain, pnlCenterUpper, pnlCenterLower;

    // labels
    private JLabel lblRegular = new JLabel("Regular: "),
                   lblPlus    = new JLabel("Plus: "),
                   lblSupreme = new JLabel("Supreme");

    // text fields
    private JTextField txtRegular = new JTextField(10),
                       txtPlus    = new JTextField(10),
                       txtSupreme = new JTextField(10);

    // buttons
    private JButton btnSave   = new JButton("Save"),
                    btnCancel = new JButton("Cancel");

    // constructor
    public PumpManagement() {
        // get the prices of the gases
        //getGasPrices();
        generateGUI();
    }

    private void generateGUI() {
        add(new JLabel("Success!"));

        setLayout(new BorderLayout());

        // Center Upper Panel
        pnlCenterUpper = new JPanel();
        pnlCenterUpper.setLayout(new GridLayout(3, 2));
        pnlCenterUpper.add(lblRegular);
        pnlCenterUpper.add(txtRegular);
        pnlCenterUpper.add(lblPlus);
        pnlCenterUpper.add(txtPlus);
        pnlCenterUpper.add(lblSupreme);
        pnlCenterUpper.add(txtSupreme);

        // Center Lower Panel
        pnlCenterLower = new JPanel();
        pnlCenterLower.setLayout(new FlowLayout());
        pnlCenterLower.add(btnSave);
        pnlCenterLower.add(btnCancel);

        // Center Main Panel
        pnlCenterMain = new JPanel();
        pnlCenterMain.add(pnlCenterUpper, BorderLayout.NORTH);
        pnlCenterMain.add(pnlCenterLower, BorderLayout.SOUTH);

        add(pnlCenterMain, BorderLayout.CENTER);

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
