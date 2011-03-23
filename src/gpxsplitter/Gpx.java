package gpxsplitter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.filter.ElementFilter;
import org.jdom.input.SAXBuilder;

/**
 *
 * @author anc6
 */
public final class Gpx
{
    public static final String ELEMENT_TAG = "ele";
    public static final String LATITUDE_TAG = "lat";
    public static final String LONGITUDE_TAG = "lon";
    private static final String RTE_TAG = "rte";
    private static final String TRK_TAG = "trk";
    private static final String VERSION = "version";

    private final Document gpxDocument;
    private final File gpxFile;
    private final GpxType gpxType;

    Gpx(File gpxFile) throws IOException, JDOMException
    {
        this.gpxFile = gpxFile;
        SAXBuilder builder = new SAXBuilder();
        this.gpxDocument = builder.build(gpxFile);
        this.gpxType = getType();
    }

    String getVersion()
    {

        return ((Element) gpxDocument.getRootElement()).getAttributeValue(VERSION);
    }

    GpxType getType()
    {
        List segs = gpxDocument.getRootElement().getChildren();
        
        if (((Element)segs.get(0)).getName().equals(TRK_TAG))
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
            /**
             * TODO: refactor this
             */
            return ((Element)((Element)((Element) gpxDocument.getRootElement()).getChildren().get(0)).getChildren().get(1)).getChildren().size();
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
            List<Element> instructionsList = ((Element)((Element)((Element) gpxDocument.getRootElement()).getChildren().get(0)).getChildren().get(1)).getChildren();
            for (Element instruction : instructionsList)
            {
                instructions.add(
                        new WayPoint(
                        Double.parseDouble(instruction.getAttributeValue(LATITUDE_TAG)),
                        Double.parseDouble(instruction.getAttributeValue(LONGITUDE_TAG)),
                        ((Element)instruction.getContent(new ElementFilter(ELEMENT_TAG)).get(0)).getValue()
                        ));
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
                        instruction.getChildText(ELEMENT_TAG))
                        );
            }
            return instructions;
        }
    }
}
