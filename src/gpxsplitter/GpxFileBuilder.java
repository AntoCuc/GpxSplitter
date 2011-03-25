package gpxsplitter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

/**
 *
 * @author anc6
 */
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
     * This file is to be overriden in Unit tests.
     * TODO: is the dash necessary?
     * @param file
     * @param fileBuiltNum
     * @throws IOException
     */
    void saveFile(File file, Document newGpxDocument) throws IOException
    {
        new XMLOutputter().output(newGpxDocument, new FileWriter(file));
    }
}
