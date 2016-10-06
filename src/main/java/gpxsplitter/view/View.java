/*
 * The MIT License
 *
 * Copyright 2016 Antonino Cucchiara.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package gpxsplitter.view;

import gpxsplitter.model.Model;
import gpxsplitter.view.filter.GpxFileFilter;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.File;
import java.net.URI;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;

/**
 * GpxSplitter's Swing frontend.
 *
 * @author Antonio Cucchiara
 */
public class View {

    /**
     * The Gpx Splitter application name.
     */
    public static final String GPX_SPLITTER = "Gpx Splitter";
    /**
     * The cross-platform Line Separator.
     */
    public static final String LINE_SEPARATOR
            = System.getProperty("line.separator");
    /**
     * The Link to the online help system.
     */
    private static final String HELP_SYSTEM_LINK
            = "http://antocuc.github.com/GpxSplitterHelp.html";
    /**
     * The Link to the application's url.
     */
    private static final String GPX_SPLITTER_URL
            = "http://antocuc.github.com/GpxSplitter.html";

    /**
     * Large panel layout.
     */
    private static final GridLayout LARGE_PANE_LAYOUT
            = new GridLayout(4, 1);

    /**
     * Small panel layout.
     */
    private static final GridLayout SMALL_PAN_LAYOUT
            = new GridLayout(1, 2);

    /**
     * The UI frame.
     */
    private final JFrame frame;

    /**
     * The about menu item.
     */
    private final JMenuItem aboutMenuItem;
    /**
     * The browse gpx button.
     */
    private final JButton browseButton;
    /**
     * The exit menu item.
     */
    private final JMenuItem exitMenuItem;
    /**
     * The loaded file type value.
     */
    private final JLabel fileTypeValue;
    /**
     * The help menu item.
     */
    private final JMenuItem helpMenuItem;
    /**
     * The field allowing to gather the wished number of instructions.
     */
    private final JTextField instructionsNumberField;
    /**
     * The field displaying the loaded file path.
     */
    private final JTextField openFileField;
    /**
     * The button to save the split gpx.
     */
    private final JButton saveFileButton;

    /**
     * Application positioning bounds.
     */
    private static final Rectangle APP_BOUNDS = new Rectangle(0, 0, 350, 250);

