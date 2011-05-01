/**
 * The GpxSplitter user interface.
 *
 * @author Antonino Cucchiara
 */

package gpxsplitter;

import gpxsplitter.Model.GpxType;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class UI extends javax.swing.JFrame
{

    public static final String GPX_SPLITTER = "Gpx Splitter";

    public UI()
    {
        initComponents();
        populateOutputGpxFormats();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.JPanel topPanel = new javax.swing.JPanel();
        openFileField = new javax.swing.JTextField();
        browseButton = new javax.swing.JButton();
        javax.swing.JLabel fileTypeLabel = new javax.swing.JLabel();
        fileTypeValue = new javax.swing.JLabel();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(GPX_SPLITTER);
        setIconImage(Toolkit.getDefaultToolkit().getImage(UI.class.getResource("/gpxsplitter/media/map.png")));
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridLayout(3, 1, 10, 10));

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

        middlePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Output Gpx"));
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

        bottomPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("New gpx name"));
        bottomPanel.setLayout(new java.awt.GridLayout(2, 2, 10, 10));

        fileNameLabel.setText("File name:");
        bottomPanel.add(fileNameLabel);
        bottomPanel.add(fileNameField);
        bottomPanel.add(emptyLabel);

        saveFileButton.setFont(new java.awt.Font("Tahoma", 1, 11));
        saveFileButton.setText("Split");
        bottomPanel.add(saveFileButton);

        getContentPane().add(bottomPanel);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-416)/2, (screenSize.height-323)/2, 416, 323);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton browseButton;
    private javax.swing.JTextField fileNameField;
    private javax.swing.JLabel fileTypeValue;
    private javax.swing.JComboBox gpxTypeComboBox;
    private javax.swing.JTextField instrNumField;
    private javax.swing.JTextField openFileField;
    private javax.swing.JButton saveFileButton;
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

    public void setFileTypeValue(String fileType)
    {
        fileTypeValue.setText(fileType);
    }

    public void setOpenFileField(String openedFile)
    {
        openFileField.setText(openedFile);
    }

    public void showMessage(String message)
    {
        JOptionPane.showMessageDialog(this, message);
    }
}
