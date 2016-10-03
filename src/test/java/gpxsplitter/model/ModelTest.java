package gpxsplitter.model;

import gpxsplitter.model.builder.GpxRouteFileBuilder;
import gpxsplitter.model.builder.GpxTrackFileBuilder;
import gpxsplitter.model.exception.FileNotValidException;
import gpxsplitter.model.generated.GpxType;
import gpxsplitter.model.generated.RteType;
import gpxsplitter.model.generated.TrkType;
import gpxsplitter.model.generated.WptType;
import java.io.ByteArrayOutputStream;
import org.junit.Test;
import static org.junit.Assert.*;

public class ModelTest {

    @Test
    public void testLoadGpxFile() throws Exception {
        Model gpxModel = new Model();
        gpxModel.loadGpxFile("src/test/resources/bikely-track-sample.gpx");
        GpxType gpx = gpxModel.getSourceGpx();
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
        assertEquals(GpxType.class, gpxModel.getSourceGpx().getClass());
        assertEquals("src/test/resources/topographix-waypoints-sample", gpxModel.getSourceFilePath());
    }

    @Test
    public void testSplitGpx() throws Exception {
        Model gpxModel = new Model();
        final String filePath = 
                "src/test/resources/topographix-route-sample.gpx";
        gpxModel.loadGpxFile(filePath);
        assertEquals(4, gpxModel.splitGpx(filePath, 1).size());
    }

    @Test
    public void testSaveGpx() throws Exception {
        GpxType gpx = new GpxType();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Model gpxModel = new Model();
        gpxModel.saveGpx(outputStream, gpx);
        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\""
                + " standalone=\"yes\"?>\n"
                + "<ns2:gpx xmlns:ns2=\"http://www.topografix.com/GPX/1/1\"/>\n";
        String actual = new String(outputStream.toByteArray());
        assertEquals(expected, actual);
    }

    @Test
    public void testGetRouteTypeDescriptor() throws Exception {
        GpxType gpx = new GpxType();
        gpx.getRte().add(new RteType());
        assertEquals(GpxDescriptor.Route, new Model().getDescriptor(gpx));
    }

    @Test
    public void testGetTrackTypeDescriptor() throws Exception {
        GpxType gpx = new GpxType();
        gpx.getTrk().add(new TrkType());
        assertEquals(GpxDescriptor.Track, new Model().getDescriptor(gpx));
    }

    @Test
    public void testGetWaypointTypeDescriptor() throws Exception {
        GpxType gpx = new GpxType();
        gpx.getWpt().add(new WptType());
        assertEquals(GpxDescriptor.Waypoint, new Model().getDescriptor(gpx));
    }

    @Test
    public void testGetEmptyTypeDescriptor() throws Exception {
        GpxType gpx = new GpxType();
        try {
            new Model().getDescriptor(gpx);
        } catch (FileNotValidException ex) {
            assertEquals(FileNotValidException.ERROR_MSG, ex.getMessage());
        }
    }

    @Test
    public void testIsValidInteger() {
        Model model = new Model();
        assertTrue(model.isValidInteger("1"));
    }

    @Test
    public void testIsValidIntegerFalse() {
        Model model = new Model();
        assertFalse(model.isValidInteger("a"));
    }

    @Test
    public void testStripGpxExtension() {
        String fileName = "abc.gpx";
        String extension = ".gpx";
        Model instance = new Model();
        String expResult = "abc";
        String result = instance.stripExtension(fileName, extension);
        assertEquals(expResult, result);
    }

    @Test
    public void testStripExtensionNoExtension() {
        String fileName = "abc";
        String extension = ".gpx";
        Model instance = new Model();
        String expResult = "abc";
        String result = instance.stripExtension(fileName, extension);
        assertEquals(expResult, result);
    }

    @Test
    public void testStripExtensionDifferentExtension() {
        String fileName = "abc.txt";
        String extension = ".gpx";
        Model instance = new Model();
        String expResult = "abc.txt";
        String result = instance.stripExtension(fileName, extension);
        assertEquals(expResult, result);
    }
}
