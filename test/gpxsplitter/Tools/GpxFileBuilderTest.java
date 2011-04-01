/**
 * Tests the file builder.
 *
 * @author Antonino Cucchiara
 */
package gpxsplitter.Tools;

import org.jdom.Element;
import org.jdom.Document;
import gpxsplitter.Model.Waypoint;
import java.util.List;
import java.util.ArrayList;
import org.junit.Before;
import gpxsplitter.Model.Gpx;
import gpxsplitter.Model.GpxType;
import org.jdom.Namespace;
import org.junit.Test;
import static org.junit.Assert.*;

public abstract class GpxFileBuilderTest
{

    private static final double LAT_WPT_1 = 51.2404704333;
    private static final double LON_WPT_1 = -0.1724553109;
    public static final String ELE_WPT_1 = "75";
    public static final double LAT_WPT_2 = 51.3;
    public static final double LON_WPT_2 = -0.1;
    public static final String ELE_WPT_2 = "76";
    
    private GpxFileBuilder gpxFileBuilder;

    public abstract GpxFileBuilder getGpxFileBuilder(Gpx gpx);

    public abstract GpxType getGpxType();

    public abstract List<Element> getWaypoints(Document doc);

    @Before
    public void setUp()
    {

        List<Waypoint> waypoints = new ArrayList<Waypoint>();
        Waypoint waypoint1 = new Waypoint(LAT_WPT_1, LON_WPT_1, ELE_WPT_1);
        waypoints.add(waypoint1);
        Waypoint waypoint2 = new Waypoint(LAT_WPT_2, LON_WPT_2, ELE_WPT_2);
        waypoints.add(waypoint2);
        Gpx expectedGpx = new Gpx(Namespace.getNamespace(TestMedia.NAMESPACE), getGpxType(), "1.1", "", waypoints);
        this.gpxFileBuilder = getGpxFileBuilder(expectedGpx);
    }

    @Test
    public void testBuildSplitGpxs()
    {
        final int expectedNumOfWpts = 1;
        List<Document> actual = this.gpxFileBuilder.buildSplitGpx(expectedNumOfWpts);

        int expectedNumOfDocs = 2;
        int actualNumOfDocs = actual.size();
        assertEquals(expectedNumOfDocs, actualNumOfDocs);

        List<Element> actualWaypointsDoc1 = getWaypoints(actual.get(0));
        assertEquals(expectedNumOfWpts, actualWaypointsDoc1.size());

        Element wpt = actualWaypointsDoc1.get(0);
        assertEquals(String.valueOf(LAT_WPT_1), wpt.getAttribute(Gpx.LATITUDE_TAG).getValue());
        assertEquals(String.valueOf(LON_WPT_1), wpt.getAttribute(Gpx.LONGITUDE_TAG).getValue());
        assertEquals(ELE_WPT_1, wpt.getChild(Gpx.ELEMENT_TAG).getValue());

        List<Element> actualWaypointsDoc2 = getWaypoints(actual.get(1));
        assertEquals(expectedNumOfWpts, actualWaypointsDoc2.size());

        Element wpt2 = actualWaypointsDoc2.get(0);
        assertEquals(String.valueOf(LAT_WPT_2), wpt2.getAttribute(Gpx.LATITUDE_TAG).getValue());
        assertEquals(String.valueOf(LON_WPT_2), wpt2.getAttribute(Gpx.LONGITUDE_TAG).getValue());
        assertEquals(ELE_WPT_2, wpt2.getChild(Gpx.ELEMENT_TAG).getValue());


    }

    @Test
    public void testStripExtensionWithExtension() throws Exception
    {
        String expected = "testFileName";
        String fileNameWithExtension = expected + GpxFileBuilder.GPX_FORMAT;
        String actual = this.gpxFileBuilder.stripExtension(fileNameWithExtension, GpxFileBuilder.GPX_FORMAT);
        assertEquals(expected, actual);
    }

    @Test
    public void testStripExtensionWithNoExtension() throws Exception
    {
        String expected = "testFileName";
        String actual = this.gpxFileBuilder.stripExtension(expected, GpxFileBuilder.GPX_FORMAT);
        assertEquals(expected, actual);
    }

    @Test
    public void testStripExtensionWithDifferentExtension() throws Exception
    {
        String expected = "testFileName.xml";
        String actual = this.gpxFileBuilder.stripExtension(expected, GpxFileBuilder.GPX_FORMAT);
        assertEquals(expected, actual);
    }
}
