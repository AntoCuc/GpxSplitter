/**
 * Builds a Gpx from a gpx track markup file.
 *
 * @author Antonino Cucchiara
 */
package gpxsplitter.tools.loaders;

import gpxsplitter.model.Gpx;
import gpxsplitter.model.Waypoint;
import gpxsplitter.tools.FileNotValidException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;

public final class GpxTrackFileLoader extends GpxFileLoader{

    public GpxTrackFileLoader(Document gpxDocument, String gpxFilePath) throws JDOMException, IOException, FileNotValidException
    {
        super(gpxDocument, gpxFilePath);
    }

    public GpxTrackFileLoader(InputStream fileInputStream, String gpxFilePath) throws JDOMException, IOException, FileNotValidException
    {
        super(fileInputStream, gpxFilePath);
    }

    @Override
    List<Waypoint> getInstructions() throws FileNotValidException
    {
        List<Waypoint> instructions = new ArrayList<Waypoint>();
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


}
