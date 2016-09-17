/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gpxsplitter.tools.loaders;

import gpxsplitter.GpxDescriptor;
import gpxsplitter.model.GpxType;
import gpxsplitter.model.RteType;
import gpxsplitter.model.TrkType;
import gpxsplitter.model.WptType;
import gpxsplitter.tools.FileNotValidException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author antonio
 */
public class GpxFileLoaderTest {
    
    public GpxFileLoaderTest() {
    }

    @Test
    public void testGetRouteTypeDescriptor() throws Exception {
        GpxType gpx = new GpxType();
        gpx.getRte().add(new RteType());
        assertEquals(GpxDescriptor.Route, GpxFileLoader.getType(gpx));
    }
    
    @Test
    public void testGetTrackTypeDescriptor() throws Exception {
        GpxType gpx = new GpxType();
        gpx.getTrk().add(new TrkType());
        assertEquals(GpxDescriptor.Track, GpxFileLoader.getType(gpx));
    }
    
    /**
     * TODO: Introduce support for waypoint based GPX files.
     * @throws Exception 
     */
    @Test
    public void testGetWaypointTypeDescriptor() throws Exception {
        GpxType gpx = new GpxType();
        gpx.getWpt().add(new WptType());
        try {
            GpxFileLoader.getType(gpx);
        } catch (FileNotValidException ex) {
            assertEquals(FileNotValidException.ERROR_MSG, ex.getMessage());
        }
    }
    
    @Test
    public void testGetEmptyTypeDescriptor() throws Exception {
        GpxType gpx = new GpxType();
        try {
            GpxFileLoader.getType(gpx);
        } catch (FileNotValidException ex) {
            assertEquals(FileNotValidException.ERROR_MSG, ex.getMessage());
        }
    }
    
}
