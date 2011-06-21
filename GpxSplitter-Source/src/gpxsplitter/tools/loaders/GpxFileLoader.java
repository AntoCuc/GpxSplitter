/**
 * Gpx loader builds a Gpx from a gpx markup file.
 *
 * @author Antonino Cucchiara
 */
package gpxsplitter.tools.loaders;

import gpxsplitter.tools.*;
import gpxsplitter.model.GpxType;
import gpxsplitter.model.Gpx;
import gpxsplitter.model.Itinerary;
import java.io.InputStream;
import java.io.IOException;
import java.util.List;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;

public abstract class GpxFileLoader implements GpxLoader
{

    final Document gpxDocument;
    private final String gpxFilePath;
    protected Gpx newGpx;

    abstract List<Itinerary> getItineraries() throws FileNotValidException;

    public GpxFileLoader(Document gpxDocument, String gpxFilePath) throws JDOMException, IOException, FileNotValidException
    {
        this.gpxDocument = gpxDocument;
        this.gpxFilePath = gpxFilePath;
    }

    public GpxFileLoader(InputStream fileInputStream, String gpxFilePath) throws JDOMException, IOException, FileNotValidException
    {
        this(new SAXBuilder().build(fileInputStream), gpxFilePath);
    }

    Namespace getNamespace()
    {
        return gpxDocument.getRootElement().getNamespace();
    }

    public static GpxType getType(Document gpxDocument) throws FileNotValidException, JDOMException, IOException
    {
        Namespace namespace = gpxDocument.getRootElement().getNamespace();
        if (gpxDocument.getRootElement().getChild(Gpx.TRK_TAG, namespace) != null)
        {
            return GpxType.Track;
        }
        else if (gpxDocument.getRootElement().getChild(Gpx.RTE_TAG, namespace) != null)
        {
            return GpxType.Route;
        }
        else
        {
            throw new FileNotValidException("The file you are trying to load is not route nor track.");
        }
    }

    private String getVersion()
    {
        return gpxDocument.getRootElement().getAttributeValue(Gpx.VERSION);
    }

    public static boolean isGpxMultitrack(Document gpxDocument) throws FileNotValidException, JDOMException, IOException
    {
        if (getTracksNum(gpxDocument) > 1)
        {
            return true;
        }
        return false;
    }

    public static int getTracksNum(Document gpxDocument)
    {
        return gpxDocument.getRootElement().getChildren(Gpx.TRK_TAG, gpxDocument.getRootElement().getNamespace()).size();
    }

    @Override
    public Gpx load() throws FileNotValidException, IOException, JDOMException
    {
        return new Gpx(getNamespace(), getType(gpxDocument), getVersion(), gpxFilePath, getItineraries());
    }
}
