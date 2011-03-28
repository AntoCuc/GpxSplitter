/**
 * Class that represents way points in a  gpx.
 *
 * @author Antonino Cucchiara
 */

package gpxsplitter.Model;

public class Waypoint {

    private final double latitude;
    private final double longitude;
    private final String element;

    public Waypoint(double latitude, double longitude, String element)
    {
        this.latitude = latitude;
        this.longitude = longitude;
        this.element = element;
    }

    public String getLatitude()
    {
        return String.valueOf(latitude);
    }

    public String getLongitude()
    {
        return String.valueOf(longitude);
    }

    public String getElement()
    {
        return element;
    }

    @Override
    public String toString()
    {
        return "Waypoint{" + "latitude=" + latitude + "longitude=" + longitude + "element=" + element + '}';
    }

}
