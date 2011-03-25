package gpxsplitter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;

/**
 *
 * @author Antonio
 */
public class GpxLoader {

    private final Document gpxDocument;
    private final Gpx newGpx;

    public GpxLoader(File file) throws JDOMException, IOException
    {
        SAXBuilder builder = new SAXBuilder();
        this.gpxDocument = builder.build(file);
        
        this.newGpx = new Gpx(getNamespace(), getType(), getVersion(), file.getAbsolutePath(), getInstructions());
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
        else if (((Element) gpxDocument.getRootElement()).getChild(Gpx.RTE_TAG) != null)
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

        return ((Element) gpxDocument.getRootElement()).getAttributeValue(Gpx.VERSION);
    }

    private List<WayPoint> getInstructions()
    {
        List<WayPoint> instructions = new ArrayList<WayPoint>();
        if (getType() == GpxType.Track)
        {
            List<Element> instructionsList = gpxDocument.getRootElement().getChild(Gpx.TRK_TAG, getNamespace()).getChild(Gpx.TRACKSEGMENT_TAG, getNamespace()).getChildren();
            for (Element instruction : instructionsList)
            {
                instructions.add(
                        new WayPoint(
                        Double.parseDouble(instruction.getAttributeValue(Gpx.LATITUDE_TAG)),
                        Double.parseDouble(instruction.getAttributeValue(Gpx.LONGITUDE_TAG)),
                        instruction.getChildText(Gpx.ELEMENT_TAG, getNamespace())));
            }
            return instructions;
        }
        else
        {
            List<Element> instructionsList = ((Element) gpxDocument.getRootElement()).getChild(Gpx.RTE_TAG).getChild("rteseg").getChildren("rtept");
            for (Element instruction : instructionsList)
            {
                instructions.add(
                        new WayPoint(
                        Double.parseDouble(instruction.getAttributeValue(Gpx.LATITUDE_TAG)),
                        Double.parseDouble(instruction.getAttributeValue(Gpx.LONGITUDE_TAG)),
                        instruction.getChildText(Gpx.ELEMENT_TAG, newGpx.getNamespace())));
            }
            return instructions;
        }
    }


}
