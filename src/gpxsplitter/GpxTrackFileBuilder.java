package gpxsplitter;

import java.io.File;
import java.io.IOException;
import org.jdom.Document;
import org.jdom.Element;

/**
 *
 * @author anc6
 */
public class GpxTrackFileBuilder extends GpxFileBuilder
{
    private static final String TRACKSEGMENT_TAG = "trkseg";
    private static final String TRACKPOINT = "trkpt";

    public GpxTrackFileBuilder(Gpx gpx)
    {
        super(gpx);
    }

    /**
     * This method will build a set of GPX files given the file name, number of
     * GPX instructions per file and the number of files to be built.
     * TODO: split this method so it returns a testable gpx document.
     * TODO: do we need to pass in the num of instructions
     * TODO: fix multiple files bug
     * @param file
     * @param instNum
     * @param filesNum
     * @throws IOException
     */
    public void build(File file, int totalInstructionsNum, int filesNum) throws IOException
    {
        int fileNum = 1;
        int instrNum = 1;
        while (fileNum <= filesNum)
        {
            Document newGpxDocument = createNewGpx();
            //RteType newRte = newGpxDocument.getRootElement();

            Element track = new Element(Gpx.TRK_TAG);
            Element trackSegment = new Element(TRACKSEGMENT_TAG);
            track.setContent(trackSegment);
            for(WayPoint wpt : gpx.getIntructions())
            {
                Element trackPoint = new Element(TRACKPOINT);
                trackPoint.setAttribute(Gpx.LATITUDE_TAG, wpt.getLatitude()+"");
                trackPoint.setAttribute(Gpx.LONGITUDE_TAG, wpt.getLongitude()+"");
                Element ele = new Element(Gpx.ELEMENT_TAG);
                ele.setText(wpt.getElement());
                trackPoint.setContent(ele);
                trackSegment.addContent(trackPoint);
            }
            newGpxDocument.getRootElement().setContent(track);

            saveFile(new File(file + "-" + fileNum + GPX_FORMAT), newGpxDocument);
            instrNum--; //The second route will start with the last wpt of the first
            fileNum++; // Proceed to next file
        }

    }
}
