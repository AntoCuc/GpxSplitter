/**
 * Tests the Gpx abstraction behaviour.
 *
 * @author Antonino Cucchiara
 */

package gpxsplitter.model;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Antonio
 */
public class GpxTest {

    private Gpx testGpx;

    public GpxTest() {



    }

    @BeforeClass
    public static void setUpClass() throws Exception
    {
    }

    @AfterClass
    public static void tearDownClass() throws Exception
    {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getNumOfInstructions method, of class Gpx.
     */
    @Test
    public void testGetNumOfInstructionsWithSingleItineraryAndSingleWaypoint()
    {
        List<Itinerary> itineraries = new ArrayList<Itinerary>();
        List<Waypoint> waypoints = new ArrayList<Waypoint>();
        waypoints.add(new Waypoint(0.1, 0.1, null));
        Itinerary itinerary = new Itinerary(waypoints);
        itineraries.add(itinerary);
        Gpx instance = new Gpx(null, null, null, null, itineraries);
        int expResult = 1;
        int result = instance.getNumOfInstructions();
        assertEquals(expResult, result);
    }

   @Test
    public void testGetNumOfInstructionsWithTwoItinerariesAndSingleWaypoint()
    {
        List<Itinerary> itineraries = new ArrayList<Itinerary>();
        List<Waypoint> waypoints = new ArrayList<Waypoint>();
        waypoints.add(new Waypoint(0.1, 0.1, null));
        Itinerary itinerary = new Itinerary(waypoints);
        itineraries.add(itinerary);
        itineraries.add(itinerary);
        Gpx instance = new Gpx(null, null, null, null, itineraries);
        int expResult = 2;
        int result = instance.getNumOfInstructions();
        assertEquals(expResult, result);
    }

}