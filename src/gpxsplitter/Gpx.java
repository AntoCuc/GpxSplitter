package gpxsplitter;

import java.util.List;
import org.jdom.Namespace;

/**
 *
 * @author anc6
 */
public final class Gpx
{

    public static final String TRACKSEGMENT_TAG = "trkseg";
    public static final String TRACKPOINT = "trkpt";
    public static final String ELEMENT_TAG = "ele";
    public static final String LATITUDE_TAG = "lat";
    public static final String LONGITUDE_TAG = "lon";
    public static final String TRK_TAG = "trk";
    public static final String RTE_TAG = "rte";
    public static final String VERSION = "version";
    private final Namespace namespace;
    private final GpxType gpxType;
    private final String version;
    private final List<WayPoint> instructions;
    private final String filePath;

    Gpx(Namespace namespace, GpxType gpxType, String version, String filePath, List<WayPoint> instructions)
    {
        this.namespace = namespace;
        this.gpxType = gpxType;
        this.version = version;
        this.instructions = instructions;
        this.filePath = filePath;
    }

    public GpxType getGpxType()
    {
        return gpxType;
    }

    int getNumOfInstructions()
    {
        return this.instructions.size();
    }

    public List<WayPoint> getInstructions()
    {
        return instructions;
    }

    Namespace getNamespace()
    {
        return namespace;
    }

    String getVersion()
    {
        return version;
    }

    String getType()
    {
        return gpxType.toString();
    }

    String getFilePath()
    {
        return filePath;
    }
}
