package gpxsplitter.model;

import gpxsplitter.GpxDescriptor;
import gpxsplitter.tools.FileNotValidException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author antonio
 */
public class GpxTest {

    @Test
    public void testGetRouteTypeDescriptor() throws Exception {
        GpxType gpx = new GpxType();
        gpx.getRte().add(new RteType());
        assertEquals(GpxDescriptor.Route, new Gpx(gpx).getDescriptor());
    }

    @Test
    public void testGetTrackTypeDescriptor() throws Exception {
        GpxType gpx = new GpxType();
        gpx.getTrk().add(new TrkType());
        assertEquals(GpxDescriptor.Track, new Gpx(gpx).getDescriptor());
    }

    /**
     * TODO: Introduce support for waypoint based GPX files.
     *
     * @throws Exception
     */
    @Test
    public void testGetWaypointTypeDescriptor() throws Exception {
        GpxType gpx = new GpxType();
        gpx.getWpt().add(new WptType());
        try {
            new Gpx(gpx).getDescriptor();
        } catch (FileNotValidException ex) {
            assertEquals(FileNotValidException.ERROR_MSG, ex.getMessage());
        }
    }

    @Test
    public void testGetEmptyTypeDescriptor() throws Exception {
        GpxType gpx = new GpxType();
        try {
            new Gpx(gpx).getDescriptor();
        } catch (FileNotValidException ex) {
            assertEquals(FileNotValidException.ERROR_MSG, ex.getMessage());
        }
    }
}
