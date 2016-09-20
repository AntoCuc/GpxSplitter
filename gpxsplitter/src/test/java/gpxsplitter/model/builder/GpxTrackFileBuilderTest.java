/*
 * The MIT License
 *
 * Copyright 2016 Antonino Cucchiara.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package gpxsplitter.model.builder;

import gpxsplitter.model.builder.GpxTrackFileBuilder;
import gpxsplitter.model.GpxType;
import gpxsplitter.model.TrkType;
import gpxsplitter.model.TrksegType;
import gpxsplitter.model.WptType;
import java.math.BigDecimal;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class GpxTrackFileBuilderTest {

    private static final BigDecimal LONG1 = BigDecimal.valueOf(-0.1724553109);
    private static final BigDecimal LAT1 = BigDecimal.valueOf(51.2404704333);
    private static final BigDecimal LONG2 = BigDecimal.valueOf(-0.1);
    private static final BigDecimal LAT2 = BigDecimal.valueOf(51.3);

    private final GpxTrackFileBuilder fileBuilder;

    public GpxTrackFileBuilderTest() {
        fileBuilder = new GpxTrackFileBuilder();
    }

    @Test
    public void testBuildSplitGpxNoTrackNoFile() {
        GpxType gpx = new GpxType();
        List<GpxType> gpxFiles = fileBuilder.buildSplitGpx(gpx, 1);
        assertTrue(gpxFiles.isEmpty());
    }

    @Test
    public void testBuildSplitGpxOneRouteOneInstructionOneFile() {
        GpxType gpx = new GpxType();
        TrksegType trackSegment = new TrksegType();
        WptType waypoint1 = new WptType();
        waypoint1.setLat(LAT1);
        waypoint1.setLon(LONG1);
        trackSegment.getTrkpt().add(waypoint1);
        TrkType track = new TrkType();
        track.getTrkseg().add(trackSegment);
        gpx.getTrk().add(track);
        List<GpxType> gpxFiles = fileBuilder.buildSplitGpx(gpx, 1);
        assertTrue(gpxFiles.size() == 1);
        assertTrue(gpxFiles.get(0).getTrk().size() == 1);
        assertTrue(gpxFiles.get(0).getTrk().get(0).getTrkseg().size() == 1);
        assertTrue(gpxFiles.get(0).getTrk().get(0).getTrkseg().get(0).getTrkpt().get(0).getLat() == LAT1);
        assertTrue(gpxFiles.get(0).getTrk().get(0).getTrkseg().get(0).getTrkpt().get(0).getLon() == LONG1);
    }

    @Test
    public void testBuildSplitGpxOneRouteTwoInstructionsOneFile() {
        GpxType gpx = new GpxType();
        TrksegType trackSegment = new TrksegType();
        WptType waypoint1 = new WptType();
        waypoint1.setLat(LAT1);
        waypoint1.setLon(LONG1);
        trackSegment.getTrkpt().add(waypoint1);
        WptType waypoint2 = new WptType();
        waypoint2.setLat(LAT2);
        waypoint2.setLon(LONG2);
        trackSegment.getTrkpt().add(waypoint2);
        TrkType track = new TrkType();
        track.getTrkseg().add(trackSegment);
        gpx.getTrk().add(track);
        List<GpxType> gpxFiles = fileBuilder.buildSplitGpx(gpx, 2);
        assertTrue(gpxFiles.size() == 1);
        assertTrue(gpxFiles.get(0).getTrk().size() == 1);
        assertTrue(gpxFiles.get(0).getTrk().get(0).getTrkseg().size() == 1);
        assertTrue(gpxFiles.get(0).getTrk().get(0).getTrkseg().get(0).getTrkpt().size() == 2);
        assertTrue(gpxFiles.get(0).getTrk().get(0).getTrkseg().get(0).getTrkpt().get(0).getLat() == LAT1);
        assertTrue(gpxFiles.get(0).getTrk().get(0).getTrkseg().get(0).getTrkpt().get(0).getLon() == LONG1);
        assertTrue(gpxFiles.get(0).getTrk().get(0).getTrkseg().size() == 1);
        assertTrue(gpxFiles.get(0).getTrk().get(0).getTrkseg().get(0).getTrkpt().get(1).getLat() == LAT2);
        assertTrue(gpxFiles.get(0).getTrk().get(0).getTrkseg().get(0).getTrkpt().get(1).getLon() == LONG2);
    }

    @Test
    public void testBuildSplitGpxOneRouteTwoInstructionsTwoFiles() {
        GpxType gpx = new GpxType();
        TrksegType trackSegment = new TrksegType();
        WptType waypoint1 = new WptType();
        waypoint1.setLat(LAT1);
        waypoint1.setLon(LONG1);
        trackSegment.getTrkpt().add(waypoint1);
        WptType waypoint2 = new WptType();
        waypoint2.setLat(LAT2);
        waypoint2.setLon(LONG2);
        trackSegment.getTrkpt().add(waypoint2);
        TrkType track = new TrkType();
        final String testGpxName = "test-gpx";
        track.setName(testGpxName);
        track.getTrkseg().add(trackSegment);
        gpx.getTrk().add(track);
        List<GpxType> gpxFiles = fileBuilder.buildSplitGpx(gpx, 1);
        assertTrue(gpxFiles.size() == 2);
        assertTrue(gpxFiles.get(0).getTrk().size() == 1);
        assertTrue(gpxFiles.get(0).getTrk().get(0).getTrkseg().size() == 1);
        assertTrue(gpxFiles.get(0).getTrk().get(0).getTrkseg().get(0).getTrkpt().size() == 1);
        assertEquals(testGpxName + 1, gpxFiles.get(0).getTrk().get(0).getName());
        assertTrue(gpxFiles.get(0).getTrk().get(0).getTrkseg().get(0).getTrkpt().get(0).getLat() == LAT1);
        assertTrue(gpxFiles.get(0).getTrk().get(0).getTrkseg().get(0).getTrkpt().get(0).getLon() == LONG1);
        assertTrue(gpxFiles.get(1).getTrk().get(0).getTrkseg().size() == 1);
        assertTrue(gpxFiles.get(1).getTrk().get(0).getTrkseg().get(0).getTrkpt().get(0).getLat() == LAT2);
        assertTrue(gpxFiles.get(1).getTrk().get(0).getTrkseg().get(0).getTrkpt().get(0).getLon() == LONG2);
        assertEquals(testGpxName + 2, gpxFiles.get(1).getTrk().get(0).getName());
    }

}
