package gpxsplitter;

import com.topografix.gpx.x1.x1.GpxDocument;
import com.topografix.gpx.x1.x1.GpxType;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author anc6
 */
public abstract class GpxFileBuilder implements GpxBuilder
{

    private static final String GPX_SPLITTER = "Gpx Splitter";
    public static final String GPX_FORMAT = ".gpx";
    protected Gpx gpx;

    public GpxFileBuilder(Gpx gpx)
    {
        this.gpx = gpx;
    }

    protected GpxDocument createNewGpx()
    {
        GpxDocument newGpxDocument = GpxDocument.Factory.newInstance();
        GpxType newGpx = newGpxDocument.addNewGpx();
        newGpx.setVersion(gpx.getVersion());
        newGpx.setCreator(GPX_SPLITTER);
        return newGpxDocument;
    }

    /**
     * This file is to be overriden in Unit tests.
     * TODO: is the dash necessary?
     * @param fileName
     * @param fileBuiltNum
     * @throws IOException
     */
    void saveFile(String fileName, GpxDocument newGpxDocument) throws IOException
    {
        String gpxContent = newGpxDocument.toString().replace("ns:", "");
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));
        bufferedWriter.write(gpxContent);
        bufferedWriter.close();
        //newGpxDocument.save(new File(fileName + "-" + fileBuiltNum + GPX_FORMAT));
    }
}
