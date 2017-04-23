package PumpSoftware;

// import the services to work with Pump's database
import PumpDB.db;
import Utilities.Services;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Login Form class to display a login form
 * Without passing the login authentication
 * user won't be able to see or access Pump System
 */
public class LoginForm extends JFrame {

    // To ascertain whether the user has managerial_privileges or not
    private boolean managerial_priviligies = false;

    // An instance of the service class to use a non-static service method
    private Services services = new Services();

    // Default font style for most of the elements on the login form
    private Font defaultFont = new Font("Segeo UI", Font.BOLD, 14);

    /**
     * LoginForm Constructor
     * Creates the LoginForm GUI and makes it visible
     */
    public LoginForm() {

        // Generate the login form
        generateLoginForm();
        setTitle("Login Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 300);
        setLocationRelativeTo(null); // center location for form
        setVisible(true); // make the form visible
    }

    /**
     * Method to assemble the LoginForm GUI and add event listeners
     */
    private void generateLoginForm() {

        // Root frame layout
        setLayout(new BorderLayout());

        // Top panel
        JPanel topPanel = new JPanel();
        JLabel lblLogo = new JLabel(new ImageIcon(services.createImageIcon("Resources/Esso.png", "Logo image of the pump").getImage().getScaledInstance(80, 60,  Image.SCALE_DEFAULT)), JLabel.CENTER);
        topPanel.add(lblLogo);
        topPanel.setBorder(new EmptyBorder(10, 50, 10, 50));
        add(topPanel, BorderLayout.NORTH);

        // Center panel
        JPanel centerPanel = new JPanel(new BorderLayout());
        JPanel centerPanelFirst = new JPanel(new GridLayout(2, 1));
        JPanel centerPanelSecond = new JPanel(new BorderLayout());
        JLabel lblUserId = new JLabel("User ID: ", JLabel.RIGHT);
        JLabel lblpassword = new JLabel("Password: ", JLabel.RIGHT);
        lblUserId.setFont(defaultFont);
        lblpassword.setFont(defaultFont);
        JTextField userIDtxtField = new JTextField(20);
        JPasswordField passwordtxtField = new JPasswordField(20);
        userIDtxtField.setFont(defaultFont);
        passwordtxtField.setFont(defaultFont);
        centerPanelFirst.add(lblUserId); centerPanelFirst.add(lblpassword);
        centerPanelSecond.add(userIDtxtField, BorderLayout.NORTH); centerPanelSecond.add(passwordtxtField, BorderLayout.SOUTH);
        centerPanel.add(centerPanelFirst, BorderLayout.CENTER); centerPanel.add(centerPanelSecond, BorderLayout.EAST);
        centerPanel.setBorder(new EmptyBorder(10, 50, 10, 50));
        add(centerPanel, BorderLayout.CENTER);

        // Bottom panel
        JPanel bottomPanel = new JPanel();
        JButton btnLogin = new JButton("Login");
        JButton btnCancel = new JButton("Cancel");
        bottomPanel.add(btnLogin);
        bottomPanel.add(btnCancel);
        bottomPanel.setBorder(new EmptyBorder(10, 50, 50, 50));
        add(bottomPanel, BorderLayout.SOUTH);

        // Add the event listener to Login button
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // check if form fields has filled or are empty
                if (userIDtxtField.getText().isEmpty() || new String(passwordtxtField.getPassword()).isEmpty()) {
                    //display a failure message to the user
                    JOptionPane.showMessageDialog(null, "Please enter the "+ (userIDtxtField.getText().isEmpty() ? "User ID" : "password") + " to login.", "Invalid " + (userIDtxtField.getText().isEmpty() ? "User ID" : "password"), JOptionPane.WARNING_MESSAGE);
                } else {
                    // authenticate the user input
                    authenticateUser(userIDtxtField.getText().toString(), new String(passwordtxtField.getPassword()));
                }
            }
        });

        // Set login button as default form button (activates on pressing enter key)
        getRootPane().setDefaultButton(btnLogin);

        // Add event listener to the Cancel button
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Confirm the user to close the program
                if (JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel.", "Cencel", JOptionPane.YES_NO_OPTION)==0) {
                    System.exit(0);
                }
            }
        });
    }

    /**
     * Authenticate the user credentials
     * @param userid user ID entered by the User
     * @param password password entered by the user
     */
    private void authenticateUser(String userid, String password) {
        // get the user data based on id provided
        String[] userData = db.getUserDetails(userid);

        // if valid user details has been received
        if (userData != null) {
            // match the password
            if (userData[1].equals(password)) {
                // check and set if the user has managerial privileges or not
                this.managerial_priviligies = (userData[2].equalsIgnoreCase("y"));
                // Create a new instance of the Pump System which the logged in user can work on
                Pump pump = new Pump(this);
                // set this login form as a reference to the next form
                //pump.loginForm = this;
                // hide the login form
                setVisible(false);

            } else {
                //display a failure message to the user
                JOptionPane.showMessageDialog(null, "You entered the incorrect password. Please try again.", "Incorrect Password", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            //display a failure message to the user
            JOptionPane.showMessageDialog(null, "The user couldn't be found in our databases. Please check the User ID and try again.", "User Not Found", JOptionPane.WARNING_MESSAGE);
        }
    }

    // returns true if user's managerial privileges has been set to true
    public boolean isManager() {
        return this.managerial_priviligies;
    }

}
