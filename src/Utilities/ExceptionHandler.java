package Utilities;

/**
 * ExceptionHandler class to handle exceptions.
 * Writes the exception to exception log,
 * Informs the user, and
 * Terminates the application.
 */
public class ExceptionHandler extends Exception {

    // Constructor
    public ExceptionHandler(String message) {
        // call and passing the message to super class
        super(message);
    }

    // static method to quickly print out the message
    public static void printOut(String message) {
        // print out the error message
        System.out.println(message);
    }

}
