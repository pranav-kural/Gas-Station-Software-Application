package Utilities;


import javax.swing.*;

public class Services {

    public Services() {

    }

    /**
     * Returns an ImageIcon, or null if the path was invalid.
     * */
    public ImageIcon createImageIcon(String path, String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

}
