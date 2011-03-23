package gpxsplitter;

import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import org.apache.xmlbeans.XmlException;

/**
 *
 * @author anc6
 */
public class Controller {

    private UI view;
    private Gpx loadedGpx;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Error setting native LAF: " + e);
        }
        
        final UI view = new UI();
        final Controller controller = new Controller(view);
        view.setController(controller);
        view.setVisible(true);
    }

    private Controller(UI view) {
        this.view = view;
    }

    void loadGpxFile(File gpxFile) throws XmlException, IOException {
        loadedGpx = new Gpx(gpxFile);
        view.update(loadedGpx);
    }

    void saveGpxFile(int desiredInstrNum, String gpxType, String fileName) throws IOException, XmlException {
        if(loadedGpx == null)
        {
            JOptionPane.showMessageDialog(null, "A GPX to split has to be selected", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(null, "Saving Gpx file(s) \n Instructions number: "
                + desiredInstrNum + "\n Gpx Type: " + gpxType);

        GpxFileBuilder gpxBuilder;
        if (view.getSelectedGpxType() == GpxFormat.Track) {
            gpxBuilder = new GpxTrackFileBuilder(loadedGpx);
        } else {
            gpxBuilder = new GpxRouteFileBuilder(loadedGpx);
        }
        int totalNumOfInstr = loadedGpx.getNumOfInstructions();
        int filesNum = howManyFiles(totalNumOfInstr, desiredInstrNum);
        gpxBuilder.build(fileName, totalNumOfInstr, filesNum);
        view.gpxSavedSuccessfully();
    }

    /**
     * This method calculates how many files have to be created when splitting
     * the GPX to the wanted number of instructions.
     * @param currNumOfInstr
     * @param desiredNumOfInstr
     * @return
     */
    static int howManyFiles(int currNumOfInstr, int desiredNumOfInstr) {
        try {
            int numOfFiles = (currNumOfInstr / desiredNumOfInstr);
            /***
             * If there are any points left out one more file is going to be created.
             */
            if ((currNumOfInstr % desiredNumOfInstr) > 0) {
                numOfFiles++;
            }
            return numOfFiles;
        } catch (ArithmeticException e) { //No instructions or invalid instructions output number
            return 0;
        }
    }
}
