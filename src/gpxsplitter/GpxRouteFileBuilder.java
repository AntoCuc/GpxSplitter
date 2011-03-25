package gpxsplitter;

import java.io.File;
import java.io.IOException;
import org.jdom.Document;
import org.jdom.Element;

/**
 *
 * @author anc6
 */
public class GpxRouteFileBuilder extends GpxFileBuilder{

    public GpxRouteFileBuilder(Gpx gpx) {
        super(gpx);
    }

    public void build(File file, int totalInstructionsNum, int filesNum) throws IOException {
        int fileNum = 1;
        int instrNum = 1;
        while (fileNum <= filesNum)
        {
            Document newGpxDocument = createNewGpx();
            //RteType newRte = newGpxDocument.getRootElement();

            Element rteSeg = new Element("rteseg");
            for(WayPoint wpt : gpx.getInstructions())
            {
                Element rtePt = new Element("rtePt");
                rtePt.setAttribute(Gpx.LATITUDE_TAG, wpt.getLatitude()+"");
                rtePt.setAttribute(Gpx.LONGITUDE_TAG, wpt.getLongitude()+"");
                Element ele = new Element(Gpx.ELEMENT_TAG);
                ele.setText(wpt.getElement()+"");
                rtePt.setContent(ele);
                rteSeg.setContent(rtePt);
            }
            newGpxDocument.setContent(rteSeg);

            saveFile(new File(file.getName() + "-" + fileNum + GPX_FORMAT), newGpxDocument);
            instrNum--; //The second route will start with the last wpt of the first
            fileNum++; // Proceed to next file
        }
    }
}
