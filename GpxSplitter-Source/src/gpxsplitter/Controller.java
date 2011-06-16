/**
 * Controller, handling user interaction and abstracting away the model.
 *
 * @author Antonino Cucchiara
 */

package gpxsplitter;

import gpxsplitter.tools.FileNotValidException;
import gpxsplitter.tools.GpxLoader;
import gpxsplitter.tools.GpxFileBuilder;
import gpxsplitter.tools.GpxRouteFileBuilder;
import gpxsplitter.tools.GpxTrackFileBuilder;
import gpxsplitter.model.GpxType;
import gpxsplitter.model.Gpx;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import org.jdom.JDOMException;

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
        new Controller(view);
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
                try
                {
                    browseGpxFile();
                }
                catch (FileNotValidException ex)
                {
                    Controller.this.view.showMessage(ex.getMessage());
                }
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

    void loadGpxFile(File gpxFile) throws IOException, JDOMException, FileNotValidException
    {
        loadedGpx = new GpxLoader(new FileInputStream(gpxFile), gpxFile.getAbsolutePath()).getLoadedGpx();
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
        if (view.getSelectedGpxType() == GpxType.Track)
        {
            gpxBuilder = new GpxTrackFileBuilder(loadedGpx);
        }
        else
        {
            gpxBuilder = new GpxRouteFileBuilder(loadedGpx);
        }
        gpxBuilder.build(file, desiredInstrNum);
        view.showMessage("Split GPX successfully saved.");
    }

    private void browseGpxFile() throws FileNotValidException
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
            JFileChooser saveFileChooser = new JFileChooser();
            saveFileChooser.setSelectedFile(new File(fileName));
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
