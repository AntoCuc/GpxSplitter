package gpxsplitter.tools.builders;

import gpxsplitter.model.GpxType;
import gpxsplitter.model.RteType;
import gpxsplitter.model.WptType;
import java.math.BigDecimal;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class GpxRouteFileBuilderTest {
    
    private static final BigDecimal LONG1 = BigDecimal.valueOf(-0.1724553109);
    private static final BigDecimal LAT1 = BigDecimal.valueOf(51.2404704333);
    private static final BigDecimal LONG2 = BigDecimal.valueOf(-0.1);
    private static final BigDecimal LAT2 = BigDecimal.valueOf(51.3);
    
    private final GpxRouteFileBuilder fileBuilder;
    
    public GpxRouteFileBuilderTest() {
        fileBuilder = new GpxRouteFileBuilder();
    }
    
    @Test
    public void testBuildSplitGpxNoRouteNoFile() {
        GpxType gpx = new GpxType();
        List<GpxType> gpxFiles = fileBuilder.buildSplitGpx(gpx, 1);
        assertTrue(gpxFiles.isEmpty());
    }

    @Test
    public void testBuildSplitGpxOneRouteOneInstructionOneFile() {
        GpxType gpx = new GpxType();
        WptType waypoint1 = new WptType();
        waypoint1.setLat(LAT1);
        waypoint1.setLon(LONG1);
        RteType route = new RteType();
        route.getRtept().add(waypoint1);
        gpx.getRte().add(route);
        List<GpxType> gpxFiles = fileBuilder.buildSplitGpx(gpx, 1);
        assertTrue(gpxFiles.size() == 1);
        assertTrue(gpxFiles.get(0).getRte().size() == 1);
        assertTrue(gpxFiles.get(0).getRte().get(0).getRtept().size() == 1);
        assertTrue(gpxFiles.get(0).getRte().get(0).getRtept().get(0).getLat() == LAT1);
        assertTrue(gpxFiles.get(0).getRte().get(0).getRtept().get(0).getLon()== LONG1);
    }
    
    @Test
    public void testBuildSplitGpxOneRouteTwoInstructionsOneFile() {
        GpxType gpx = new GpxType();
        WptType waypoint1 = new WptType();
        waypoint1.setLat(LAT1);
        waypoint1.setLon(LONG1);
        RteType route = new RteType();
        route.getRtept().add(waypoint1);
        WptType waypoint2 = new WptType();
        waypoint2.setLat(LAT2);
        waypoint2.setLon(LONG2);
        route.getRtept().add(waypoint2);
        gpx.getRte().add(route);
        List<GpxType> gpxFiles = fileBuilder.buildSplitGpx(gpx, 2);
        assertTrue(gpxFiles.size() == 1);
        assertTrue(gpxFiles.get(0).getRte().size() == 1);
        assertTrue(gpxFiles.get(0).getRte().get(0).getRtept().size() == 2);
        assertTrue(gpxFiles.get(0).getRte().get(0).getRtept().get(0).getLat() == LAT1);
        assertTrue(gpxFiles.get(0).getRte().get(0).getRtept().get(0).getLon()== LONG1);
        assertTrue(gpxFiles.get(0).getRte().get(0).getRtept().get(1).getLat() == LAT2);
        assertTrue(gpxFiles.get(0).getRte().get(0).getRtept().get(1).getLon()== LONG2);
    }
    
    @Test
    public void testBuildSplitGpxOneRouteTwoInstructionsTwoFiles() {
        GpxType gpx = new GpxType();
        WptType waypoint1 = new WptType();
        waypoint1.setLat(LAT1);
        waypoint1.setLon(LONG1);
        RteType route = new RteType();
        route.getRtept().add(waypoint1);
        WptType waypoint2 = new WptType();
        waypoint2.setLat(LAT2);
        waypoint2.setLon(LONG2);
        route.getRtept().add(waypoint2);
        gpx.getRte().add(route);
        List<GpxType> gpxFiles = fileBuilder.buildSplitGpx(gpx, 1);
        assertTrue(gpxFiles.size() == 2);
        assertTrue(gpxFiles.get(0).getRte().size() == 1);
        assertTrue(gpxFiles.get(0).getRte().get(0).getRtept().size() == 1);
        assertTrue(gpxFiles.get(0).getRte().get(0).getRtept().get(0).getLat() == LAT1);
        assertTrue(gpxFiles.get(0).getRte().get(0).getRtept().get(0).getLon()== LONG1);
        assertTrue(gpxFiles.get(1).getRte().get(0).getRtept().size() == 1);
        assertTrue(gpxFiles.get(1).getRte().get(0).getRtept().get(0).getLat() == LAT2);
        assertTrue(gpxFiles.get(1).getRte().get(0).getRtept().get(0).getLon()== LONG2);
    }
    
}
