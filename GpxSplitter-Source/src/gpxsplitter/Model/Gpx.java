/**
 * Behold the gpx. Representation of the gpx.
 *
 * @author Antonino Cucchiara
 */

package gpxsplitter.model;

import java.util.List;
import org.jdom.Namespace;

public final class Gpx
{

    public static final String TRACKSEGMENT_TAG = "trkseg";
    public static final String TRACKPOINT = "trkpt";
    public static final String ELEMENT_TAG = "ele";
    public static final String LATITUDE_TAG = "lat";
    public static final String LONGITUDE_TAG = "lon";
    public static final String TRK_TAG = "trk";
    public static final String RTE_TAG = "rte";
    public static final String RTEPT_TAG = "rtept";
    public static final String RTEPT_NAME_TAG = "name";
    public static final String VERSION = "version";
    private final Namespace namespace;
    private final GpxType gpxType;
    private final String version;
    private final List<Waypoint> instructions;
    private final String filePath;

    public Gpx(Namespace namespace, GpxType gpxType, String version, String filePath, List<Waypoint> instructions)
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

    public int getNumOfInstructions()
    {
        return this.instructions.size();
    }

    public List<Waypoint> getInstructions()
    {
        return instructions;
    }

    public Namespace getNamespace()
    {
        return namespace;
    }

    public String getVersion()
    {
        return version;
    }

    public String getType()
    {
        return gpxType.toString();
    }

    public String getFilePath()
    {
        return filePath;
    }
}
