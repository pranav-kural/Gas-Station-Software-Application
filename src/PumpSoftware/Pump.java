package PumpSoftware;

import PumpDB.db;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class Pump extends JFrame {
    //text fields
    private JTextField txtRegular, txtPlus, txtSupreme;

    // JLabels
    private JLabel lblTitle, lblHundredUpper, lblTenUpper, lblOneUpper, lblCentOneUpper,
            lblCentTwoUpper, lblHundredLower, lblTenLower, lblOneLower, lblCentOneLower,
            lblCentTwoLower, lblWest, lblEast, lblCentre, lblSouthMain, lblCentreSouthUpper,
            lblCentreSouthMiddle, lblCentreSouthLower, lblSouthMiddleNorth;

    // JPanels
    private JPanel pnlNorth, pnlWest, pnlCentre, pnlEast, pnlCentreNorth,
            pnlCentreNorthUpper, pnlCentreNorthLower, pnlCentreNorthCentre,
            pnlCentreSouth, pnlSouthMain, pnlSouthLowerNorth, pnlSouthLowerSouth,
            pnlSouthUpper, pnlSouthMiddle, pnlSouthLower,
            pnlSouthMiddleNorth, pnlSouthMiddleSouth;

    //JSlider for south panel
    private JSlider jslSouth;

    private JButton btnRegular, btnPlus, btnSupreme;

    //JRadioButtons and button group
    private JRadioButton rdRegular, rdPlus, rdSupreme;
    private ButtonGroup rdGroup;

    //JButtons
    private JButton btnExit, btnPump;

    private boolean isPumping = false;
    private Double presetAmount = 0.0;
    private Double displayedAmount = 0.0;
    private Double pricePerLiter = 0.0;
    private Double litreLimit = 0.0;

    public Pump() {
        super("Pump");
        setLayout(new BorderLayout());
        setSize(500, 550);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        txtRegular = new JTextField(5);
        txtPlus = new JTextField(5);
        txtSupreme= new JTextField(5);

        // Set the value of text boxes displaying Gas prices per liter
        setGasAmount();

        txtRegular.setFont(new Font("Arial", Font.BOLD, 20));
        txtPlus.setFont(new Font("Arial", Font.BOLD, 20));
        txtSupreme.setFont(new Font("Arial", Font.BOLD, 20));


        lblTitle = new JLabel("SUNOCO");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));

        lblHundredUpper = new JLabel("0");
        lblHundredUpper.setHorizontalAlignment(SwingConstants.CENTER);
        lblHundredUpper.setFont(new Font("Arial", Font.BOLD, 48));

        lblTenUpper = new JLabel("0");
        lblTenUpper.setHorizontalAlignment(SwingConstants.CENTER);
        lblTenUpper.setFont(new Font("Arial", Font.BOLD, 48));

        lblOneUpper = new JLabel("0");
        lblOneUpper.setHorizontalAlignment(SwingConstants.CENTER);
        lblOneUpper.setFont(new Font("Arial", Font.BOLD, 48));

        lblCentOneUpper = new JLabel("0");
        lblCentOneUpper.setHorizontalAlignment(SwingConstants.CENTER);
        lblCentOneUpper.setFont(new Font("Arial", Font.BOLD, 24));

        lblCentTwoUpper = new JLabel("0");
        lblCentTwoUpper.setHorizontalAlignment(SwingConstants.CENTER);
        lblCentTwoUpper.setFont(new Font("Arial", Font.BOLD, 24));

        lblCentOneLower = new JLabel("0");
        lblCentOneLower.setHorizontalAlignment(SwingConstants.CENTER);
        lblCentOneLower.setFont(new Font("Arial", Font.BOLD, 24));

        lblCentTwoLower = new JLabel("0");
        lblCentTwoLower.setHorizontalAlignment(SwingConstants.CENTER);
        lblCentTwoLower.setFont(new Font("Arial", Font.BOLD, 24));

        lblCentre = new JLabel("This Sale $");
        lblCentre.setFont(new Font("Arial", Font.BOLD, 18));
        lblCentreSouthUpper = new JLabel("Litres");
        lblCentreSouthUpper.setHorizontalAlignment(SwingConstants.CENTER);
        lblCentreSouthUpper.setFont(new Font("Arial", Font.BOLD, 20));
        lblCentreSouthMiddle = new JLabel("0.00");
        lblCentreSouthMiddle.setHorizontalAlignment(SwingConstants.CENTER);
        lblCentreSouthMiddle.setFont(new Font("Arial", Font.BOLD, 20));
        lblCentreSouthLower = new JLabel("Price Per Litre c");
        lblCentreSouthLower.setHorizontalAlignment(SwingConstants.CENTER);
        lblCentreSouthLower.setFont(new Font("Arial", Font.BOLD, 20));

        lblHundredLower = new JLabel("0");
        lblHundredLower.setHorizontalAlignment(SwingConstants.CENTER);
        lblHundredLower.setFont(new Font("Arial", Font.BOLD, 48));

        lblTenLower = new JLabel("0");
        lblTenLower.setHorizontalAlignment(SwingConstants.CENTER);
        lblTenLower.setFont(new Font("Arial", Font.BOLD, 48));

        lblOneLower = new JLabel("0");
        lblOneLower.setHorizontalAlignment(SwingConstants.CENTER);
        lblOneLower.setFont(new Font("Arial", Font.BOLD, 48));

        lblWest = new JLabel();
        lblEast = new JLabel();
        lblSouthMain = new JLabel();

        lblSouthMiddleNorth = new JLabel("Preset Purchase Amount");
        lblSouthMiddleNorth.setFont(new Font("Arial", Font.BOLD, 20));

        rdRegular = new JRadioButton("Regular", true);
        rdRegular.setActionCommand(txtRegular.getText());
        rdPlus = new JRadioButton("Plus", false);
        rdPlus.setActionCommand(txtRegular.getText());
        rdSupreme = new JRadioButton("Supreme", false);
        rdSupreme.setActionCommand(txtRegular.getText());

        rdGroup = new ButtonGroup();
        rdGroup.add(rdRegular);
        rdGroup.add(rdPlus);
        rdGroup.add(rdSupreme);

        btnExit = new JButton("Exit");
        btnPump = new JButton("Start");

        // create north region panel
        pnlNorth = new JPanel();
        pnlNorth.add(lblTitle);
        pnlNorth.setBorder(BorderFactory.createRaisedBevelBorder());

        pnlWest = new JPanel();
        pnlWest.add(lblWest);
        pnlWest.setBorder(BorderFactory.createRaisedBevelBorder());

        pnlEast = new JPanel();
        pnlEast.add(lblEast);
        pnlEast.setBorder(BorderFactory.createRaisedBevelBorder());

        pnlCentre = new JPanel();
        pnlCentreNorth = new JPanel();
        pnlCentreSouth = new JPanel();
        pnlCentreNorthUpper = new JPanel();
        pnlCentreNorthUpper.setLayout(new GridLayout(0, 5));
        pnlCentreNorthLower = new JPanel();
        pnlCentreNorthLower.setLayout(new GridLayout(0, 5));
        pnlCentreNorthCentre = new JPanel();
        pnlCentre.setLayout(new BorderLayout());
        pnlCentreNorth.setLayout(new BorderLayout());
        pnlCentreSouth.setLayout(new GridLayout(0, 1, 25, 25));
        pnlCentre.setBorder(BorderFactory.createRaisedBevelBorder());
        pnlCentreNorthUpper.setBorder(BorderFactory.createRaisedBevelBorder());
        pnlCentreNorthLower.setBorder(BorderFactory.createRaisedBevelBorder());
        pnlCentreNorthUpper.add(lblHundredUpper);
        pnlCentreNorthUpper.add(lblTenUpper);
        pnlCentreNorthUpper.add(lblOneUpper);
        pnlCentreNorthUpper.add(lblCentTwoUpper);
        pnlCentreNorthUpper.add(lblCentOneUpper);

        pnlCentreNorthLower.add(lblHundredLower);
        pnlCentreNorthLower.add(lblTenLower);
        pnlCentreNorthLower.add(lblOneLower);
        pnlCentreNorthLower.add(lblCentTwoLower);
        pnlCentreNorthLower.add(lblCentOneLower);

        pnlCentreNorthCentre.add(lblCentre);

        pnlCentreNorth.add(pnlCentreNorthUpper, BorderLayout.NORTH);
        pnlCentreNorth.add(pnlCentreNorthCentre, BorderLayout.CENTER);
        pnlCentreNorth.add(pnlCentreNorthLower, BorderLayout.SOUTH);

        lblCentreSouthUpper.setVerticalAlignment(SwingConstants.TOP);
        lblCentreSouthMiddle.setVerticalAlignment(SwingConstants.CENTER);
        lblCentreSouthLower.setVerticalAlignment(SwingConstants.BOTTOM);
        pnlCentreSouth.add(lblCentreSouthUpper);
        pnlCentreSouth.add(lblCentreSouthMiddle);
        pnlCentreSouth.add(lblCentreSouthLower);


        pnlCentre.add(pnlCentreNorth, BorderLayout.NORTH);
        pnlCentre.add(pnlCentreSouth, BorderLayout.SOUTH);

        pnlSouthMain = new JPanel();
        pnlSouthMain.setLayout(new BorderLayout());

        pnlSouthUpper = new JPanel();
        pnlSouthMiddle = new JPanel();
        pnlSouthLower = new JPanel();

        pnlSouthMiddleNorth = new JPanel();
        pnlSouthMiddleSouth = new JPanel();

        //set the layout of south upper panel to grid
        pnlSouthUpper.setLayout(new GridLayout(2, 3));
        pnlSouthUpper.setBorder(BorderFactory.createRaisedBevelBorder());
        pnlSouthUpper.add(txtRegular);
        pnlSouthUpper.add(txtPlus);
        pnlSouthUpper.add(txtSupreme);

        btnRegular = new JButton("Regular");
        btnPlus = new JButton("Plus");
        btnSupreme = new JButton("Supreme");
        pnlSouthUpper.add(rdRegular);
        pnlSouthUpper.add(rdPlus);
        pnlSouthUpper.add(rdSupreme);

        //set the layout of middle panel in the south panel to border
        pnlSouthLower.setLayout(new BorderLayout());

        pnlSouthMiddle.add(lblSouthMiddleNorth);

        jslSouth = new JSlider(0, 200, 0);
        jslSouth.setMinorTickSpacing(5);
        jslSouth.setMajorTickSpacing(25);
        jslSouth.setPaintTicks(true);
        jslSouth.setPaintLabels(true);

        pnlSouthLowerNorth = new JPanel();
        pnlSouthLowerNorth.setBorder(BorderFactory.createRaisedBevelBorder());

        pnlSouthLowerSouth = new JPanel();
        pnlSouthLowerSouth.add(btnPump);
        pnlSouthLowerSouth.add(btnExit);

        pnlSouthLower.add(jslSouth, BorderLayout.NORTH);
        jslSouth.setBorder(BorderFactory.createRaisedBevelBorder());
        pnlSouthLower.add(pnlSouthLowerSouth, BorderLayout.SOUTH);

        pnlSouthMain.add(pnlSouthUpper, BorderLayout.NORTH);
        pnlSouthMain.add(pnlSouthMiddle, BorderLayout.CENTER);
        pnlSouthMain.add(pnlSouthLower, BorderLayout.SOUTH);

        btnExit.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        if (JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit?", JOptionPane.YES_NO_OPTION) == 0) {
                            // yes=0, no=1
                            setVisible(false); // hide the form
                        }
                    }
                }
        );

        btnPump.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        // if pump is already pumping
                        if (isPumping) {
                            JOptionPane.showMessageDialog(null, "The pump already working. Please complete the transaction before starting a new one. Thanks.", "Already Pumping", JOptionPane.WARNING_MESSAGE);
                        }
                        else if (JOptionPane.showConfirmDialog(null, "Are you sure you want to start pumping?", "Start Pumping?", JOptionPane.YES_NO_OPTION) == 0) {
                            JOptionPane.showMessageDialog(null, "Pumping has started");
                            presetAmount = Double.parseDouble(lblCentreSouthMiddle.getText());
                            //changeCentOneUpperValue();
                            updateDisplayAmount();
                            pricePerLiter = Double.parseDouble(rdGroup.getSelection().getActionCommand());
                            Double val = Math.round(presetAmount / (pricePerLiter / 100) * 100.0) / 100.0;
                            setLitreLimit(val);
                            runThePump();

                        }
                    }
                }
        );

        jslSouth.addChangeListener(new ChangeListener()
        {
            @Override
            public void stateChanged(ChangeEvent event)
            {
                DecimalFormat df = new DecimalFormat("0.00");
                int value = jslSouth.getValue();
                lblCentreSouthMiddle.setText(df.format(value));
            }

        });

        //add to frame region
        add(pnlNorth, BorderLayout.NORTH);
        add(pnlWest, BorderLayout.WEST);
        add(pnlEast, BorderLayout.EAST);
        add(pnlCentre, BorderLayout.CENTER);
        add(pnlSouthMain, BorderLayout.SOUTH);


        setVisible(true);


    }// end of constructor

    private void setLitreLimit(Double limit) {
        this.litreLimit = limit;
    }


    private void setGasAmount() {
        Double[] gasPrices = db.getGasPrices().toArray(new Double[3]);
        txtRegular.setText(String.valueOf(gasPrices[0]));
        txtPlus.setText(String.valueOf(gasPrices[1]));
        txtSupreme.setText(String.valueOf(gasPrices[2]));

        // set the textboxes to read only
        txtRegular.setEditable(false);
        txtPlus.setEditable(false);
        txtSupreme.setEditable(false);
    }


    // Update the amount being displayed
    private void setDisplayedAmount() {
        this.displayedAmount =
                Double.parseDouble(lblHundredUpper.getText() + lblTenUpper.getText() + lblOneUpper.getText() + lblCentTwoUpper.getText() + lblCentOneUpper.getText());
        System.out.println(String.valueOf(displayedAmount));
    }

    // Update the amount being displayed
    private boolean underLitersLimit() {
        Double litersFilledByNow =  Double.parseDouble(lblHundredLower.getText() + lblTenLower.getText() + lblOneLower.getText() + "." + lblCentTwoLower.getText() + lblCentOneLower.getText());
        return (litersFilledByNow < this.litreLimit);
    }

    private boolean checkPresetLimit() {
        return (this.displayedAmount == this.presetAmount);
    }

    private void runThePump() {

        for (int i = 1; i <= 10; i++) {

            // if cent one is 9 currently
            if (i == 10) {
                lblCentOneLower.setText("0");

                if (lblCentTwoLower.getText().equals("9")) {

                    // set the cent two to 0
                    lblCentTwoLower.setText("0");

                    int onesValue = Integer.parseInt(lblOneLower.getText());

                    if (onesValue == 9) {
                        lblOneLower.setText("0");

                        // get the tens value
                        int tensValue = Integer.parseInt(lblTenLower.getText());

                        if (tensValue == 9) {
                            lblTenLower.setText("0");

                            // increment tens value by 1
                            lblHundredLower.setText(String.valueOf(Integer.parseInt(lblHundredLower.getText()) + 1));
                            if (!underLitersLimit()) {
                                break; // exit the loop
                            }


                        } else {
                            // increment tens value by 1
                            lblTenLower.setText(String.valueOf(tensValue + 1));
                            if (!underLitersLimit()) {
                                break; // exit the loop
                            }
                        }

                        // increment the tens value
                    } else {
                        // increment ones value by 1
                        lblOneLower.setText(String.valueOf(onesValue + 1));
                        if (!underLitersLimit()) {
                            break; // exit the loop
                        }
                    }

                } else {
                    lblCentTwoLower.setText(String.valueOf(Integer.parseInt(lblCentTwoLower.getText()) + 1));
                    if (!underLitersLimit()) {
                        break; // exit the loop
                    }
                }

                // reset i
                i = 0;
            } else {
                lblCentOneLower.setText(String.valueOf(i));
                if (!underLitersLimit()) {
                    break; // exit the loop
                }
            }
        }

    }




    private void updateDisplayAmount() {
        // get each number in the amount
        String[] amountDigits = String.valueOf(this.presetAmount).split("");
        if (amountDigits.length > 4) {
            lblHundredUpper.setText(amountDigits[0]);
            lblTenUpper.setText(amountDigits[1]);
            lblOneUpper.setText(amountDigits[2]);
        } else if (amountDigits.length > 3){
            lblTenUpper.setText(amountDigits[0]);
            lblOneUpper.setText(amountDigits[1]);
        } else {
            lblOneUpper.setText(amountDigits[0]);
        }

    }


    private void waitFor(long time) {
        try {
            Thread.sleep(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





    public void changeCentOneUpperValue()
    {
        for(int i = 1; i <= 10; i++)
        {

            if (checkPresetLimit()) {
                break; // exit the loop
            }

            //wait(50);
            if(i == 10)
            {
                lblCentOneUpper.setText(Integer.toString(0));
                if (checkPresetLimit()) {
                    break; // exit the loop
                }
                changeCentTwoUpperValue();
            }

            lblCentOneUpper.setText(Integer.toString(i));

        } //end of for loop
    }// end of method

    public void changeCentTwoUpperValue()
    {
        for(int i = 1; i <= 10; i++)
        {
            if (checkPresetLimit()) {
                break; // exit the loop
            }

            if(i == 10)
            {
                lblCentTwoUpper.setText(Integer.toString(0));
                if (checkPresetLimit()) {
                    break; // exit the loop
                }
                changeOneUpperValue();
                break; // exit the loop
            }
            else
            {
                lblCentTwoUpper.setText(Integer.toString(i));
            }
        }//end of for loop
    }//end of method

    public void changeOneUpperValue()
    {
        for(int i = 1; i <= 10; i++)
        {

            if (checkPresetLimit()) {
                break; // exit the loop
            }

            if(i == 10)
            {
                lblOneUpper.setText(Integer.toString(0));
                if (checkPresetLimit()) {
                    break; // exit the loop
                }
                changeTenUpperValue();
                break; // exit the loop
            }
                lblOneUpper.setText(Integer.toString(i));
        }//end of for loop
    }//end of method

    public void changeTenUpperValue()
    {


        for(int i = 1; i <= 10; i++)
        {

            if (checkPresetLimit()) {
                break; // exit the loop
            }

            if(i == 10)
            {
                lblTenUpper.setText(Integer.toString(0));
                if (checkPresetLimit()) {
                    break; // exit the loop
                }
                changeHundredUpperValue();
            }
            else
            {
                lblTenUpper.setText(Integer.toString(i));
            }
        }//end of for loop
    }//end of method

    public void changeHundredUpperValue()
    {
        for(int i = 1; i <= 10; i++)
        {

            if (checkPresetLimit()) {
                break; // exit the loop
            }

            if(i == 10)
            {
                lblHundredUpper.setText(Integer.toString(0));
                if (checkPresetLimit()) {
                    break; // exit the loop
                }
                changeCentOneUpperValue();
            }
            else
            {
                lblHundredUpper.setText(Integer.toString(i));
            }
        }//end of for loop
    }//end of method

    public static void main(String[] args) {
        Pump ob = new Pump();
    }// end of main

    private class sliderChangeListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {

        }
    }

}// end of class


