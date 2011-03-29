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

    protected Document createNewGpx()
    {
        Document newGpxDocument = new Document(new Element("gpx"));
        Element newGpx = newGpxDocument.getRootElement();
        newGpx.setAttribute("version", gpx.getVersion());
        newGpx.setAttribute("creator", UI.GPX_SPLITTER);
        return newGpxDocument;
    }

    /**
     * This method is to be overriden in Unit tests.
     * TODO: is the dash necessary?
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
}
