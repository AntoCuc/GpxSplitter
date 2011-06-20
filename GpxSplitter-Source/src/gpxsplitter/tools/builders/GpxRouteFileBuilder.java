/**
 * Builds gpx routes from a gpx.
 *
 * @author Antonino Cucchiara
 */

package gpxsplitter.tools.builders;

import gpxsplitter.tools.*;
import gpxsplitter.model.Gpx;
import gpxsplitter.model.Waypoint;
import java.util.ArrayList;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;

public final class GpxRouteFileBuilder extends GpxFileBuilder{

    public GpxRouteFileBuilder(Gpx gpx) {
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

            Element route = new Element(Gpx.RTE_TAG);

            for(int i=0; i<preferredInstrNum; i++)
            {
                try
                {
                    Waypoint currentInstruction = instructions.get(currInstr);
                    Element routePt = new Element(Gpx.RTEPT_TAG);
                    routePt.setAttribute(Gpx.LATITUDE_TAG, currentInstruction.getLatitude());
                    routePt.setAttribute(Gpx.LONGITUDE_TAG, currentInstruction.getLongitude());
                    Element ele = new Element(Gpx.ELEMENT_TAG);
                    ele.setText(currentInstruction.getElement());
                    routePt.setContent(ele);
                    //For now the name is the same as the Element
                    Element name = new Element(Gpx.ELEMENT_TAG);
                    name.setText(currentInstruction.getElement());
                    routePt.setContent(name);
                    route.addContent(routePt);
                    currInstr++;
                }
                catch(IndexOutOfBoundsException e)
                {
                    //Break the loop, the file does not have any further instructions.
                    break;
                }
            }
            newGpxDocument.getRootElement().setContent(route);
            gpxList.add(newGpxDocument);
            fileNum++; // Proceed to next file
        }
        return gpxList;

    }
}
