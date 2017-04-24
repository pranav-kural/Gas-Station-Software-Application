package PumpSoftware;

// import the services to work with Pump's database
import PumpDB.db;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
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
    private JButton btnUpdate   = new JButton("Update"),
                    btnCancel = new JButton("Cancel");

    private Font defaultFont = new Font("Segeo UI", Font.BOLD, 16);

    // constructor
    public PumpManagement() {
        // get the prices of the gases
        //getGasPrices();
        generateGUI();

    }

    private void generateGUI() {
        add(new JLabel("Success!"));

        setLayout(new BorderLayout());

        // set font of labels
        lblRegular.setFont(defaultFont);
        lblPlus.setFont(defaultFont);
        lblSupreme.setFont(defaultFont);
        // set font of textFields
        txtRegular.setFont(defaultFont);
        txtPlus.setFont(defaultFont);
        txtSupreme.setFont(defaultFont);

        // set size
        txtRegular.setPreferredSize(new Dimension(100, 35));
        txtPlus.setPreferredSize(new Dimension(100, 35));
        txtSupreme.setPreferredSize(new Dimension(100, 35));

        // North Panel
        pnlNorth = new JPanel();
        pnlNorth.setLayout(new GridLayout(3, 2, 0, 30));
        pnlNorth.setBorder(new EmptyBorder(50, 50, 10, 50));
        pnlNorth.add(lblRegular);
        pnlNorth.add(txtRegular);
        pnlNorth.add(lblPlus);
        pnlNorth.add(txtPlus);
        pnlNorth.add(lblSupreme);
        pnlNorth.add(txtSupreme);
        pnlNorth.setBorder(new TitledBorder("Update Gas Prices"));

        // South Panel
        pnlSouth = new JPanel();
        pnlSouth.setLayout(new FlowLayout());
        pnlSouth.setBorder(new EmptyBorder(20, 50, 10, 50));
        pnlSouth.add(btnUpdate);
        btnUpdate.addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent event){
                        if(Double.valueOf(regularPrice)!= Double.valueOf(txtRegular.getText()) || Double.valueOf(plusPrice)!= Double.valueOf(txtPlus.getText()) || Double.valueOf(supremePrice)!= Double.valueOf(txtSupreme.getText()))
                        {
                            updatePrices();
                            JOptionPane.showMessageDialog(null, "The price was successfully updated.", "Update successfully!", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Please update the price.", "Update Price", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
        );

        pnlSouth.add(btnCancel);
        btnCancel.addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent event){
                        if(JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel?", "Cancel?", JOptionPane.YES_NO_OPTION)==0)
                        {
                            System.exit(0);
                        }
                    }
                }
        );

        // Main Panel
        pnlMain = new JPanel();
        pnlMain.setBorder(new EmptyBorder(10, 50, 10, 50));
        pnlMain.add(pnlNorth, BorderLayout.NORTH);
        pnlMain.add(pnlSouth, BorderLayout.SOUTH);

        add(pnlMain);

        // calling getGasPrice method from db class
        getGasPrices();

    }

    // get gas prices from the database
    private void getGasPrices() {

        // get gas prices
        ArrayList<Double> gasPrices = db.getGasPrices();

        // set the gas prices
        regularPrice = gasPrices.get(0);    // price for regular gas type
        plusPrice = gasPrices.get(1);       // price for plus gas type
        supremePrice = gasPrices.get(2);    // price for supreme

        txtRegular.setText(Double.toString(regularPrice));
        txtPlus.setText(Double.toString(plusPrice));
        txtSupreme.setText(Double.toString(supremePrice));

    } // end getGasPrices

    // update gas prices from the database
    private void updatePrices() {

        db.updateGasPrices(Double.parseDouble(txtRegular.getText()), Double.parseDouble(txtPlus.getText()), Double.parseDouble(txtSupreme.getText()));

        ArrayList<Double> updatePrices = new ArrayList<>();

        Double regular = Double.parseDouble(txtRegular.getText());
        Double plus = Double.parseDouble(txtPlus.getText());
        Double supreme = Double.parseDouble(txtSupreme.getText());

        updatePrices.add(regular);
        updatePrices.add(plus);
        updatePrices.add(supreme);

    } // end updateGasPrices

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
