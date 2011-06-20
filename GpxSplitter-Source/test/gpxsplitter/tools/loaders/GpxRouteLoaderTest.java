/**
 * Builds a Gpx from a gpx route markup file and tests its features.
 *
 * @author Antonino Cucchiara
 */
package gpxsplitter.tools.loaders;

import gpxsplitter.tools.*;
import gpxsplitter.tools.loaders.GpxLoader;
import gpxsplitter.model.GpxType;
import java.io.ByteArrayInputStream;
import org.junit.Test;
import static org.junit.Assert.*;

public class GpxRouteLoaderTest extends GpxLoaderTest
{

    @Override
    public GpxLoader getGpxLoader() throws Exception
    {
        return new GpxLoader(new ByteArrayInputStream(this.getData().getBytes()), "");
    }

    @Override
    public String getData()
    {
        return TestMedia.getTestRoute();
    }

    @Override
    public GpxType getExpectedType()
    {
        return GpxType.Route;
    }

    @Test
    @Override
    public void testGetTracksNum()
    {
        assertEquals(0, gpxLoader.getTracksNum());
    }

    @Test
    @Override
    public void testIsGpxMultiTrack() throws FileNotValidException
    {
        assertFalse(this.gpxLoader.isGpxMultitrack());
    }
}
