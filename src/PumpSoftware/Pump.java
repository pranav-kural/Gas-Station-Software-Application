package PumpSoftware;


import javax.swing.*;

public class Pump extends JFrame {

    public Pump() {
        generateGUI();
        setTitle("Pump System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 300);
        setLocationRelativeTo(null); // center location for form
        setVisible(true);
    }

    private void generateGUI() {

        add(new PumpManagement());

    }

}
