package gpxsplitter.model.loader;

import gpxsplitter.model.generated.GpxType;
import java.io.ByteArrayInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.xml.bind.JAXBException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author antonio
 */
public class GpxFileLoaderTest {

    @Test
    public void testLoadNoContent() throws Exception {
        GpxFileLoader fileLoader = new GpxFileLoader();
        try {
            fileLoader.load(new ByteArrayInputStream(new byte[0]));
            fail("JAXB Exception not thrown!");
        } catch (JAXBException ex) {
            //Pass
        }
    }

    @Test
    public void testLoadBikely() throws Exception {
        GpxFileLoader fileLoader = new GpxFileLoader();
        final Path testGpxPath = 
                Paths.get("src/test/resources/bikely-track-sample.gpx");
        byte[] gpx = Files.readAllBytes(testGpxPath);
        GpxType actual = fileLoader.load(new ByteArrayInputStream(gpx));
        assertEquals("1.1", actual.getVersion());
//        assertEquals(1, actual.getTracksNum());
//        assertEquals(4, actual.getUnderlying().getTrk().get(0)
//                .getTrkseg().get(0).getTrkpt().size());
    }
    
    @Test
    public void testLoadEtrex() throws Exception {
        GpxFileLoader fileLoader = new GpxFileLoader();
        final Path testGpxPath = 
                Paths.get("src/test/resources/garmin-etrex-track-sample.gpx");
        byte[] gpx = Files.readAllBytes(testGpxPath);
        GpxType actual = fileLoader.load(new ByteArrayInputStream(gpx));
        assertEquals("1.1", actual.getVersion());
//        assertEquals(1, actual.getTracksNum());
//        assertEquals("24-JUL-09", actual.getUnderlying()
//                .getTrk().get(0).getName());
//        assertEquals(4, actual.getUnderlying().getTrk().get(0)
//                .getTrkseg().get(0).getTrkpt().size());
    }
    
    @Test
    public void testLoadTopographixRoute() throws Exception {
        GpxFileLoader fileLoader = new GpxFileLoader();
        final Path testGpxPath = 
                Paths.get("src/test/resources/topographix-route-sample.gpx");
        byte[] gpx = Files.readAllBytes(testGpxPath);
        GpxType actual = fileLoader.load(new ByteArrayInputStream(gpx));
        assertEquals("1.1", actual.getVersion());
//        assertEquals("Patrick's Route", actual.getUnderlying()
//                .getRte().get(0).getName());
//        assertEquals(4, actual.getUnderlying().getRte().get(0)
//                .getRtept().size());
    }
    
    @Test
    public void testLoadTopographixTrack() throws Exception {
        GpxFileLoader fileLoader = new GpxFileLoader();
        final Path testGpxPath = 
                Paths.get("src/test/resources/topographix-track-sample.gpx");
        byte[] gpx = Files.readAllBytes(testGpxPath);
        GpxType actual = fileLoader.load(new ByteArrayInputStream(gpx));
        assertEquals("1.1", actual.getVersion());
//        assertEquals(1, actual.getTracksNum());
//        assertEquals("Patrick's Track", actual.getUnderlying()
//                .getTrk().get(0).getName());
//        assertEquals(4, actual.getUnderlying().getTrk().get(0)
//                .getTrkseg().get(0).getTrkpt().size());
    }
    
    @Ignore
    @Test
    public void testLoadTopographixWaypoints() throws Exception {
        GpxFileLoader fileLoader = new GpxFileLoader();
        final Path testGpxPath = 
               Paths.get("src/test/resources/topographix-waypoints-sample.gpx");
        byte[] gpx = Files.readAllBytes(testGpxPath);
        GpxType actual = fileLoader.load(new ByteArrayInputStream(gpx));
        assertEquals("1.1", actual.getVersion());
//        assertEquals(4, actual.getUnderlying().getWpt().size());
    }
}
