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
package gpxsplitter.model;

import gpxsplitter.model.exception.FileNotValidException;
import gpxsplitter.model.generated.GpxType;
import gpxsplitter.model.generated.RteType;
import gpxsplitter.model.generated.TrkType;
import gpxsplitter.model.generated.WptType;
import org.junit.Test;
import static org.junit.Assert.*;

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

    @Test
    public void testGetVersion() {
        GpxType gpx = new GpxType();
        String actual = new Gpx(gpx).getVersion();
        assertEquals("1.1", actual);
    }

    @Test
    public void testGetUnderlying() {
        GpxType expected = new GpxType();
        GpxType actual = new Gpx(expected).getUnderlying();
        assertSame(expected, actual);
    }

    @Test
    public void testIsMultitrackNegative() {
        GpxType gpx = new GpxType();
        gpx.getTrk().add(new TrkType());
        assertFalse(new Gpx(gpx).isMultitrack());
    }

    @Test
    public void testIsMultitrackPositive() {
        GpxType gpx = new GpxType();
        gpx.getTrk().add(new TrkType());
        gpx.getTrk().add(new TrkType());
        assertTrue(new Gpx(gpx).isMultitrack());
    }

    @Test
    public void testGetTracksNum() {
        GpxType gpx = new GpxType();
        gpx.getTrk().add(new TrkType());
        gpx.getTrk().add(new TrkType());
        assertEquals(2, new Gpx(gpx).getTracksNum());
    }
}
