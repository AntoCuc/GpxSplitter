/**
 * Tests the file builder.
 *
 * @author Antonino Cucchiara
 */
package gpxsplitter.tools.builders;

import gpxsplitter.tools.*;
import org.jdom.Element;
import org.jdom.Document;
import gpxsplitter.model.Waypoint;
import java.util.List;
import java.util.ArrayList;
import org.junit.Before;
import gpxsplitter.model.Gpx;
import gpxsplitter.model.GpxType;
import gpxsplitter.model.Itinerary;
import org.jdom.Namespace;
import org.junit.Test;
import static org.junit.Assert.*;

public abstract class GpxFileBuilderTest
{

    static final double LAT_WPT_1 = 51.2404704333;
    static final double LON_WPT_1 = -0.1724553109;
    public static final String ELE_WPT_1 = "75";
    public static final double LAT_WPT_2 = 51.3;
    public static final double LON_WPT_2 = -0.1;
    public static final String ELE_WPT_2 = "76";
    
    GpxFileBuilder gpxFileBuilder;

    public abstract GpxFileBuilder getGpxFileBuilder(Gpx gpx);

    public abstract GpxType getGpxType();

    public abstract List<Element> getWaypoints(Document doc);

    @Before
    public void setUp()
    {

        List<Itinerary> itineraries = new ArrayList<Itinerary>();
        List<Waypoint> waypoints = new ArrayList<Waypoint>();
        Waypoint waypoint1 = new Waypoint(LAT_WPT_1, LON_WPT_1, ELE_WPT_1);
        waypoints.add(waypoint1);
        Waypoint waypoint2 = new Waypoint(LAT_WPT_2, LON_WPT_2, ELE_WPT_2);
        waypoints.add(waypoint2);
        Itinerary testItinerary1 = new Itinerary(waypoints);
        itineraries.add(testItinerary1);
        Itinerary testItinerary2 = new Itinerary(waypoints);
        itineraries.add(testItinerary2);
        Gpx expectedGpx = new Gpx(Namespace.getNamespace(TestMedia.NAMESPACE), getGpxType(), "1.1", "", itineraries);
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
    public void testBuildSplitGpxsWithUnevenNumberOfInstructionsComparedToThePreferred()
    {
        final int preferredNumOfWpts = 3;
        final int expectedNumOfWpts = 2;
        List<Document> actual = this.gpxFileBuilder.buildSplitGpx(preferredNumOfWpts);

        int expectedNumOfDocs = 1;
        int actualNumOfDocs = actual.size();
        assertEquals(expectedNumOfDocs, actualNumOfDocs);

        List<Element> actualWaypointsDoc1 = getWaypoints(actual.get(0));
        assertEquals(expectedNumOfWpts, actualWaypointsDoc1.size());

        Element wpt = actualWaypointsDoc1.get(0);
        assertEquals(String.valueOf(LAT_WPT_1), wpt.getAttribute(Gpx.LATITUDE_TAG).getValue());
        assertEquals(String.valueOf(LON_WPT_1), wpt.getAttribute(Gpx.LONGITUDE_TAG).getValue());
        assertEquals(ELE_WPT_1, wpt.getChild(Gpx.ELEMENT_TAG).getValue());

        Element wpt2 = actualWaypointsDoc1.get(1);
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

     @Test
    public void testHowManyFiles()
    {
        int expected = 2;
        int actual = GpxFileBuilder.howManyFiles(10, 5);
        assertEquals(expected, actual);
    }

    @Test
    public void testHowManyFilesNoInstructions()
    {
        int expected = 0;
        int actual = GpxFileBuilder.howManyFiles(0, 5);
        assertEquals(expected, actual);
    }

    @Test
    public void testHowManyFilesNoDesiredInstructions()
    {
        int expected = 0;
        int actual = GpxFileBuilder.howManyFiles(19, 0);
        assertEquals(expected, actual);
    }

    @Test
    public void testHowManyFilesOddFiles()
    {
        int expected = 4;
        int actual = GpxFileBuilder.howManyFiles(10, 3);
        assertEquals(expected, actual);
    }

    @Test
    public void testHowManyFilesOddInstructions()
    {
        int expected = 2;
        int actual = GpxFileBuilder.howManyFiles(19, 10);
        assertEquals(expected, actual);
    }
}
