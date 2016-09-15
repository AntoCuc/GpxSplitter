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

public final class GpxTrackFileLoaderTest extends GpxFileLoaderTest
{

    @Override
    public GpxFileLoader getGpxLoader() throws Exception
    {
        return new GpxTrackFileLoader(new ByteArrayInputStream(this.getData().getBytes()), "");
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
    public void testGetTracksNum() throws IOException, JDOMException, FileNotValidException
    {
        assertEquals(1, GpxFileLoader.getTracksNum(getGpxDocument()));
    }

    @Test
    @Override
    public void testIsGpxMultiTrack() throws IOException, JDOMException, FileNotValidException
    {
        assertFalse(GpxFileLoader.isGpxMultitrack(getGpxDocument()));
    }
}
