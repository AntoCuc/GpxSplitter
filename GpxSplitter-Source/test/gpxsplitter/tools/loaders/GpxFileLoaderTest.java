/**
 * Builds a Gpx from a gpx markup file and tests its features.
 *
 * @author Antonino Cucchiara
 */
package gpxsplitter.tools.loaders;

import gpxsplitter.tools.*;
import gpxsplitter.model.GpxType;
import gpxsplitter.model.Waypoint;
import java.io.ByteArrayInputStream;
import org.jdom.input.SAXBuilder;
import java.io.IOException;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public abstract class GpxFileLoaderTest
{

    protected GpxFileLoader gpxLoader;

    public abstract String getData();

    public abstract GpxFileLoader getGpxLoader() throws Exception;

    public abstract GpxType getExpectedType();

    public abstract void testGetTracksNum() throws IOException, JDOMException, FileNotValidException;

    public abstract void testIsGpxMultiTrack() throws IOException, JDOMException, FileNotValidException;

    @Before
    public void setUp() throws Exception
    {
        this.gpxLoader = getGpxLoader();
    }

    @Test
    public void testNamespace() throws FileNotValidException, IOException, JDOMException
    {
        Namespace expected = Namespace.getNamespace(TestMedia.NAMESPACE);
        assertEquals(expected, this.gpxLoader.load().getNamespace());
    }

    @Test
    public void testGpxType() throws FileNotValidException, IOException, JDOMException
    {
        assertEquals(this.getExpectedType(), this.gpxLoader.load().getGpxType());
    }

    @Test
    public void testVersion() throws FileNotValidException, IOException, JDOMException
    {
        assertEquals("1.1", this.gpxLoader.load().getVersion());
    }

    @Test
    public void testInstructions() throws FileNotValidException, IOException, JDOMException
    {
        assertEquals(2, this.gpxLoader.load().getItineraries().get(0).getWaypoints().size());
        assertEquals(new Waypoint(51.2404704333, -0.1724553109, "75").toString(),
                this.gpxLoader.load().getItineraries().get(0).getWaypoints().get(0).toString());
        assertEquals(new Waypoint(51.3, -0.1, "76").toString(),
                this.gpxLoader.load().getItineraries().get(0).getWaypoints().get(1).toString());
    }

    Document getGpxDocument() throws IOException, JDOMException, FileNotValidException
    {
        return new SAXBuilder().build(new ByteArrayInputStream(this.getData().getBytes()));
    }
}
