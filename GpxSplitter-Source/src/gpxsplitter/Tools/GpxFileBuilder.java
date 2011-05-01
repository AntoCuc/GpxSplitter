/**
 * Builds a gpx markup file.
 *
 * @author Antonino Cucchiara
 */

package gpxsplitter.Tools;

import gpxsplitter.Model.Gpx;
import gpxsplitter.UI;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

public abstract class GpxFileBuilder implements GpxBuilder
{

    public static final String GPX_FORMAT = ".gpx";
    protected Gpx gpx;

    public GpxFileBuilder(Gpx gpx)
    {
        this.gpx = gpx;
    }

    /**
     * The method build split gpx uses the gpx document loaded and the
     * preferred number of instructions to build a list of Gpx files ready
     * to be written to file.
     * @param preferredInstrNum
     * @return a list of gpx files
     */
    public abstract List<Document> buildSplitGpx(int preferredInstrNum);

    /**
     * The method create gpx template creates the start elements and
     * heading of a gpx document.
     * @return a gpx document.
     */
    protected Document createGpxTemplate()
    {
        Document newGpxDocument = new Document(new Element("gpx"));
        Element newGpx = newGpxDocument.getRootElement();
        newGpx.setAttribute("version", gpx.getVersion());
        newGpx.setAttribute("creator", UI.GPX_SPLITTER);
        return newGpxDocument;
    }

    /**
     * This method is to be overriden in Unit tests.
     * @param file
     * @param gpx document to write to the file
     * @throws IOException
     */
    void saveFile(File file, Document newGpxDocument) throws IOException
    {
        new XMLOutputter().output(newGpxDocument, new FileWriter(file));
    }

    /**
     * This method cleans the filename of the extension if it is there.
     * @param file
     * @return the filename stripped of the extension
     */
    String stripExtension(String fileName, String extension)
    {
        if (fileName.endsWith(extension))
        {
            return fileName.substring(0, (fileName.length() - extension.length()));
        }
        else
        {
            return fileName;
        }
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
}
