package gpxsplitter;

import com.topografix.gpx.x1.x1.GpxDocument;
import com.topografix.gpx.x1.x1.TrkType;
import com.topografix.gpx.x1.x1.TrksegType;
import com.topografix.gpx.x1.x1.WptType;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.ElementType;
import java.util.ArrayList;
import java.util.List;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;

/**
 *
 * @author anc6
 */
public final class Gpx
{

    private final GpxDocument gpxDocument;
    private final File gpxFile;
    private final GpxType gpxType;

    Gpx(File gpxFile) throws XmlException, IOException
    {
        this.gpxFile = gpxFile;
        this.gpxDocument = GpxDocument.Factory.parse(gpxFile);
        this.gpxType = getType();
    }

    String getVersion()
    {

        return gpxDocument.getGpx().getVersion();
    }

    GpxType getType() throws XmlException
    {
        if (gpxDocument.getGpx().getTrkArray().length > 0)
        {
            return GpxType.Track;
        }
        else if (gpxDocument.getGpx().getRteArray().length > 0)
        {
            return GpxType.Route;
        }
        else
        {
            throw new XmlException("The gpx does not seem to be of the track nor route type");
        }
    }

    int getNumOfInstructions() throws XmlException
    {
        if (gpxType == GpxType.Track)
        {
            return gpxDocument.getGpx().getTrkArray()[0].getTrksegArray()[0].getTrkptArray().length;
        }
        else if (gpxType == GpxType.Route)
        {
            return gpxDocument.getGpx().getRteArray()[0].getRteptArray().length;
        }
        else
        {
            throw new XmlException("The gpx does not seem to be of the track nor route type");
        }
    }

    String getFilePath()
    {
        return gpxFile.getAbsolutePath();
    }

    List<WayPoint> getIntructions()
    {
        List<WayPoint> instructions = new ArrayList<WayPoint>();
        if (gpxType == GpxType.Track)
        {
            WptType[] instr = gpxDocument.getGpx().getTrkArray()[0].getTrksegArray()[0].getTrkptArray();
            for (int i = 0; i < instr.length; i++)
            {
                instructions.add(new WayPoint(instr[i].getLat(),
                        instr[i].getLon(), instr[i].getEle()));
            }
            return instructions;
        }
        else
        {
            WptType[] instr = gpxDocument.getGpx().getRteArray()[0].getRteptArray();
            for (int i = 0; i < instr.length; i++)
            {
                instructions.add(new WayPoint(instr[i].getLat(),
                        instr[i].getLon(), instr[i].getEle()));
            }
            return instructions;
        }
    }
}
