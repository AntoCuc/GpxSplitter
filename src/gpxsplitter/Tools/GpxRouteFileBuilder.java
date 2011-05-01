/**
 * Builds gpx routes from a gpx.
 *
 * @author Antonino Cucchiara
 */

package gpxsplitter.Tools;

import gpxsplitter.Controller;
import gpxsplitter.Model.Gpx;
import gpxsplitter.Model.Waypoint;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;

public final class GpxRouteFileBuilder extends GpxFileBuilder{

    public GpxRouteFileBuilder(Gpx gpx) {
        super(gpx);
    }

    @Override
    public void build(File file, int preferedInstrNum) throws IOException {
        int fileNum = 1;
        int instrNum = 1;
        while (fileNum <= fileNum)
        {
            Document newGpxDocument = createGpxTemplate();

            Element rteSeg = new Element("rteseg");
            for(Waypoint wpt : gpx.getInstructions())
            {
                Element rtePt = new Element("rtePt");
                rtePt.setAttribute(Gpx.LATITUDE_TAG, wpt.getLatitude());
                rtePt.setAttribute(Gpx.LONGITUDE_TAG, wpt.getLongitude());
                Element ele = new Element(Gpx.ELEMENT_TAG);
                ele.setText(wpt.getElement());
                rtePt.setContent(ele);
                rteSeg.setContent(rtePt);
            }
            newGpxDocument.setContent(rteSeg);

            saveFile(new File(file.getName() + "-" + fileNum + GPX_FORMAT), newGpxDocument);
            instrNum--; //The second route will start with the last wpt of the first
            fileNum++; // Proceed to next file
        }
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
            newGpxDocument.getRootElement().setContent(route);
            gpxList.add(newGpxDocument);
            fileNum++; // Proceed to next file
        }
        return gpxList;

    }
}
