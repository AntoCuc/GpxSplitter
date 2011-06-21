/**
 * Abstraction of a track or route.
 *
 * @author Antonino Cucchiara
 */

package gpxsplitter.model;

import java.util.List;

public class Itinerary {

    private final List<Waypoint> waypoints;

    public Itinerary(List<Waypoint> waypoints)
    {
        this.waypoints = waypoints;
    }

    public List<Waypoint> getWaypoints()
    {
        return waypoints;
    }

}
