package gpxsplitter;

import com.topografix.gpx.x1.x1.GpxDocument;
import com.topografix.gpx.x1.x1.RteType;
import com.topografix.gpx.x1.x1.WptType;
import java.io.IOException;
import java.util.List;
import org.apache.xmlbeans.XmlException;

/**
 *
 * @author anc6
 */
public class GpxRouteFileBuilder extends GpxFileBuilder{

    public GpxRouteFileBuilder(Gpx gpx) {
        super(gpx);
    }

    public void build(String fileName, int totalInstructionsNum, int filesNum) throws XmlException, IOException {
        int fileNum = 1;
        int instrNum = 1;
        while (fileNum <= filesNum)
        {
            GpxDocument newGpxDocument = createNewGpx();
            RteType newRte = newGpxDocument.getGpx().addNewRte();

            for (; instrNum < gpx.getNumOfInstructions(); instrNum++)
            {
                List<WayPoint> waypoints = gpx.getIntructions();
                WptType newRtePt = newRte.addNewRtept();
                newRtePt.setLat(waypoints.get(instrNum).getLatitude());
                newRtePt.setLon(waypoints.get(instrNum).getLongitude());
                newRtePt.setEle(waypoints.get(instrNum).getElement());
            }
            saveFile(fileName + "-" + fileNum + GPX_FORMAT, newGpxDocument);
            instrNum--; //The second route will start with the last wpt of the first
            fileNum++; // Proceed to next file
        }
    }
}
