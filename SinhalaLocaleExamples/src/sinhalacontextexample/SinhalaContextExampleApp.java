/*
 * SinhalaContextExampleApp.java
 */

package sinhalacontextexample;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class SinhalaContextExampleApp extends SingleFrameApplication {

    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
        show(new SinhalaContextExampleView(this));
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of SinhalaContextExampleApp
     */
    public static SinhalaContextExampleApp getApplication() {
        return Application.getInstance(SinhalaContextExampleApp.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        launch(SinhalaContextExampleApp.class, args);

        /*
        
        Date now = new Date();        
        // Display using the Antarctica locale format
        DateFormat antarcticaFormat =
                DateFormat.getDateTimeInstance(
                DateFormat.FULL, DateFormat.FULL,
                new Locale("si", "LK"));

        String antarcticaString = antarcticaFormat.format(now);
        System.out.println("Sri Lanka: " + antarcticaString);
        */
    }
}
