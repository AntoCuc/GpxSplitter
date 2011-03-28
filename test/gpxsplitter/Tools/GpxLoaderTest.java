/**
 * Builds a Gpx from a gpx markup file and tests its features.
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

public abstract class GpxLoaderTest
{

    public static final String NAMESPACE = "http://www.topografix.com/GPX/1/1";
    private GpxLoader gpxLoader;

    public abstract String getData();
    public abstract GpxType getExpectedType();

    @Before
    public void setUp() throws JDOMException, IOException, FileNotValidException
    {
        this.gpxLoader = new GpxLoader(new ByteArrayInputStream(this.getData().getBytes()), "");
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
        assertEquals(this.getExpectedType(), this.gpxLoader.getLoadedGpx().getGpxType());
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
