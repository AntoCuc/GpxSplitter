/**
 * Builds a Gpx from a gpx markup file and tests its features.
 *
 * @author Antonino Cucchiara
 */
package gpxsplitter.tools.loaders;

import gpxsplitter.tools.*;
import gpxsplitter.tools.loaders.GpxLoader;
import gpxsplitter.model.GpxType;
import gpxsplitter.model.Waypoint;
import org.jdom.Namespace;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public abstract class GpxLoaderTest
{

    protected GpxLoader gpxLoader;

    public abstract String getData();
    public abstract GpxLoader getGpxLoader()throws Exception;
    public abstract GpxType getExpectedType();

    public abstract void testGetTracksNum();
    public abstract void testIsGpxMultiTrack() throws FileNotValidException;

    @Before
    public void setUp() throws Exception
    {
        this.gpxLoader = getGpxLoader();
    }

    @Test
    public void testNamespace()
    {
        Namespace expected = Namespace.getNamespace(TestMedia.NAMESPACE);
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
