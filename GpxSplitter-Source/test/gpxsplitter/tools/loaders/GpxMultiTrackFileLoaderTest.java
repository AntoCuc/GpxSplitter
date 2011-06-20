/**
 * Builds a Gpx from a gpx track markup file and tests its features.
 *
 * @author Antonino Cucchiara
 */
package gpxsplitter.tools.loaders;

import gpxsplitter.tools.*;
import gpxsplitter.model.GpxType;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.jdom.JDOMException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Antonio
 */
public final class GpxMultiTrackFileLoaderTest extends GpxFileLoaderTest
{

    @Override
    public GpxFileLoader getGpxLoader() throws Exception
    {
        return new GpxMultiTrackFileLoader(new ByteArrayInputStream(this.getData().getBytes()), "");
    }

    @Override
    public String getData()
    {
        return TestMedia.getTestGpxWithMultipleTracks();
    }

    @Override
    public GpxType getExpectedType()
    {
        return GpxType.Track;
    }

    @Test
    @Override
    public void testGetTracksNum()throws IOException, JDOMException, FileNotValidException
    {
        assertEquals(2, GpxFileLoader.getTracksNum(getGpxDocument()));
    }

    @Test
    @Override
    public void testIsGpxMultiTrack() throws IOException, JDOMException, FileNotValidException
    {
        assertTrue(GpxFileLoader.isGpxMultitrack(getGpxDocument()));
    }
}
