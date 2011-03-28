/**
 * Gpx loader test builds a Gpx from a gpx markup file and tests its features.
 *
 * @author Antonino Cucchiara
 */

package gpxsplitter.Tools;

import gpxsplitter.Model.GpxType;
import gpxsplitter.Model.Waypoint;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GpxLoaderTest
{
    public static final String NAMESPACE = "http://www.topografix.com/GPX/1/1";

    private GpxLoader gpxLoader;

    //public abstract String loadData();

    @Before
    public void setUp() throws JDOMException, IOException
    {
        String data = "<?xml version=\"1.0\"?>"
                + "<gpx version=\"1.1\" "
                + "creator=\"Bikely - http://www.bikely.com\" "
                + "xmlns=\""+ NAMESPACE+"\" "
                + "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "
                + "xsi:schemaLocation=\"http://www.topografix.com/GPX/1/1 http://www.topografix.com/GPX/1/1/gpx.xsd\"> "
                + "<trk>"
                + "<name>Test track</name>"
                + "<desc>Test track description</desc>"
                + "<trkseg>"
                + "<trkpt lat = \"51.2404704333\" lon = \"-0.1724553109\">"
                + "<ele>75</ele >"
                + "<time>2011 - 02 - 06 08:46:10></time>"
                + "</trkpt>"
                + "<trkpt lat = \"51.3\" lon = \"-0.1\">"
                + "<ele>76</ele >"
                + "<time>2011 - 02 - 06 08:46:10></time>"
                + "</trkpt>"
                + "</trkseg></trk></gpx>";

        this.gpxLoader = new GpxLoader(new ByteArrayInputStream(data.getBytes()));
    }

    ;

    @Test
    public void testNamespace()
    {
        Namespace expected = Namespace.getNamespace(NAMESPACE);
        assertEquals(expected, this.gpxLoader.getLoadedGpx().getNamespace());
    }

    @Test
    public void testGpxType()
    {
        assertEquals(GpxType.Track, this.gpxLoader.getLoadedGpx().getGpxType());
    }

    @Test
    public void testVersion()
    {
        assertEquals("1.1", this.gpxLoader.getLoadedGpx().getVersion());
    }

    @Test
    public void testInstructions()
    {
        assertEquals(2, this.gpxLoader.getLoadedGpx().getInstructions().size());
        assertEquals(new Waypoint(51.2404704333, -0.1724553109, "75").toString(),
                this.gpxLoader.getLoadedGpx().getInstructions().get(0).toString());
        assertEquals(new Waypoint(51.3, -0.1, "76").toString(),
                this.gpxLoader.getLoadedGpx().getInstructions().get(1).toString());
    }
}
