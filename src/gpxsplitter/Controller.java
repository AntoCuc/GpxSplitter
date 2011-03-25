package gpxsplitter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import org.jdom.JDOMException;

/**
 *
 * @author anc6
 */
public class Controller
{

    private UI view;
    private Gpx loadedGpx;

    public static void main(String[] args)
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e)
        {
            System.out.println("Error setting native LAF: " + e);
        }

        final UI view = new UI();
        final Controller controller = new Controller(view);
        view.setController(controller);
        view.setVisible(true);
    }

    private Controller(UI view)
    {
        this.view = view;
        this.view.getBrowseButton().addMouseListener(new MouseAdapter()
        {

            @Override
            public void mousePressed(MouseEvent e)
            {
                browseGpxFile();
            }
        });
        this.view.getSaveFileButton().addMouseListener(new MouseAdapter()
        {

            @Override
            public void mousePressed(MouseEvent e)
            {
                saveGpxFile();
            }
        });
    }

    void loadGpxFile(File gpxFile) throws IOException, JDOMException
    {
        loadedGpx = new GpxLoader(gpxFile).getLoadedGpx();
        view.setFileTypeValue("Gpx " + loadedGpx.getType() + " " + loadedGpx.getVersion() + " (" + loadedGpx.getNumOfInstructions() + " instructions)");
        view.setOpenFileField(loadedGpx.getFilePath());
    }

    void saveGpxFile(int desiredInstrNum, String gpxType, File file) throws IOException
    {
        if (loadedGpx == null)
        {
            view.showMessage("A GPX to split has to be selected");
            return;
        }

        view.showMessage("Saving Gpx file(s) \n Instructions number: "
                + desiredInstrNum + "\n Gpx Type: " + gpxType);

        GpxFileBuilder gpxBuilder;
        if (view.getSelectedGpxType() == GpxFormat.Track)
        {
            gpxBuilder = new GpxTrackFileBuilder(loadedGpx);
        }
        else
        {
            gpxBuilder = new GpxRouteFileBuilder(loadedGpx);
        }
        int totalNumOfInstr = loadedGpx.getNumOfInstructions();
        int filesNum = howManyFiles(totalNumOfInstr, desiredInstrNum);
        gpxBuilder.build(file, totalNumOfInstr, filesNum);
        view.showMessage("Split GPX successfully saved.");
    }

    /**
     * This method calculates how many files have to be created when splitting
     * the GPX to the wanted number of instructions.
     * @param currNumOfInstr
     * @param desiredNumOfInstr
     * @return
     */
    static int howManyFiles(int currNumOfInstr, int desiredNumOfInstr)
    {
        try
        {
            int numOfFiles = (currNumOfInstr / desiredNumOfInstr);
            /***
             * If there are any points left out one more file is going to be created.
             */
            if ((currNumOfInstr % desiredNumOfInstr) > 0)
            {
                numOfFiles++;
            }
            return numOfFiles;
        }
        catch (ArithmeticException e)
        { //No instructions or invalid instructions output number
            return 0;
        }
    }

    private void browseGpxFile()
    {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new GpxFileFilter());
        fileChooser.showOpenDialog(view);
        File selectedFile = fileChooser.getSelectedFile();
        if (selectedFile != null)
        {
            try
            {
                loadGpxFile(selectedFile);
            }
            catch (IOException ex)
            {
                Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch (JDOMException ex)
            {
                Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void saveGpxFile()
    {
        String numOfInstructions = view.getInstructionsNumber();
        if (!isAValidInteger(numOfInstructions))
        {
            view.showMessage("Instructions number not valid");
            return;
        }
        int instNum = Integer.parseInt(numOfInstructions);
        String gpxType = view.getHighlightedGpxType();
        String fileName = view.getNewGpxFileName();
        if (fileName.isEmpty())
        {
            view.showMessage("File name cannot be empty");
            return;
        }
        try
        {
            JFileChooser saveFileChooser = new JFileChooser(fileName);
            saveFileChooser.setFileFilter(new GpxFileFilter());
            saveFileChooser.showSaveDialog(view);
            saveGpxFile(instNum, gpxType, saveFileChooser.getSelectedFile());

        }
        catch (IOException e)
        {
            view.showMessage("Problem whilst saving the file.");
        }
    }

    private boolean isAValidInteger(String intAsStr)
    {
        try
        {
            Integer.parseInt(intAsStr);
            return true;
        }
        catch (NumberFormatException e)
        {
            return false;
        }
    }
}
