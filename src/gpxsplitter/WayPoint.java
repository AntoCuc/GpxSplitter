/**
 * Class that represents way points in a  gpx.
 *
 * @author Antonino Cucchiara
 */

package gpxsplitter;

class Waypoint {

    private final double latitude;
    private final double longitude;
    private final String element;

    Waypoint(double latitude, double longitude, String element)
    {
        this.latitude = latitude;
        this.longitude = longitude;
        this.element = element;
    }

    String getLatitude()
    {
        return String.valueOf(latitude);
    }

    String getLongitude()
    {
        return String.valueOf(longitude);
    }

    String getElement()
    {
        return element;
    }

}
