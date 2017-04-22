package PumpSoftware;

// import the services to work with Pump's database
import PumpDB.db;
import Utilities.Services;

import javax.swing.*;
import java.awt.*;

public class LoginForm extends JFrame {

    private String[] userData = new String[3];

    private Services services = new Services();

    public LoginForm() {

        // Generate the login form
        generateLoginForm();
    }

    private void generateLoginForm() {

        // Root frame layout
        setLayout(new BorderLayout());

        // Top panel
        JPanel topPanel = new JPanel();
        JLabel lblLogo = new JLabel(services.createImageIcon("Resources/Esso.png", "Logo image of the pump"), JLabel.CENTER);
        topPanel.add(lblLogo);
        add(topPanel, BorderLayout.NORTH);

        // Center panel
        JPanel centerPanel = new JPanel(new GridLayout(2, 2));
        JLabel lblUserId = new JLabel("User ID: ", JLabel.LEFT);

    }

    private void getUserData() {
        // get the user data
        userData = db.getUserDetails("10000");
    }

}
