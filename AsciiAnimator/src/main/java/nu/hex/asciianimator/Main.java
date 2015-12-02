package nu.hex.asciianimator;

import java.io.File;
import nu.hex.asciianimator.gui.MainWindow;
import nu.hex.asciianimator.io.SettingsResource;
import nu.hex.asciianimator.io.SimpleFileWriter;

/**
 *
 * @author hl
 */
public class Main {

    public static void main(String[] args) {
        handleSettings();
        new MainWindow().openAnimatorWindow();
    }

    private static void handleSettings() {
        File settingsDirectory = new File(System.getProperty("user.home") + "/.asciianimator");
        settingsDirectory.mkdirs();
        if (!new File(settingsDirectory.getAbsolutePath() + "/asciianimator.properties").exists()) {
            File properties = new File(settingsDirectory.getAbsolutePath() + "/asciianimator.properties");
            new SimpleFileWriter(new SettingsResource().getPropertiesContent(), properties).write();
        }
    }
}
