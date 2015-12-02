package nu.hex.asciianimator.gui;

import java.awt.Component;
import java.io.File;
import java.util.Locale;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author hl
 */
public class AazFileChooser extends JFileChooser {

    private final Component parent;
    private final String path;

    public AazFileChooser(String path, Component parent) {
        this.parent = parent;
        this.path = path;
        setup();

    }

    private void setup() {
        this.setDialogTitle("Select file to open");
        this.setApproveButtonText("Open");
        this.setControlButtonsAreShown(true);
        this.setCurrentDirectory(new File(path));
        this.setAcceptAllFileFilterUsed(false);
        this.setFileHidingEnabled(true);
        this.setMultiSelectionEnabled(false);
        this.setLocale(new Locale("en"));
        this.addChoosableFileFilter(new FileNameExtensionFilter("ASCII animations files", "aaz"));
    }

    public File getFile() {
        showOpenDialog(parent);
        File result = getSelectedFile();
        return result;
    }
}
