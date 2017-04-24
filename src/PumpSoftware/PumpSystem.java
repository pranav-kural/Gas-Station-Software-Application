package PumpSoftware;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PumpSystem extends JFrame {

    // declare the tabbed pane
    private JTabbedPane tabContainer;

    // reference to the login form (parent form)
    public LoginForm loginForm;

    // PumpSystem constructor
    public PumpSystem(LoginForm loginForm) {
        // set the parent form reference
        this.loginForm = loginForm;
        // generate the PumpSystem GUI
        generateGUI();
        setTitle("PumpSystem System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null); // center location for form
        setVisible(true); // show the PumpSystem System GUI
    }

    // Generate the PumpSystem UI
    private void generateGUI() {

        // initialize the tab container
        tabContainer = new JTabbedPane();

        // reference to the global tank
        GlobalTank globalTank = new GlobalTank();

        // add the tabs
        tabContainer.addTab("PumpSystem Management", null, (loginForm.isManager() ? new PumpManagement() : new JLabel("You don't have access to this tab. You need managerial privileges to update the gas prices.", JLabel.CENTER)), "Update the gas prices.");
        tabContainer.addTab("Global Tank", null, globalTank, "Check the gas prices and quantity.");
        tabContainer.addTab("Launch PumpSystem", null, new JLabel("The pump will be launched soon.", JLabel.CENTER), "Fill gas.");

        tabContainer.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (tabContainer.getSelectedIndex() == 1) {
                    globalTank.displayGasQuantityInfo();
                    globalTank.displayGasPriceInfo();
                } else if(tabContainer.getSelectedIndex() == 2) {
                    Pump pump = new Pump();
                }
            }
        });


        // add the tabContainer to PumpSystem GUI
        add(tabContainer);
    }
}
