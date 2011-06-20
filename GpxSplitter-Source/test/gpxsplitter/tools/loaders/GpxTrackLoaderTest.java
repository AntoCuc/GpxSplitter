/**
 * Builds a Gpx from a gpx track markup file and tests its features.
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

/**
 *
 * @author Antonio
 */
public final class GpxTrackLoaderTest extends GpxLoaderTest
{

    @Override
    public GpxLoader getGpxLoader() throws Exception
    {
        return new GpxLoader(new ByteArrayInputStream(this.getData().getBytes()), "");
    }

    @Override
    public String getData()
    {
        return TestMedia.getTestTrack();
    }

    @Override
    public GpxType getExpectedType()
    {
        return GpxType.Track;
    }

    @Test
    @Override
    public void testGetTracksNum()
    {
        assertEquals(1, gpxLoader.getTracksNum());
    }

    @Test
    @Override
    public void testIsGpxMultiTrack() throws FileNotValidException
    {
        assertFalse(this.gpxLoader.isGpxMultitrack());
    }
}
