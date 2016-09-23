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
import gpxsplitter.model.exception.FileNotValidException;
import gpxsplitter.model.Model;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.xml.bind.JAXBException;

/**
 * Coordinates data flows between Model and View.
 *
 * @author Antonino Cucchiara
 */
public final class Controller {

    /**
     * Logger facility.
     */
    private static final Logger LOGGER =
            Logger.getLogger(Controller.class.getName());

    /**
     * The GpxSplitter model.
     */
    private final Model model;
    /**
     * The GpxSplitter view.
     */
    private final View view;

    /**
     * GpxSplitter bootstrapping method.
     * @param args GpcSplitter arguments
     */
    public static void main(final String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Error setting native LAF: " + e);
        }

        final Model model = new Model();
        final View view = new View(model);
        final Controller controller = new Controller(model, view);
        controller.initialise();
    }

    /**
     * Initialise the Controller with references to the model and view.
     * @param newModel GpxSplitter Business Logic
     * @param newView GpxSplitter Frontend
     */
    private Controller(final Model newModel, final View newView) {
        this.model = newModel;
        this.view = newView;

        this.view.getBrowseButton().addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(final MouseEvent e) {
                Controller.this.loadGpxFile();
            }
        });
        this.view.getSaveFileButton().addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(final MouseEvent e) {
                Controller.this.saveGpxFile();
            }
        });
        this.view.getExitMenuItem().addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(final MouseEvent e) {
                System.exit(0);
            }
        });
        this.view.getAboutMenuItem().addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(final MouseEvent e) {
                Controller.this.view.showAboutPane();
            }
        });
        this.view.getHelpMenuItem().addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(final MouseEvent e) {
                Controller.this.view.browseHelpSystem();
            }
        });
    }

    /**
     * Coordinate the loading of a Gpx file.
     */
    private void loadGpxFile() {
        try {
            final String loadFilePath = view.openGpxFile();
            if (loadFilePath != null) {
                try {
                    model.loadGpxFile(loadFilePath);
                    view.setOpenFileField(loadFilePath);
                    String loadedFileDescription = "Gpx "
                            + model.getSourceGpx().getDescriptor()
                            + " version " + model.getSourceGpx().getVersion();
                    view.setFileTypeValue(loadedFileDescription);
                } catch (IOException ex) {
                    view.showMessage("A problem occured loading the file."
                            + View.LINE_SEPARATOR
                            + "Do you have the rights to read from that "
                            + "location?");
                } catch (JAXBException ex) {
                    view.showMessage("Not a valid GPX.");
                    LOGGER.severe(ex.getMessage());
                }
            }
        } catch (FileNotValidException ex) {
            Controller.this.view.showMessage(ex.getMessage());
        }
    }

    /**
     * Coordinate the saving of a Gpx file.
     */
    private void saveGpxFile() {
        String instructionsNumberFieldText =
                view.getInstructionsNumberFieldText();
        if (!isValidInteger(instructionsNumberFieldText)) {
            view.showMessage("Instructions number not valid");
            return;
        }
        int instructionsNumber = Integer.parseInt(instructionsNumberFieldText);
        try {
            final String selectedFileName = "split-"
                    + model.getSourceFilePath();
            final String saveFilePath = view.saveGpxFile(selectedFileName);
            if (model.getSourceGpx() == null) {
                view.showMessage("A GPX to split has to be selected");
                return;
            }
            view.showMessage("Saving Gpx file(s) \n Instructions number: "
                    + instructionsNumber
                    + "\n Gpx Type: "
                    + model.getSourceGpx().getDescriptor());
            model.saveGpx(saveFilePath, instructionsNumber);
            view.showMessage("Split GPX successfully saved.");
        } catch (JAXBException | FileNotValidException e) {
            view.showMessage("Problem whilst saving the file."
                    + View.LINE_SEPARATOR
                    + "Do you have the rights to write to that location?");
        }
    }

    /**
     * Determines whether the string can be parsed as an integer.
     * @param input the unsecured input
     * @return true if the input is an integer
     */
    private boolean isValidInteger(final String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Initialise the GpxSplitter UI.
     */
    public void initialise() {
        this.view.setVisible(true);
    }
}
