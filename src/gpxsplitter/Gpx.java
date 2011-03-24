package gpxsplitter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.filter.ElementFilter;
import org.jdom.input.SAXBuilder;

/**
 *
 * @author anc6
 */
public final class Gpx
{
    public static final String TRACKSEGMENT_TAG = "trkseg";
    public static final String TRACKPOINT = "trkpt";
    public static final String ELEMENT_TAG = "ele";
    public static final String LATITUDE_TAG = "lat";
    public static final String LONGITUDE_TAG = "lon";
    public static final String TRK_TAG = "trk";
    private static final String RTE_TAG = "rte";
    private static final String VERSION = "version";
    private final Document gpxDocument;
    private final File gpxFile;
    private final GpxType gpxType;
    private final Namespace namespace;

    Gpx(File gpxFile) throws IOException, JDOMException
    {
        this.gpxFile = gpxFile;
        SAXBuilder builder = new SAXBuilder();
        this.gpxDocument = builder.build(gpxFile);
        this.namespace = gpxDocument.getRootElement().getNamespace();
        this.gpxType = getType();
    }

    String getVersion()
    {

        return ((Element) gpxDocument.getRootElement()).getAttributeValue(VERSION);
    }

    GpxType getType()
    {
        if (gpxDocument.getRootElement().getChild(TRK_TAG, namespace) != null)
        {
            return GpxType.Track;
        }
        else if (((Element) gpxDocument.getRootElement()).getChild(RTE_TAG) != null)
        {
            return GpxType.Route;
        }
        else
        {
            return GpxType.NA;
        }
    }

    int getNumOfInstructions()
    {
        if (gpxType == GpxType.Track)
        {
            return gpxDocument.getRootElement().getChild(TRK_TAG, namespace).getChild(TRACKSEGMENT_TAG, namespace).getChildren().size();
        }
        else if (gpxType == GpxType.Route)
        {
            return ((Element) gpxDocument.getRootElement()).getChild(RTE_TAG).getChild("rteseg").getChildren("rtept").size();
        }
        else
        {
            return 0;
        }
    }

    String getFilePath()
    {
        return gpxFile.getAbsolutePath();
    }

    List<WayPoint> getIntructions()
    {
        List<WayPoint> instructions = new ArrayList<WayPoint>();
        if (gpxType == GpxType.Track)
        {
            List<Element> instructionsList = gpxDocument.getRootElement().getChild(TRK_TAG, namespace).getChild(TRACKSEGMENT_TAG, namespace).getChildren();
            for (Element instruction : instructionsList)
            {
                instructions.add(
                        new WayPoint(
                        Double.parseDouble(instruction.getAttributeValue(LATITUDE_TAG)),
                        Double.parseDouble(instruction.getAttributeValue(LONGITUDE_TAG)),
                        instruction.getChildText(ELEMENT_TAG, namespace)));
            }
            return instructions;
        }
        else
        {
            List<Element> instructionsList = ((Element) gpxDocument.getRootElement()).getChild(RTE_TAG).getChild("rteseg").getChildren("rtept");
            for (Element instruction : instructionsList)
            {
                instructions.add(
                        new WayPoint(
                        Double.parseDouble(instruction.getAttributeValue(LATITUDE_TAG)),
                        Double.parseDouble(instruction.getAttributeValue(LONGITUDE_TAG)),
                        instruction.getChildText(ELEMENT_TAG)));
            }
            return instructions;
        }
    }
}
