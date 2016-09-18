/**
 * Controller, handling user interaction and abstracting away the model.
 *
 * @author Antonino Cucchiara
 */
package gpxsplitter;

import gpxsplitter.view.UI;
import gpxsplitter.model.GpxDescriptor;
import gpxsplitter.tools.FileNotValidException;
import gpxsplitter.tools.builders.GpxFileBuilder;
import gpxsplitter.tools.builders.GpxRouteFileBuilder;
import gpxsplitter.tools.builders.GpxTrackFileBuilder;
import gpxsplitter.model.Gpx;
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
    private Gpx loadedGpx;
    private File loadedGpxFile;

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
    }

    private void loadGpxFile() throws FileNotValidException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new GpxFileFilter());
        fileChooser.showOpenDialog(view);
        File selectedFile = fileChooser.getSelectedFile();
        if (selectedFile != null) {
            try {
                loadedGpxFile = selectedFile;
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

        String fileType = "Gpx " + loadedGpx.getDescriptor() + " version " + loadedGpx.getVersion();
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
        try {
            GpxDescriptor gpxType = loadedGpx.getDescriptor();
            JFileChooser saveFileChooser = new JFileChooser();
            saveFileChooser.setSelectedFile(new File("split-" + loadedGpxFile.getName()));
            saveFileChooser.setFileFilter(new GpxFileFilter());
            saveFileChooser.showSaveDialog(view);
            saveGpxFile(instNum, gpxType, saveFileChooser.getSelectedFile());

        } catch (IOException | JAXBException | FileNotValidException e) {
            view.showMessage("Problem whilst saving the file." + UI.LINE_SEPARATOR + "Do you have the rights to write to that location?");
        }
    }

    void saveGpxFile(int desiredInstrNum, GpxDescriptor descriptor, File file) throws IOException, JAXBException, FileNotValidException {
        if (loadedGpx == null) {
            view.showMessage("A GPX to split has to be selected");
            return;
        }

        view.showMessage("Saving Gpx file(s) \n Instructions number: " + desiredInstrNum + "\n Gpx Type: " + descriptor);

        GpxFileBuilder gpxBuilder;
        if (descriptor == GpxDescriptor.Track) {
            gpxBuilder = new GpxTrackFileBuilder();
        } else if (descriptor == GpxDescriptor.Route) {
            gpxBuilder = new GpxRouteFileBuilder();
        } else {
            throw new FileNotValidException();
        }

        gpxBuilder.build(file, loadedGpx.getUnderlying(), desiredInstrNum);
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
