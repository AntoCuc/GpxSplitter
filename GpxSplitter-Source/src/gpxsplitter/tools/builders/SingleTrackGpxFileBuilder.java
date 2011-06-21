/**
 * Builds multiple single track gpx files from a single multi track gpx file.
 *
 * @author Antonino Cucchiara
 */
package gpxsplitter.tools.builders;

import gpxsplitter.model.Gpx;
import gpxsplitter.model.Itinerary;
import gpxsplitter.model.Waypoint;
import java.util.ArrayList;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;

public final class SingleTrackGpxFileBuilder extends GpxFileBuilder
{

    public SingleTrackGpxFileBuilder(Gpx gpx)
    {
        super(gpx);
    }

    /**
     * Builds a split gpx not cosidering the instructions number but focusing
     * on having a single track per gpx.
     * @param preferredInstrNum Ignored
     * @return a list of single tracked gpx files
     */
    @Override
    public List<Document> buildSplitGpx(int preferredInstrNum)
    {
        List<Document> gpxDocumentsList = new ArrayList<Document>();
        List<Itinerary> itineraries = gpx.getItineraries();


        for (int i = 0; i < itineraries.size(); i++)
        {
            Itinerary currentItinerary = itineraries.get(i);

            Document newGpxDocument = createGpxTemplate();
            Element track = new Element(Gpx.TRK_TAG);
            Element trackSegment = new Element(Gpx.TRACKSEGMENT_TAG);
            track.setContent(trackSegment);

            List<Waypoint> waypoints = currentItinerary.getWaypoints();

            for (int j = 0; j < waypoints.size(); j++)
            {
                try
                {
                    Waypoint currentInstruction = waypoints.get(j);
                    Element trackPoint = new Element(Gpx.TRACKPOINT);
                    trackPoint.setAttribute(Gpx.LATITUDE_TAG, currentInstruction.getLatitude());
                    trackPoint.setAttribute(Gpx.LONGITUDE_TAG, currentInstruction.getLongitude());
                    Element ele = new Element(Gpx.ELEMENT_TAG);
                    ele.setText(currentInstruction.getElement());
                    trackPoint.setContent(ele);
                    trackSegment.addContent(trackPoint);
                }
                catch (IndexOutOfBoundsException e)
                {
                    //Break the loop, the file does not have any further instructions.
                    break;
                }
            }
            newGpxDocument.getRootElement().setContent(track);
            gpxDocumentsList.add(newGpxDocument);
        }
        return gpxDocumentsList;
    }
}
