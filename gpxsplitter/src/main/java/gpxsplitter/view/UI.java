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

import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class UI extends javax.swing.JFrame {

    public static final String GPX_SPLITTER = "Gpx Splitter";
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    public UI() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.JPanel inputFilePanel = new javax.swing.JPanel();
        openFileField = new javax.swing.JTextField();
        browseButton = new javax.swing.JButton();
        javax.swing.JPanel inputFileFeaturesPanel = new javax.swing.JPanel();
        javax.swing.JLabel fileTypeLabel = new javax.swing.JLabel();
        fileTypeValue = new javax.swing.JLabel();
        javax.swing.JPanel ouputConfigurationPanel = new javax.swing.JPanel();
        javax.swing.JLabel instrNumLabel = new javax.swing.JLabel();
        instrNumField = new javax.swing.JTextField();
        javax.swing.JPanel outputFilePanel = new javax.swing.JPanel();
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

        inputFilePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Open Gpx file"));
        inputFilePanel.setLayout(new java.awt.GridLayout(1, 2, 10, 10));

        openFileField.setEnabled(false);
        inputFilePanel.add(openFileField);

        browseButton.setText("Browse...");
        inputFilePanel.add(browseButton);

        getContentPane().add(inputFilePanel);

        inputFileFeaturesPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Gpx features"));
        inputFileFeaturesPanel.setLayout(new java.awt.GridLayout(1, 2, 10, 10));

        fileTypeLabel.setText("Loaded gpx:");
        inputFileFeaturesPanel.add(fileTypeLabel);

        fileTypeValue.setText("N/A");
        inputFileFeaturesPanel.add(fileTypeValue);

        getContentPane().add(inputFileFeaturesPanel);

        ouputConfigurationPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Output configuration"));
        ouputConfigurationPanel.setLayout(new java.awt.GridLayout(1, 2, 10, 10));

        instrNumLabel.setText("Instructions / file:");
        ouputConfigurationPanel.add(instrNumLabel);

        instrNumField.setText("125");
        ouputConfigurationPanel.add(instrNumField);

        getContentPane().add(ouputConfigurationPanel);

        outputFilePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Save Gpx files"));
        outputFilePanel.setLayout(new java.awt.GridLayout(1, 2, 10, 10));
        outputFilePanel.add(emptyLabel);

        saveFileButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        saveFileButton.setText("Save");
        outputFilePanel.add(saveFileButton);

        getContentPane().add(outputFilePanel);

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

        setBounds(0, 0, 366, 289);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JButton browseButton;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JLabel fileTypeValue;
    private javax.swing.JMenuItem helpMenuItem;
    private javax.swing.JTextField instrNumField;
    private javax.swing.JTextField openFileField;
    private javax.swing.JButton saveFileButton;
    // End of variables declaration//GEN-END:variables

    public JButton getBrowseButton() {
        return browseButton;
    }

    public JButton getSaveFileButton() {
        return saveFileButton;
    }

    public JMenuItem getExitMenuItem() {
        return exitMenuItem;
    }

    public JMenuItem getHelpMenuItem() {
        return helpMenuItem;
    }

    public JMenuItem getAboutMenuItem() {
        return aboutMenuItem;
    }

    public String getInstructionsNumber() {
        return instrNumField.getText();
    }

    public void setFileTypeValue(String fileType) {
        fileTypeValue.setText(fileType);
    }

    public void setOpenFileField(String openedFile) {
        openFileField.setText(openedFile);
    }

    public void setSplittingEnabled(boolean enabled) {
        instrNumField.setEnabled(enabled);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public int showConfirmDialog(String message) {
        return JOptionPane.showConfirmDialog(this, message);
    }

    public void showAboutPane() {
        JOptionPane.showMessageDialog(
                this,
                "<html><h3>GPX Splitter vHEAD</h3>"
                + "<p>Author: Antonio Cucchiara <br>"
                + "Homepage: http://antocuc.github.com/GpxSplitter.html <br>"
                + "License:  Creative Commons  3.0"
                + "<ul><li>Attribution</li><li>Non Commercial</li><li>Share Alike</li></ul>"
                + "</p></html>",
                "About " + GPX_SPLITTER,
                JOptionPane.PLAIN_MESSAGE,
                new javax.swing.ImageIcon(getClass().getResource("/gpxsplitter/media/map.png")));

    }

    public void browseHelpSystem() {
        java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
        if (!desktop.isSupported(java.awt.Desktop.Action.BROWSE)) {
            showMessage("Desktop doesn't support the browse action the online help cannot be open.");
        }
        try {
            java.net.URI uri = new java.net.URI("http://antocuc.github.com/GpxSplitterHelp.html");
            desktop.browse(uri);
        } catch (Exception e) {
            showMessage("Problem reaching the website.");
        }
    }
}
