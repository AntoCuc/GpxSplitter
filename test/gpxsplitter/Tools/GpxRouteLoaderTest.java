/**
 * Builds a Gpx from a gpx route markup file and tests its features.
 *
 * @author Antonino Cucchiara
 */
package gpxsplitter.Tools;

import gpxsplitter.Model.GpxType;

public class GpxRouteLoaderTest extends GpxLoaderTest
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
                + "<rte>"
                + "<name>Test route</name>"
                + "<desc>Test route description</desc>"
                + "<rtept lat = \"51.2404704333\" lon = \"-0.1724553109\">"
                + "<name>Point 1</name>"
                + "<ele>75</ele >"
                + "<time>2011 - 02 - 06 08:46:10></time>"
                + "</rtept>"
                + "<rtept lat = \"51.3\" lon = \"-0.1\">"
                + "<name>Point 2</name>"
                + "<ele>76</ele >"
                + "<time>2011 - 02 - 06 08:46:10></time>"
                + "</rtept>"
                + "</rte></gpx>";
    }

    @Override
    public GpxType getExpectedType()
    {
        return GpxType.Route;
    }
}
