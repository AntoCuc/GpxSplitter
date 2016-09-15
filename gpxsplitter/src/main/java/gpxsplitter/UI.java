/**
 * The GpxSplitter user interface.
 *
 * @author Antonino Cucchiara
 */
package gpxsplitter;

import gpxsplitter.model.GpxType;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

public class UI extends javax.swing.JFrame
{

    public static final String GPX_SPLITTER = "Gpx Splitter";
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    public UI()
    {
        initComponents();
        populateOutputGpxFormats();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.ButtonGroup multiTrackOperationButtonGroup = new javax.swing.ButtonGroup();
        javax.swing.JPanel topPanel = new javax.swing.JPanel();
        openFileField = new javax.swing.JTextField();
        browseButton = new javax.swing.JButton();
        javax.swing.JLabel fileTypeLabel = new javax.swing.JLabel();
        fileTypeValue = new javax.swing.JLabel();
        javax.swing.JPanel multiTrackGpxPanel = new javax.swing.JPanel();
        javax.swing.JLabel tracksNumLabel = new javax.swing.JLabel();
        tracksNumValue = new javax.swing.JLabel();
        javax.swing.JLabel operationLabel = new javax.swing.JLabel();
        javax.swing.JPanel operationsPanel = new javax.swing.JPanel();
        joinTracksRadioButton = new javax.swing.JRadioButton();
        separateTracksRadioButton = new javax.swing.JRadioButton();
        javax.swing.JPanel middlePanel = new javax.swing.JPanel();
        javax.swing.JLabel instrNumLabel = new javax.swing.JLabel();
        instrNumField = new javax.swing.JTextField();
        javax.swing.JLabel gpxTypeLabel = new javax.swing.JLabel();
        gpxTypeComboBox = new javax.swing.JComboBox();
        javax.swing.JPanel bottomPanel = new javax.swing.JPanel();
        javax.swing.JLabel fileNameLabel = new javax.swing.JLabel();
        fileNameField = new javax.swing.JTextField();
        javax.swing.JLabel emptyLabel = new javax.swing.JLabel();
        saveFileButton = new javax.swing.JButton();
        javax.swing.JMenuBar menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        exitMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        helpMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(GPX_SPLITTER);
        setIconImage(Toolkit.getDefaultToolkit().getImage(UI.class.getResource("/gpxsplitter/media/map.png")));
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridLayout(4, 1, 10, 10));

        topPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Open Gpx file"));
        topPanel.setLayout(new java.awt.GridLayout(2, 2, 10, 10));

        openFileField.setEnabled(false);
        topPanel.add(openFileField);

        browseButton.setText("Browse...");
        topPanel.add(browseButton);

        fileTypeLabel.setText("Loaded gpx:");
        topPanel.add(fileTypeLabel);

        fileTypeValue.setText("N/A");
        topPanel.add(fileTypeValue);

        getContentPane().add(topPanel);

        multiTrackGpxPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Multi track settings"));
        multiTrackGpxPanel.setLayout(new java.awt.GridLayout(2, 2, 10, 10));

        tracksNumLabel.setText("Tracks on the gpx:");
        multiTrackGpxPanel.add(tracksNumLabel);

        tracksNumValue.setText("N/A");
        multiTrackGpxPanel.add(tracksNumValue);

        operationLabel.setText("Operation:");
        multiTrackGpxPanel.add(operationLabel);

        operationsPanel.setLayout(new java.awt.GridLayout(1, 2));

        multiTrackOperationButtonGroup.add(joinTracksRadioButton);
        joinTracksRadioButton.setSelected(true);
        joinTracksRadioButton.setText("Join tracks");
        operationsPanel.add(joinTracksRadioButton);

        multiTrackOperationButtonGroup.add(separateTracksRadioButton);
        separateTracksRadioButton.setText("Separate");
        operationsPanel.add(separateTracksRadioButton);

        multiTrackGpxPanel.add(operationsPanel);

        getContentPane().add(multiTrackGpxPanel);

        middlePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Single track settings"));
        middlePanel.setLayout(new java.awt.GridLayout(2, 2, 10, 10));

        instrNumLabel.setText("Instructions / file:");
        middlePanel.add(instrNumLabel);

        instrNumField.setText("125");
        middlePanel.add(instrNumField);

        gpxTypeLabel.setText("Gpx type:");
        middlePanel.add(gpxTypeLabel);

        gpxTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Track", "Route" }));
        middlePanel.add(gpxTypeComboBox);

        getContentPane().add(middlePanel);

        bottomPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Save Gpx files"));
        bottomPanel.setLayout(new java.awt.GridLayout(2, 2, 10, 10));

        fileNameLabel.setText("File's name:");
        bottomPanel.add(fileNameLabel);
        bottomPanel.add(fileNameField);
        bottomPanel.add(emptyLabel);

        saveFileButton.setFont(new java.awt.Font("Tahoma", 1, 11));
        saveFileButton.setText("Save");
        bottomPanel.add(saveFileButton);

        getContentPane().add(bottomPanel);

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

        setJMenuBar(menuBar);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-430)/2, (screenSize.height-460)/2, 430, 460);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JButton browseButton;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JTextField fileNameField;
    private javax.swing.JLabel fileTypeValue;
    private javax.swing.JComboBox gpxTypeComboBox;
    private javax.swing.JMenuItem helpMenuItem;
    private javax.swing.JTextField instrNumField;
    private javax.swing.JRadioButton joinTracksRadioButton;
    private javax.swing.JTextField openFileField;
    private javax.swing.JButton saveFileButton;
    private javax.swing.JRadioButton separateTracksRadioButton;
    private javax.swing.JLabel tracksNumValue;
    // End of variables declaration//GEN-END:variables

    GpxType getSelectedGpxType()
    {
        return (GpxType) gpxTypeComboBox.getSelectedItem();
    }

    private void populateOutputGpxFormats()
    {
        gpxTypeComboBox.removeAllItems(); // Clear and repopulate from the enum
        for (GpxType format : GpxType.values())
        {
            gpxTypeComboBox.addItem(format);
        }
    }

    JButton getBrowseButton()
    {
        return browseButton;
    }

    JButton getSaveFileButton()
    {
        return saveFileButton;
    }

    JMenuItem getExitMenuItem()
    {
        return exitMenuItem;
    }

    JMenuItem getHelpMenuItem()
    {
        return helpMenuItem;
    }

    JMenuItem getAboutMenuItem()
    {
        return aboutMenuItem;
    }

    String getInstructionsNumber()
    {
        return instrNumField.getText();
    }

    String getHighlightedGpxType()
    {
        return gpxTypeComboBox.getSelectedItem().toString();
    }

    String getNewGpxFileName()
    {
        return fileNameField.getText();
    }

    JRadioButton getSeparateTracksRadioButton()
    {
        return separateTracksRadioButton;
    }

    JRadioButton getJoinTracksRadioButton()
    {
        return joinTracksRadioButton;
    }

    public void setFileTypeValue(String fileType)
    {
        fileTypeValue.setText(fileType);
    }

    public void setOpenFileField(String openedFile)
    {
        openFileField.setText(openedFile);
    }

    void setSplittingEnabled(boolean enabled)
    {
        instrNumField.setEnabled(enabled);
        gpxTypeComboBox.setEnabled(enabled);
    }

    void setMultiTrackEnabled(boolean enabled)
    {
        joinTracksRadioButton.setEnabled(enabled);
        separateTracksRadioButton.setEnabled(enabled);
        tracksNumValue.setEnabled(enabled);
    }

    public void setTracksNumValue(String tracksNum)
    {
        this.tracksNumValue.setText(tracksNum);
    }

    public void showMessage(String message)
    {
        JOptionPane.showMessageDialog(this, message);
    }

    int showConfirmDialog(String message)
    {
        return JOptionPane.showConfirmDialog(this, message);
    }

    void showAboutPane()
    {
        JOptionPane.showMessageDialog(
                this,
                "<html><h3>GPX Splitter vHEAD</h3>" +
                "<p>Author: Antonio Cucchiara <br>" +
                "Homepage: http://antocuc.github.com/GpxSplitter.html <br>" +
                "License:  Creative Commons  3.0" +
                "<ul><li>Attribution</li><li>Non Commercial</li><li>Share Alike</li></ul>" +
                "</p></html>",
                "About " + GPX_SPLITTER,
                JOptionPane.PLAIN_MESSAGE,
                new javax.swing.ImageIcon(getClass().getResource("/gpxsplitter/media/map.png")));

    }

    void browseHelpSystem()
    {
        java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
        if (!desktop.isSupported(java.awt.Desktop.Action.BROWSE))
        {
            showMessage("Desktop doesn't support the browse action the online help cannot be open.");
        }
        try
        {
            java.net.URI uri = new java.net.URI("http://antocuc.github.com/GpxSplitterHelp.html");
            desktop.browse(uri);
        }
        catch (Exception e)
        {
            showMessage("Problem reaching the website.");
        }
    }
}
