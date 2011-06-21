/**
 * Builds a Gpx from a gpx route markup file.
 *
 * @author Antonino Cucchiara
 */
package gpxsplitter.tools.loaders;

import gpxsplitter.model.Gpx;
import gpxsplitter.model.Itinerary;
import gpxsplitter.model.Waypoint;
import gpxsplitter.tools.FileNotValidException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;

public final class GpxRouteFileLoader extends GpxFileLoader{

    public GpxRouteFileLoader(Document gpxDocument, String gpxFilePath) throws JDOMException, IOException, FileNotValidException
    {
        super(gpxDocument, gpxFilePath);
    }

    public GpxRouteFileLoader(InputStream fileInputStream, String gpxFilePath) throws JDOMException, IOException, FileNotValidException
    {
        super(fileInputStream, gpxFilePath);
    }

    @Override
    List<Itinerary> getItineraries() throws FileNotValidException
    {
        List<Waypoint> waypoints = new ArrayList<Waypoint>();
        List<Element> instructionsList = gpxDocument.getRootElement().getChild(Gpx.RTE_TAG, getNamespace()).getChildren(Gpx.RTEPT_TAG, getNamespace());
            for (Element instruction : instructionsList)
            {
                waypoints.add(
                        new Waypoint(
                        Double.parseDouble(instruction.getAttributeValue(Gpx.LATITUDE_TAG)),
                        Double.parseDouble(instruction.getAttributeValue(Gpx.LONGITUDE_TAG)),
                        instruction.getChildText(Gpx.ELEMENT_TAG, getNamespace())));
            }
            List<Itinerary> itineraries = new ArrayList<Itinerary>();
            itineraries.add(new Itinerary(waypoints));
            return itineraries;
    }
}
