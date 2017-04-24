package PumpSoftware;

// import the services to work with PumpSystem's database
import PumpDB.db;
import javafx.beans.binding.DoubleExpression;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * PumpSystem Management class to manage the prices of the gases
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

    // instantiate the variable to set form
    private Font defaultFont = new Font("Segeo UI", Font.BOLD, 16);

    // constructor
    public PumpManagement() {
        // get the prices of the gases
        //getGasPrices();
        generateGUI();
    }

    // this method is used to create the GUI for PumpSystem Mngt Tab
    private void generateGUI() {
        add(new JLabel("Success!"));

        // set the main layout
        setLayout(new BorderLayout());

        // set font of labels
        lblRegular.setFont(defaultFont);
        lblPlus.setFont(defaultFont);
        lblSupreme.setFont(defaultFont);
        // set font of textFields
        txtRegular.setFont(defaultFont);
        txtPlus.setFont(defaultFont);
        txtSupreme.setFont(defaultFont);

        // set size of txtFields
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

        // Event handler for update button
        btnUpdate.addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent event){
                        try
                        {
                            if(regularPrice != Double.parseDouble(txtRegular.getText()) || plusPrice != Double.parseDouble(txtPlus.getText()) || supremePrice != Double.parseDouble(txtSupreme.getText()))
                            {
                                // call the update price method which is connected to the database
                                updatePrices();
                                // display user success message
                                JOptionPane.showMessageDialog(null, "The price was successfully updated.", "Update successfully!", JOptionPane.INFORMATION_MESSAGE);
                            }
                            else if (txtRegular.getText().isEmpty() || txtPlus.getText().isEmpty() || txtSupreme.getText().isEmpty()){
                                //btnUpdate.setEnabled(false);
                                JOptionPane.showMessageDialog(null, "Please enter valid price.", "Invalid Price", JOptionPane.WARNING_MESSAGE);
                            }
                            else{
                                //btnUpdate.setEnabled(false);
                                JOptionPane.showMessageDialog(null, "Please update the price.", "Update Price", JOptionPane.WARNING_MESSAGE);
                            }
                        }
                        catch (Exception error)
                        {

                        }

                    }
                }
        );

        pnlSouth.add(btnCancel);
        // Event handler for cancel button to exit the program
        btnCancel.addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent event){
                        // display the user confirm message then exit if OK
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

        // call the method from db class & populate with values
        db.updateGasPrices(Double.parseDouble(txtRegular.getText()), Double.parseDouble(txtPlus.getText()), Double.parseDouble(txtSupreme.getText()));

        // create an ArrayList of Double to store price
        ArrayList<Double> updatePrices = new ArrayList<>();

        // set the variable's = to the text in textFields & also parse to Double
        Double regular = Double.parseDouble(txtRegular.getText());
        Double plus = Double.parseDouble(txtPlus.getText());
        Double supreme = Double.parseDouble(txtSupreme.getText());

        // add the values to the ArrayList
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

} // end of class
