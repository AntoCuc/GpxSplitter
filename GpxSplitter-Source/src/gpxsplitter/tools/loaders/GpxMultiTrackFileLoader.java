/**
 * Builds a Gpx from a gpx markup file with multiple tracks.
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

public final class GpxMultiTrackFileLoader extends GpxFileLoader
{

    public GpxMultiTrackFileLoader(Document gpxDocument, String gpxFilePath) throws JDOMException, IOException, FileNotValidException
    {
        super(gpxDocument, gpxFilePath);
    }

    public GpxMultiTrackFileLoader(InputStream fileInputStream, String gpxFilePath) throws JDOMException, IOException, FileNotValidException
    {
        super(fileInputStream, gpxFilePath);
    }

    @Override
    List<Itinerary> getItineraries() throws FileNotValidException
    {
        List<Itinerary> itineraries = new ArrayList<Itinerary>();

        List<Element> tracksList = gpxDocument.getRootElement().getChildren(Gpx.TRK_TAG, getNamespace());
        for (Element track : tracksList)
        {
            List<Waypoint> waypoints = new ArrayList<Waypoint>();

            List<Element> waypointsList = track.getChild(Gpx.TRACKSEGMENT_TAG, getNamespace()).getChildren();
            for (Element waypoint : waypointsList)
            {
                waypoints.add(
                        new Waypoint(
                        Double.parseDouble(waypoint.getAttributeValue(Gpx.LATITUDE_TAG)),
                        Double.parseDouble(waypoint.getAttributeValue(Gpx.LONGITUDE_TAG)),
                        waypoint.getChildText(Gpx.ELEMENT_TAG, getNamespace())));
            }
            itineraries.add(new Itinerary(waypoints));
        }
        return itineraries;
    }
}
