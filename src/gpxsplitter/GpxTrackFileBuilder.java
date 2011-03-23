package gpxsplitter;

import com.topografix.gpx.x1.x1.GpxDocument;
import com.topografix.gpx.x1.x1.TrksegType;
import com.topografix.gpx.x1.x1.WptType;
import java.io.IOException;
import java.util.List;
import org.apache.xmlbeans.XmlException;

/**
 *
 * @author anc6
 */
public class GpxTrackFileBuilder extends GpxFileBuilder
{

    public GpxTrackFileBuilder(Gpx gpx)
    {
        super(gpx);
    }

    /**
     * This method will build a set of GPX files given the file name, number of
     * GPX instructions per file and the number of files to be built.
     * TODO: split this method so it returns a testable gpx document.
     * TODO: do we need to pass in the num of instructions
     * @param fileName
     * @param instNum
     * @param filesNum
     * @throws IOException
     */
    public void build(String fileName, int totalInstructionsNum, int filesNum) throws XmlException, IOException
    {
        int fileNum = 1;
        while (fileNum <= filesNum)
        {
            GpxDocument newGpxDocument = createNewGpx();
            TrksegType newTrkseg = newGpxDocument.getGpx().addNewTrk().addNewTrkseg();

            for (int i = 0; i < gpx.getNumOfInstructions(); i++)
            {
                List<WayPoint> waypoints = gpx.getIntructions();
                WptType newTrkPoint = newTrkseg.addNewTrkpt();
                newTrkPoint.setLat(waypoints.get(i).getLatitude());
                newTrkPoint.setLon(waypoints.get(i).getLongitude());
                newTrkPoint.setEle(waypoints.get(i).getElement());
            }
            saveFile(fileName + "-" + fileNum + GPX_FORMAT, newGpxDocument);
            fileNum++;
        }

    }
}
