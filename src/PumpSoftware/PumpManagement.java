package PumpSoftware;

// import the services to work with Pump's database
import PumpDB.db;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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
    private JPanel pnlMain, pnlNorth, pnlSouth;

    // labels
    private JLabel lblRegular = new JLabel("Regular: "),
                   lblPlus    = new JLabel("Plus: "),
                   lblSupreme = new JLabel("Supreme: ");

    // text fields
    private JTextField txtRegular = new JTextField(10),
                       txtPlus    = new JTextField(10),
                       txtSupreme = new JTextField(10);

    // buttons
    private JButton btnSave   = new JButton("Save"),
                    btnCancel = new JButton("Cancel");

    private Font defaultFont = new Font("Segeo UI", Font.BOLD, 14);

    // constructor
    public PumpManagement() {
        // get the prices of the gases
        //getGasPrices();
        generateGUI();
    }

    private void generateGUI() {
        add(new JLabel("Success!"));

        setLayout(new BorderLayout());

        lblRegular.setFont(defaultFont);
        lblPlus.setFont(defaultFont);
        lblSupreme.setFont(defaultFont);

        txtRegular.setFont(defaultFont);
        txtPlus.setFont(defaultFont);
        txtSupreme.setFont(defaultFont);

        // Center Upper Panel
        pnlNorth = new JPanel();
        pnlNorth.setLayout(new GridLayout(3, 2));
        pnlNorth.setBorder(new EmptyBorder(10, 50, 10, 50));
        pnlNorth.add(lblRegular);
        pnlNorth.add(txtRegular);
        pnlNorth.add(lblPlus);
        pnlNorth.add(txtPlus);
        pnlNorth.add(lblSupreme);
        pnlNorth.add(txtSupreme);

        // Center Lower Panel
        pnlSouth = new JPanel();
        pnlSouth.setLayout(new FlowLayout());
        pnlSouth.setBorder(new EmptyBorder(10, 50, 10, 50));
        pnlSouth.add(btnSave);
        pnlSouth.add(btnCancel);

        // Center Main Panel
        pnlMain = new JPanel();
        pnlMain.add(pnlNorth, BorderLayout.NORTH);
        pnlMain.add(pnlSouth, BorderLayout.SOUTH);

        add(pnlMain);

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
