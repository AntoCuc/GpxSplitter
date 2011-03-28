/**
 * Gpx loader builds a Gpx from a gpx markup file.
 *
 * @author Antonino Cucchiara
 */

package gpxsplitter.Tools;

import gpxsplitter.Model.GpxType;
import gpxsplitter.Model.Gpx;
import gpxsplitter.Model.Waypoint;
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

    public GpxLoader(InputStream fileInputStream, String gpxFilePath) throws JDOMException, IOException
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

    private GpxType getType()
    {
        if (gpxDocument.getRootElement().getChild(Gpx.TRK_TAG, getNamespace()) != null)
        {
            return GpxType.Track;
        }
        else if (gpxDocument.getRootElement().getChild(Gpx.RTE_TAG) != null)
        {
            return GpxType.Route;
        }
        else
        {
            return GpxType.NA;
        }
    }

    private String getVersion()
    {
        return gpxDocument.getRootElement().getAttributeValue(Gpx.VERSION);
    }

    private List<Waypoint> getInstructions()
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
            List<Element> instructionsList = gpxDocument.getRootElement().getChild(Gpx.RTE_TAG).getChild("rteseg").getChildren("rtept");
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
}
