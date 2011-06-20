/**
 * Gpx loader builds a Gpx from a gpx markup file.
 *
 * @author Antonino Cucchiara
 */

package gpxsplitter.tools;

import gpxsplitter.model.GpxType;
import gpxsplitter.model.Gpx;
import gpxsplitter.model.Waypoint;
import java.io.InputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;

public class GpxLoader
{

    private final Document gpxDocument;
    private final Gpx newGpx;

    public GpxLoader(InputStream fileInputStream, String gpxFilePath) throws JDOMException, IOException, FileNotValidException
    {
        SAXBuilder builder = new SAXBuilder();
        this.gpxDocument = builder.build(fileInputStream);

        this.newGpx = new Gpx(getNamespace(), getType(), getVersion(), gpxFilePath, getInstructions());
    }

    private Namespace getNamespace()
    {
        return gpxDocument.getRootElement().getNamespace();
    }

    public Gpx getLoadedGpx()
    {
        return newGpx;
    }

    private GpxType getType() throws FileNotValidException
    {
        if (gpxDocument.getRootElement().getChild(Gpx.TRK_TAG, getNamespace()) != null)
        {
            return GpxType.Track;
        }
        else if (gpxDocument.getRootElement().getChild(Gpx.RTE_TAG, getNamespace()) != null)
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

    private List<Waypoint> getInstructions() throws FileNotValidException
    {
        List<Waypoint> instructions = new ArrayList<Waypoint>();
        if (getType() == GpxType.Track)
        {
            List<Element> instructionsList = gpxDocument.getRootElement().getChild(Gpx.TRK_TAG, getNamespace()).getChild(Gpx.TRACKSEGMENT_TAG, getNamespace()).getChildren();
            for (Element instruction : instructionsList)
            {
                instructions.add(
                        new Waypoint(
                        Double.parseDouble(instruction.getAttributeValue(Gpx.LATITUDE_TAG)),
                        Double.parseDouble(instruction.getAttributeValue(Gpx.LONGITUDE_TAG)),
                        instruction.getChildText(Gpx.ELEMENT_TAG, getNamespace())));
            }
            return instructions;
        }
        else
        {
            List<Element> instructionsList = gpxDocument.getRootElement().getChild(Gpx.RTE_TAG, getNamespace()).getChildren(Gpx.RTEPT_TAG, getNamespace());
            for (Element instruction : instructionsList)
            {
                instructions.add(
                        new Waypoint(
                        Double.parseDouble(instruction.getAttributeValue(Gpx.LATITUDE_TAG)),
                        Double.parseDouble(instruction.getAttributeValue(Gpx.LONGITUDE_TAG)),
                        instruction.getChildText(Gpx.ELEMENT_TAG, getNamespace())));
            }
            return instructions;
        }
    }

    public boolean isGpxMultitrack() throws FileNotValidException
    {
        if(getType() == GpxType.Track && (getTracksNum() > 1))
        {
                return true;
        }
        return false;
    }

    public int getTracksNum()
    {
        return gpxDocument.getRootElement().getChildren(Gpx.TRK_TAG, getNamespace()).size();
    }
}
