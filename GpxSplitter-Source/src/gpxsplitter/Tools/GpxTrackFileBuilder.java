/**
 * Builds gpx tracks from a gpx.
 *
 * @author Antonino Cucchiara
 */
package gpxsplitter.Tools;

import gpxsplitter.Model.Gpx;
import gpxsplitter.Model.Waypoint;
import java.io.File;
import java.io.IOException;
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

    /**
     * This method will build a set of GPX files given the file name, number of
     * GPX instructions per file and the number of files to be built.
     * TODO: fix multiple files bug
     * @param file
     * @param instNum
     * @param filesNum
     * @throws IOException
     */
    @Override
    public void build(File file, int preferedInstrNum) throws IOException
    {
        int fileNum = 1;
        List<Document> docs = buildSplitGpx(preferedInstrNum);
        for(Document newGpxDocument : docs)
        {
            saveFile(new File(stripExtension(file.getAbsolutePath(), GPX_FORMAT) + "-" + fileNum + GPX_FORMAT), newGpxDocument);
            fileNum++;
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

            Element track = new Element(Gpx.TRK_TAG);
            Element trackSegment = new Element(Gpx.TRACKSEGMENT_TAG);
            track.setContent(trackSegment);

            for(int i=0; i<preferredInstrNum; i++)
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
            newGpxDocument.getRootElement().setContent(track);
            gpxList.add(newGpxDocument);
            fileNum++; // Proceed to next file
        }
        return gpxList;
    }
}
