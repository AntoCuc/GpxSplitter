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
package gpxsplitter.controller;

import gpxsplitter.view.View;
import gpxsplitter.tools.FileNotValidException;
import gpxsplitter.model.Model;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.xml.bind.JAXBException;

public class Controller {

    private final Model model;
    private final View view;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.out.println("Error setting native LAF: " + e);
        }

        final Model model = new Model();
        final View view = new View(model);
        final Controller controller = new Controller(model, view);
        controller.initialise();
    }

    private Controller(Model model, View view) {
        this.model = model;
        this.view = view;

        this.view.getBrowseButton().addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                Controller.this.loadGpxFile();
            }
        });
        this.view.getSaveFileButton().addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                Controller.this.saveGpxFile();
            }
        });
        this.view.getExitMenuItem().addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                System.exit(0);
            }
        });
        this.view.getAboutMenuItem().addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent e) {
                Controller.this.view.showAboutPane();
            }
        });
        this.view.getHelpMenuItem().addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                Controller.this.view.browseHelpSystem();
            }
        });
    }

    private void loadGpxFile() {
        try {
            File selectedFile = view.openGpxFile();
            if (selectedFile != null) {
                try {
                    model.loadGpxFile(selectedFile);
                    view.setOpenFileField(selectedFile.getAbsolutePath());
                    String loadedFileDescription = "Gpx " + model.getSourceGpx().getDescriptor() + " version " + model.getSourceGpx().getVersion();
                    view.setFileTypeValue(loadedFileDescription);
                } catch (IOException ex) {
                    view.showMessage("A problem occured when loading the file." + View.LINE_SEPARATOR + "Do you have the rights to read from that location?");
                } catch (JAXBException ex) {
                    view.showMessage("The file you tried to load is not a valid GPX.");
                }
            }
        } catch (FileNotValidException ex) {
            Controller.this.view.showMessage(ex.getMessage());
        }
    }

    private void saveGpxFile() {
        String instructionsNumberFieldText = view.getInstructionsNumberFieldText();
        if (!isValidInteger(instructionsNumberFieldText)) {
            view.showMessage("Instructions number not valid");
            return;
        }
        int instructionsNumber = Integer.parseInt(instructionsNumberFieldText);
        try {
            File saveFile = view.saveGpxFile(new File("split-" + model.getSourceFile().getName()));
            if (model.getSourceGpx() == null) {
                view.showMessage("A GPX to split has to be selected");
                return;
            }
            view.showMessage("Saving Gpx file(s) \n Instructions number: " + instructionsNumber + "\n Gpx Type: " + model.getSourceGpx().getDescriptor());
            model.saveGpx(saveFile, instructionsNumber);
            view.showMessage("Split GPX successfully saved.");
        } catch (JAXBException | FileNotValidException e) {
            view.showMessage("Problem whilst saving the file." + View.LINE_SEPARATOR + "Do you have the rights to write to that location?");
        }
    }

    private boolean isValidInteger(String intAsStr) {
        try {
            Integer.parseInt(intAsStr);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void initialise() {
        this.view.setVisible(true);
    }
}
