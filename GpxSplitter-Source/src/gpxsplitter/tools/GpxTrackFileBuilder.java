/**
 * Builds gpx tracks from a gpx.
 *
 * @author Antonino Cucchiara
 */
package gpxsplitter.tools;

import gpxsplitter.model.Waypoint;
import gpxsplitter.model.Gpx;
import java.util.ArrayList;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;

public final class GpxTrackFileBuilder extends GpxFileBuilder
{

    public GpxTrackFileBuilder(Gpx gpx)
    {
        super(gpx);
    }

    @Override
    public List<Document> buildSplitGpx(int preferredInstrNum)
    {
        List<Document> gpxList = new ArrayList<Document>();
        List<Waypoint> instructions = gpx.getInstructions();
        int splitFiles = howManyFiles(instructions.size(), preferredInstrNum);

        int currInstr = 0;
        int fileNum = 1;
        while (fileNum <= splitFiles)
        {
            Document newGpxDocument = createGpxTemplate();

            Element track = new Element(Gpx.TRK_TAG);
            Element trackSegment = new Element(Gpx.TRACKSEGMENT_TAG);
            track.setContent(trackSegment);

            for(int i=0; i<preferredInstrNum; i++)
            {
                try
                {
                    Waypoint currentInstruction = instructions.get(currInstr);
                    Element trackPoint = new Element(Gpx.TRACKPOINT);
                    trackPoint.setAttribute(Gpx.LATITUDE_TAG, currentInstruction.getLatitude());
                    trackPoint.setAttribute(Gpx.LONGITUDE_TAG, currentInstruction.getLongitude());
                    Element ele = new Element(Gpx.ELEMENT_TAG);
                    ele.setText(currentInstruction.getElement());
                    trackPoint.setContent(ele);
                    trackSegment.addContent(trackPoint);
                    currInstr++;
                }
                catch(IndexOutOfBoundsException e)
                {
                    //Break the loop, the file does not have any further instructions.
                    break;
                }
            }
            newGpxDocument.getRootElement().setContent(track);
            gpxList.add(newGpxDocument);
            fileNum++; // Proceed to next file
        }
        return gpxList;
    }
}
