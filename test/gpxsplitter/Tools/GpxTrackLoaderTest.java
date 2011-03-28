/**
 * Builds a Gpx from a gpx track markup file and tests its features.
 *
 * @author Antonino Cucchiara
 */
package gpxsplitter.Tools;

import gpxsplitter.Model.GpxType;

/**
 *
 * @author Antonio
 */
public final class GpxTrackLoaderTest extends GpxLoaderTest
{

    @Override
    public String getData()
    {
        return "<?xml version=\"1.0\"?>"
                + "<gpx version=\"1.1\" "
                + "creator=\"Bikely - http://www.bikely.com\" "
                + "xmlns=\"" + NAMESPACE + "\" "
                + "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "
                + "xsi:schemaLocation=\"http://www.topografix.com/GPX/1/1 http://www.topografix.com/GPX/1/1/gpx.xsd\"> "
                + "<trk>"
                + "<name>Test track</name>"
                + "<desc>Test track description</desc>"
                + "<trkseg>"
                + "<trkpt lat = \"51.2404704333\" lon = \"-0.1724553109\">"
                + "<ele>75</ele >"
                + "<time>2011 - 02 - 06 08:46:10></time>"
                + "</trkpt>"
                + "<trkpt lat = \"51.3\" lon = \"-0.1\">"
                + "<ele>76</ele >"
                + "<time>2011 - 02 - 06 08:46:10></time>"
                + "</trkpt>"
                + "</trkseg></trk></gpx>";
    }

    @Override
    public GpxType getExpectedType()
    {
        return GpxType.Track;
    }
}
