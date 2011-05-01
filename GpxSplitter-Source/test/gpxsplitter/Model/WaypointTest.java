/**
 * Tests the way point behaviour.
 *
 * @author Antonino Cucchiara
 */
package gpxsplitter.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class WaypointTest
{
    
    @Test
    public void testEqualsWithEqualWaypoints()
    {
        Waypoint waypoint1 = new Waypoint(1, 2, "test");
        Waypoint waypoint2 = new Waypoint(1, 2, "test");
        assertTrue(waypoint1.equals(waypoint2));
    }

    @Test
    public void testEqualsWithDifferentLat()
    {
        Waypoint waypoint1 = new Waypoint(1, 2, "test");
        Waypoint waypoint2 = new Waypoint(2, 2, "test");
        assertFalse(waypoint1.equals(waypoint2));
    }

    @Test
    public void testEqualsWithDifferentLong()
    {
        Waypoint waypoint1 = new Waypoint(1, 2, "test");
        Waypoint waypoint2 = new Waypoint(1, 3, "test");
        assertFalse(waypoint1.equals(waypoint2));
    }

    @Test
    public void testEqualsWithDifferentElement()
    {
        Waypoint waypoint1 = new Waypoint(1, 2, "test");
        Waypoint waypoint2 = new Waypoint(1, 2, "test different");
        assertFalse(waypoint1.equals(waypoint2));
    }

    @Test
    public void testEqualsWithDifferentObj()
    {
        Waypoint waypoint1 = new Waypoint(1, 2, "test");
        assertFalse(waypoint1.equals(new Object()));
    }

    @Test
    public void testToString()
    {
        Waypoint waypoint = new Waypoint(1, 2, "test");
        String expected = "Waypoint{latitude=1.0 longitude=2.0 element=test}";
        assertEquals(expected, waypoint.toString());
    }
}
