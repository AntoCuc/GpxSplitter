/**
 * Tests gpx track file builder.
 *
 * @author Antonino Cucchiara
 */
package gpxsplitter.tools.builders;

import org.jdom.Document;
import gpxsplitter.model.Gpx;
import gpxsplitter.model.GpxType;
import java.util.List;
import org.jdom.Element;
import org.junit.Test;
import static org.junit.Assert.*;

public final class SingleTrackFileBuilderTest extends GpxFileBuilderTest
{

    @Override
    public GpxFileBuilder getGpxFileBuilder(Gpx gpx)
    {
        return new SingleTrackGpxFileBuilder(gpx);
    }

    @Override
    public GpxType getGpxType()
    {
        return GpxType.Track;
    }

    @Override
    public List<Element> getWaypoints(Document doc)
    {
        return doc.getRootElement().getChild(Gpx.TRK_TAG).getChild(Gpx.TRACKSEGMENT_TAG).getChildren();
    }

    @Test
    @Override
    public void testBuildSplitGpxs()
    {
        final int expectedNumOfWpts = 2;
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

        Element wpt1 = actualWaypointsDoc1.get(1);
        assertEquals(String.valueOf(LAT_WPT_2), wpt1.getAttribute(Gpx.LATITUDE_TAG).getValue());
        assertEquals(String.valueOf(LON_WPT_2), wpt1.getAttribute(Gpx.LONGITUDE_TAG).getValue());
        assertEquals(ELE_WPT_2, wpt1.getChild(Gpx.ELEMENT_TAG).getValue());

        List<Element> actualWaypointsDoc2 = getWaypoints(actual.get(1));
        assertEquals(expectedNumOfWpts, actualWaypointsDoc2.size());

        Element wpt2 = actualWaypointsDoc2.get(0);
        assertEquals(String.valueOf(LAT_WPT_1), wpt2.getAttribute(Gpx.LATITUDE_TAG).getValue());
        assertEquals(String.valueOf(LON_WPT_1), wpt2.getAttribute(Gpx.LONGITUDE_TAG).getValue());
        assertEquals(ELE_WPT_1, wpt2.getChild(Gpx.ELEMENT_TAG).getValue());

        Element wpt3 = actualWaypointsDoc2.get(1);
        assertEquals(String.valueOf(LAT_WPT_2), wpt3.getAttribute(Gpx.LATITUDE_TAG).getValue());
        assertEquals(String.valueOf(LON_WPT_2), wpt3.getAttribute(Gpx.LONGITUDE_TAG).getValue());
        assertEquals(ELE_WPT_2, wpt3.getChild(Gpx.ELEMENT_TAG).getValue());
    }

    @Test
    @Override
    public void testBuildSplitGpxsWithUnevenNumberOfInstructionsComparedToThePreferred()
    {
        final int preferredNumOfWpts = 3;
        final int expectedNumOfWpts = 2;
        List<Document> actual = this.gpxFileBuilder.buildSplitGpx(preferredNumOfWpts);

        int expectedNumOfDocs = 2;
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

        List<Element> actualWaypointsDoc2 = getWaypoints(actual.get(1));
        assertEquals(expectedNumOfWpts, actualWaypointsDoc2.size());

        Element wpt3 = actualWaypointsDoc2.get(0);
        assertEquals(String.valueOf(LAT_WPT_1), wpt3.getAttribute(Gpx.LATITUDE_TAG).getValue());
        assertEquals(String.valueOf(LON_WPT_1), wpt3.getAttribute(Gpx.LONGITUDE_TAG).getValue());
        assertEquals(ELE_WPT_1, wpt3.getChild(Gpx.ELEMENT_TAG).getValue());

        Element wpt4 = actualWaypointsDoc2.get(1);
        assertEquals(String.valueOf(LAT_WPT_2), wpt4.getAttribute(Gpx.LATITUDE_TAG).getValue());
        assertEquals(String.valueOf(LON_WPT_2), wpt4.getAttribute(Gpx.LONGITUDE_TAG).getValue());
        assertEquals(ELE_WPT_2, wpt4.getChild(Gpx.ELEMENT_TAG).getValue());
    }
}
