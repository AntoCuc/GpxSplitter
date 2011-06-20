/**
 * Container for test goodies.
 *
 * @author Antonino Cucchiara
 */

package gpxsplitter.tools;

public class TestMedia {
    
    public static final String NAMESPACE = "http://www.topografix.com/GPX/1/1";

    public static String getTestTrack()
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

    public static String getTestGpxWithMultipleTracks()
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
                + "</trkseg>"
                + "</trk>"
                + "<trk>"
                + "<name>Test track</name>"
                + "<desc>Test track description</desc>"
                + "<trkseg>"
                + "<trkpt lat = \"51.2404704234\" lon = \"-0.1724553123\">"
                + "<ele>75</ele >"
                + "<time>2011 - 02 - 06 08:46:10></time>"
                + "</trkpt>"
                + "<trkpt lat = \"51.321\" lon = \"-0.145\">"
                + "<ele>76</ele >"
                + "<time>2011 - 02 - 06 08:46:10></time>"
                + "</trkpt>"
                + "</trkseg>"
                + "</trk></gpx>";
    }

    public static String getTestRoute()
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

}
