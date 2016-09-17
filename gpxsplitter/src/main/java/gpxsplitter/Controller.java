/**
 * Controller, handling user interaction and abstracting away the model.
 *
 * @author Antonino Cucchiara
 */
package gpxsplitter;

import gpxsplitter.tools.FileNotValidException;
import gpxsplitter.tools.builders.GpxFileBuilder;
import gpxsplitter.tools.builders.GpxRouteFileBuilder;
import gpxsplitter.tools.builders.GpxTrackFileBuilder;
import gpxsplitter.model.GpxType;
import gpxsplitter.tools.builders.SingleTrackGpxFileBuilder;
import gpxsplitter.tools.loaders.GpxFileLoader;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.xml.bind.JAXBException;

public class Controller {

    private UI view;
    private GpxType loadedGpx;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.out.println("Error setting native LAF: " + e);
        }

        final UI view = new UI();
        final Controller controller = new Controller(view);
        controller.initialise();
    }

    private Controller(UI view) {
        this.view = view;
        this.view.getBrowseButton().addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    loadGpxFile();
                } catch (FileNotValidException ex) {
                    Controller.this.view.showMessage(ex.getMessage());
                }
            }
        });
        this.view.getSaveFileButton().addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                saveGpxFile();
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
        this.view.getSeparateTracksRadioButton().addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                Controller.this.view.setSplittingEnabled(false);
            }
        });
        this.view.getJoinTracksRadioButton().addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                Controller.this.view.setSplittingEnabled(true);
            }
        });
    }

    private void loadGpxFile() throws FileNotValidException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new GpxFileFilter());
        fileChooser.showOpenDialog(view);
        File selectedFile = fileChooser.getSelectedFile();
        if (selectedFile != null) {
            try {
                loadGpxFile(selectedFile);
            } catch (IOException ex) {
                view.showMessage("A problem occured when loading the file." + UI.LINE_SEPARATOR + "Do you have the rights to read from that location?");
            } catch (JAXBException ex) {
                view.showMessage("The file you tried to load is not a valid GPX.");
            }
        }
    }

    void loadGpxFile(File gpxFile) throws JAXBException, FileNotFoundException, FileNotValidException, IOException {
        final GpxFileLoader gpxLoader = new GpxFileLoader();
        loadedGpx = gpxLoader.load(new FileInputStream(gpxFile));

        if (GpxFileLoader.isGpxMultitrack(loadedGpx)) {
            view.setTracksNumValue(String.valueOf(GpxFileLoader.getTracksNum(loadedGpx)));
            view.setMultiTrackEnabled(true);
        } else {
            view.setMultiTrackEnabled(false);
        }

        String fileType = "Gpx " + loadedGpx.getVersion();
        fileType += " (" + loadedGpx.getTrk().size() + " tracks, )" + loadedGpx.getRte().size() + " routes.";
        view.setOpenFileField(gpxFile.getAbsolutePath());
        view.setFileTypeValue(fileType);
    }

    private void saveGpxFile() {
        String numOfInstructions = view.getInstructionsNumber();
        if (!isAValidInteger(numOfInstructions)) {
            view.showMessage("Instructions number not valid");
            return;
        }
        int instNum = Integer.parseInt(numOfInstructions);
        String gpxType = view.getHighlightedGpxType();
        String fileName = view.getNewGpxFileName();
        if (fileName.isEmpty()) {
            view.showMessage("File name cannot be empty");
            return;
        }
        try {
            JFileChooser saveFileChooser = new JFileChooser();
            saveFileChooser.setSelectedFile(new File(fileName));
            saveFileChooser.setFileFilter(new GpxFileFilter());
            saveFileChooser.showSaveDialog(view);
            saveGpxFile(instNum, gpxType, saveFileChooser.getSelectedFile());

        } catch (IOException | JAXBException e) {
            view.showMessage("Problem whilst saving the file." + UI.LINE_SEPARATOR + "Do you have the rights to write to that location?");
        }
    }

    void saveGpxFile(int desiredInstrNum, String gpxType, File file) throws IOException, JAXBException {
        if (loadedGpx == null) {
            view.showMessage("A GPX to split has to be selected");
            return;
        }

        view.showMessage("Saving Gpx file(s) \n Instructions number: " + desiredInstrNum + "\n Gpx Type: " + gpxType);

        GpxFileBuilder gpxBuilder;
        if (view.getSeparateTracksRadioButton().isSelected()) {
            gpxBuilder = new SingleTrackGpxFileBuilder();
        } else if ("Track".equals(view.getSelectedGpxType())) {
            gpxBuilder = new GpxTrackFileBuilder();
        } else {
            gpxBuilder = new GpxRouteFileBuilder();
        }

        gpxBuilder.build(file, loadedGpx, desiredInstrNum);
        view.showMessage("Split GPX successfully saved.");
    }

    private boolean isAValidInteger(String intAsStr) {
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
