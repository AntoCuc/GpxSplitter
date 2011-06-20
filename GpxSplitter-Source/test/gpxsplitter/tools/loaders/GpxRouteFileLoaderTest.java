/**
 * Builds a Gpx from a gpx route markup file and tests its features.
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

public class GpxRouteFileLoaderTest extends GpxFileLoaderTest
{

    @Override
    public GpxFileLoader getGpxLoader() throws Exception
    {
        return new GpxRouteFileLoader(new ByteArrayInputStream(this.getData().getBytes()), "");
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
    public void testGetTracksNum() throws IOException, JDOMException, FileNotValidException
    {
        assertEquals(0, GpxFileLoader.getTracksNum(getGpxDocument()));
    }

    @Test
    @Override
    public void testIsGpxMultiTrack() throws IOException, JDOMException, FileNotValidException
    {
        assertFalse(GpxFileLoader.isGpxMultitrack(getGpxDocument()));
    }
}
