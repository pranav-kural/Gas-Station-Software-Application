package PumpSoftware;

import PumpDB.db;

import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GlobalTank extends JPanel {
    private JLabel lblTitle1, lblRegular1, lblPlus1, lblSupreme1,lblTitle2, lblRegular2, lblPlus2, lblSupreme2;
    private JTextField txtRegular1, txtPlus1, txtSupreme1, txtRegular2, txtPlus2, txtSupreme2;
    private JPanel pnlNorthMain, pnlUpperNorth, pnlLowerNorth, pnlSouthMain, pnlUpperSouth, pnlLowerSouth,pnlLowerSouth1;
    private JButton btnExit;

    public GlobalTank(){
        setLayout(new BorderLayout());
        setSize(300,280);


        pnlNorthMain = new JPanel();
        pnlNorthMain.setLayout(new BorderLayout());
        pnlNorthMain.setBorder(BorderFactory.createRaisedBevelBorder());
        add(pnlNorthMain, BorderLayout.NORTH);


        pnlUpperNorth = new JPanel();
        lblTitle1 = new JLabel("Gas SOLD (Liters)");
        lblTitle1.setFont(new Font("Arial", Font.BOLD, 16));
        pnlNorthMain.add(pnlUpperNorth, BorderLayout.NORTH);
        pnlNorthMain.setPreferredSize(new Dimension(120,100));
        pnlUpperNorth.add(lblTitle1);

        pnlLowerNorth = new JPanel();
        lblRegular1 = new JLabel("Regular:");
        lblRegular1.setFont(new Font("Arial", Font.BOLD, 14));
        lblPlus1 = new JLabel("Plus:");
        lblPlus1.setFont(new Font("Arial", Font.BOLD, 14));
        lblSupreme1 = new JLabel("Supreme:");
        lblSupreme1.setFont(new Font("Arial", Font.BOLD, 14));
        txtRegular1 = new JTextField();
        txtRegular1.setFont(new Font("Arial", Font.BOLD, 14));
        txtPlus1 = new JTextField();
        txtPlus1.setFont(new Font("Arial", Font.BOLD, 14));
        txtSupreme1 = new JTextField();
        txtSupreme1.setFont(new Font("Arial", Font.BOLD, 14));
        pnlLowerNorth.setLayout(new GridLayout(3,2));
        pnlLowerNorth.setBorder(BorderFactory.createRaisedBevelBorder());
        pnlNorthMain.add(pnlLowerNorth, BorderLayout.SOUTH);
        pnlLowerNorth.add(lblRegular1);
        pnlLowerNorth.add(txtRegular1);
        pnlLowerNorth.add(lblPlus1);
        pnlLowerNorth.add(txtPlus1);
        pnlLowerNorth.add(lblSupreme1);
        pnlLowerNorth.add(txtSupreme1);


        pnlSouthMain = new JPanel();
        pnlSouthMain.setLayout(new BorderLayout());
        pnlSouthMain.setBorder(BorderFactory.createRaisedBevelBorder());
        add(pnlSouthMain, BorderLayout.SOUTH);

        pnlUpperSouth = new JPanel();
        lblTitle2 = new JLabel("Gas AVAILABLE (Liters)");
        lblTitle2.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitle2.setHorizontalAlignment(SwingConstants.LEFT);
        pnlSouthMain.add(pnlUpperSouth, BorderLayout.NORTH);
        pnlUpperSouth.add(lblTitle2);

        pnlLowerSouth = new JPanel();
        lblRegular2 = new JLabel("Regular:");
        lblRegular2.setFont(new Font("Arial", Font.BOLD, 14));
        lblPlus2 = new JLabel("Plus:");
        lblPlus2.setFont(new Font("Arial", Font.BOLD, 14));
        lblSupreme2 = new JLabel("Supreme:");
        lblSupreme2.setFont(new Font("Arial", Font.BOLD, 14));
        txtRegular2 = new JTextField();
        txtRegular2.setFont(new Font("Arial", Font.BOLD, 14));
        txtPlus2 = new JTextField();
        txtPlus2.setFont(new Font("Arial", Font.BOLD, 14));
        txtSupreme2 = new JTextField();
        txtSupreme2.setFont(new Font("Arial", Font.BOLD, 14));
        pnlLowerSouth.setLayout(new GridLayout(3,2));
        pnlLowerSouth.setBorder(BorderFactory.createRaisedBevelBorder());
        pnlSouthMain.add(pnlLowerSouth, BorderLayout.CENTER);
        pnlLowerSouth.add(lblRegular2);
        pnlLowerSouth.add(txtRegular2);
        pnlLowerSouth.add(lblPlus2);
        pnlLowerSouth.add(txtPlus2);
        pnlLowerSouth.add(lblSupreme2);
        pnlLowerSouth.add(txtSupreme2);

        pnlLowerSouth1 = new JPanel();
        btnExit = new JButton("Exit");
        btnExit.addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent event){
                        if(JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit?", JOptionPane.YES_NO_OPTION)==0)
                        {
                            System.exit(0);
                        }
                    }
                }
        );

        pnlLowerSouth1.setBorder(BorderFactory.createRaisedBevelBorder());
        pnlSouthMain.add(pnlLowerSouth1, BorderLayout.SOUTH);
        pnlLowerSouth1.add(btnExit);


        // Display the gas prices information
        displayGasPriceInfo();

        // fill gas quantity data
        displayGasQuantityInfo();

    }//end of constructor

    // Display the gas prices in the text boxes
    private void displayGasPriceInfo() {
        // get the gas prices
        Double[] gasPrices = db.getGasPrices().toArray(new Double[3]);

        // fill the gas prices to the text boxes
        this.txtRegular1.setText(String.valueOf(gasPrices[0]));
        this.txtPlus1.setText(String.valueOf(gasPrices[1]));
        this.txtSupreme1.setText(String.valueOf(gasPrices[2]));
    }

    // Display the gas quantities in the text boxes
    private void displayGasQuantityInfo() {

        // get the gas quantity info
        Double[] gasQuantities = db.getGasQuantity().toArray(new Double[3]);

        // fill the data to text boxes
        this.txtRegular2.setText(String.valueOf(gasQuantities[0]));
        this.txtPlus2.setText(String.valueOf(gasQuantities[1]));
        this.txtSupreme2.setText(String.valueOf(gasQuantities[2]));

    }

}//end of class
