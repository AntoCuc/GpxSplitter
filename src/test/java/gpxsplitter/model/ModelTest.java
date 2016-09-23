package gpxsplitter.model;

import gpxsplitter.model.builder.GpxRouteFileBuilder;
import gpxsplitter.model.builder.GpxTrackFileBuilder;
import gpxsplitter.model.exception.FileNotValidException;
import org.junit.Test;
import static org.junit.Assert.*;

public class ModelTest {

    @Test
    public void testLoadGpxFile() throws Exception {
        Model gpxModel = new Model();
        gpxModel.loadGpxFile("src/test/resources/bikely-track-sample.gpx");
        Gpx gpx = gpxModel.getSourceGpx();
        assertNotNull(gpx);
    }

    @Test
    public void testGetGpxBuilderForTrack() throws Exception {
        Model gpxModel = new Model();
        gpxModel.loadGpxFile("src/test/resources/bikely-track-sample.gpx");
        assertEquals(GpxTrackFileBuilder.class, gpxModel.getGpxBuilder().getClass());
    }

    @Test
    public void testGetGpxBuilderForRoute() throws Exception {
        Model gpxModel = new Model();
        gpxModel.loadGpxFile("src/test/resources/topographix-route-sample.gpx");
        assertEquals(GpxRouteFileBuilder.class, gpxModel.getGpxBuilder().getClass());
    }

    @Test
    public void testGetGpxBuilderForWaypoint() throws Exception {
        Model gpxModel = new Model();
        gpxModel.loadGpxFile("src/test/resources/topographix-waypoints-sample.gpx");
        try {
            gpxModel.getGpxBuilder();
        } catch (FileNotValidException ex) {
            //Pass
        }
    }

    @Test
    public void testGetters() throws Exception {
        Model gpxModel = new Model();
        final String sourceFilePath = "src/test/resources/topographix-waypoints-sample.gpx";
        gpxModel.loadGpxFile(sourceFilePath);
        assertEquals(Gpx.class, gpxModel.getSourceGpx().getClass());
        assertEquals(sourceFilePath, gpxModel.getSourceFilePath());
    }
}