    /**
     * Initialise the Gpx Splitter UI.
     *
     * @param model containing the business logic
     */
    public View(final Model model) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Error setting native LAF: " + e);
        }
        frame = new JFrame(GPX_SPLITTER);
        final JPanel inputFilePanel = new JPanel();
        openFileField = new JTextField();
        browseButton = new JButton();
        final JPanel inputFileFeaturesPanel = new JPanel();
        final JLabel fileTypeLabel = new JLabel();
        fileTypeValue = new JLabel();
        final JPanel ouputConfigurationPanel = new JPanel();
        final JLabel instructionsNumberLabel = new JLabel();
        instructionsNumberField = new JTextField();
        final JPanel outputFilePanel = new JPanel();
        final JLabel emptyLabel = new JLabel();
        saveFileButton = new JButton();
        final JMenuBar menuBar = new JMenuBar();
        final JMenu fileMenu = new JMenu();
        exitMenuItem = new JMenuItem();
        final JMenu helpMenu = new JMenu();
        helpMenuItem = new JMenuItem();
        aboutMenuItem = new JMenuItem();

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(
                View.class.getResource("/gpxsplitter/media/map.png")
        ));
        frame.setResizable(false);
        frame.getContentPane().setLayout(LARGE_PANE_LAYOUT);
        final TitledBorder openGpxTitledBorder
                = BorderFactory.createTitledBorder("Open Gpx file");

        inputFilePanel.setBorder(openGpxTitledBorder);
        inputFilePanel.setLayout(SMALL_PAN_LAYOUT);

        openFileField.setEnabled(false);
        inputFilePanel.add(openFileField);

        browseButton.setText("Browse...");
        inputFilePanel.add(browseButton);

        frame.getContentPane().add(inputFilePanel);
        final TitledBorder gpxFeaturesTitledBorder
                = BorderFactory.createTitledBorder("Gpx features");

        inputFileFeaturesPanel.setBorder(gpxFeaturesTitledBorder);
        inputFileFeaturesPanel.setLayout(SMALL_PAN_LAYOUT);

        fileTypeLabel.setText("Loaded gpx:");
        inputFileFeaturesPanel.add(fileTypeLabel);

        fileTypeValue.setText("N/A");
        inputFileFeaturesPanel.add(fileTypeValue);

        frame.getContentPane().add(inputFileFeaturesPanel);
        final TitledBorder outputConfigTitledBorder
                = BorderFactory.createTitledBorder("Output configuration");

        ouputConfigurationPanel.setBorder(outputConfigTitledBorder);
        ouputConfigurationPanel.setLayout(SMALL_PAN_LAYOUT);

        instructionsNumberLabel.setText("Instructions / file:");
        ouputConfigurationPanel.add(instructionsNumberLabel);

        instructionsNumberField.setText("125");
        ouputConfigurationPanel.add(instructionsNumberField);

        frame.getContentPane().add(ouputConfigurationPanel);
        final TitledBorder saveGpxTitledBorder
                = BorderFactory.createTitledBorder("Save Gpx files");

        outputFilePanel.setBorder(saveGpxTitledBorder);
        outputFilePanel.setLayout(SMALL_PAN_LAYOUT);
        outputFilePanel.add(emptyLabel);

        saveFileButton.setText("Save");
        outputFilePanel.add(saveFileButton);

        frame.getContentPane().add(outputFilePanel);

        fileMenu.setText("File");

        exitMenuItem.setText("Exit");
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setText("?");

        helpMenuItem.setText("Help");
        helpMenu.add(helpMenuItem);

        aboutMenuItem.setText("About");
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        frame.setJMenuBar(menuBar);

        frame.setBounds(APP_BOUNDS);
    }

    /**
     * Retrieves the browse button.
     *
     * @return the browse button
     */
    public final JButton getBrowseButton() {
        return browseButton;
    }

    /**
     * Retrieves the save button.
     *
     * @return the save button
     */
    public final JButton getSaveFileButton() {
        return saveFileButton;
    }

    /**
     * Retrieves the exit item.
     *
     * @return the exit menu item
     */
    public final JMenuItem getExitMenuItem() {
        return exitMenuItem;
    }

    /**
     * Retrieves the help item.
     *
     * @return the help menu item
     */
    public final JMenuItem getHelpMenuItem() {
        return helpMenuItem;
    }

    /**
     * Retrieves the about item.
     *
     * @return the about menu item
     */
    public final JMenuItem getAboutMenuItem() {
        return aboutMenuItem;
    }

    /**
     * Retrieves the number of instructions entered.
     *
     * @return the num of instructions
     */
    public final String getInstructionsNumberFieldText() {
        return instructionsNumberField.getText();
    }

    /**
     * Sets the path of the loaded file.
     *
     * @param openedFile to diplay
     */
    public final void setOpenFileField(final String openedFile) {
        openFileField.setText(openedFile);
    }

    /**
     * Displays the file descriptor.
     * @param loadedFileDescription to display
     */
    public final void setFileTypeValue(final String loadedFileDescription) {
        fileTypeValue.setText(loadedFileDescription);
    }

    /**
     * Displays a controller message.
     *
     * @param message to be displayed
     */
    public final void showMessage(final String message) {
        JOptionPane.showMessageDialog(this.frame, message);
    }

    /**
     * Displays a confirmation dialog with a message.
     *
     * @param message to display in the dialog
     * @return 1 if OK
     */
    public final int showConfirmDialog(final String message) {
        return JOptionPane.showConfirmDialog(this.frame, message);
    }

    /**
     * DIsplays the about pane.
     */
    public final void showAboutPane() {
        JOptionPane.showMessageDialog(this.frame,
                "<html><h3>GPX Splitter</h3>"
                + "<p>Author: Antonio Cucchiara <br>"
                + "Homepage: "
                + GPX_SPLITTER_URL
                + "<br>"
                + "The MIT License"
                + "</p></html>",
                "About " + GPX_SPLITTER,
                JOptionPane.PLAIN_MESSAGE,
                new javax.swing.ImageIcon(
                        getClass().getResource("/gpxsplitter/media/map.png")));
    }

    /**
     * Uses the default browser to load the help system.
     */
    public final void browseHelpSystem() {
        java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
        if (!desktop.isSupported(java.awt.Desktop.Action.BROWSE)) {
            showMessage("Browse action cannot be opened.");
        }
        try {
            URI uri = new URI(HELP_SYSTEM_LINK);
            desktop.browse(uri);
        } catch (Exception e) {
            showMessage("Problem reaching the website.");
        }
    }

    /**
     * Build a generic file chooser.
     *
     * @return the file chooser
     */
    private JFileChooser buildGpxFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new GpxFileFilter());
        return fileChooser;
    }

    /**
     * Allows the selection of a GPX file from the file system.
     *
     * @return the file path
     */
    public final String openGpxFile() {
        JFileChooser fileChooser = buildGpxFileChooser();
        fileChooser.showOpenDialog(this.frame);
        return fileChooser.getSelectedFile().getAbsolutePath();
    }

    /**
     * Allows the selection of a path to save a GPX file to the file system.
     *
     * @param selectedFile to save to
     * @return the file path
     */
    public final String saveGpxFile(final String selectedFile) {
        JFileChooser fileChooser = buildGpxFileChooser();
        fileChooser.setSelectedFile(new File(selectedFile));
        fileChooser.showSaveDialog(this.frame);
        return fileChooser.getSelectedFile().getAbsolutePath();
    }

    /**
     * Allows initialisation of the view.
     */
    public final void setVisible() {
        this.frame.setVisible(true);
    }
}
