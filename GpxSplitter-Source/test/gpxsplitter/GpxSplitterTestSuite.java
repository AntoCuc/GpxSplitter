/**
 * This suite contains all the tests for the GpxSplitter.
 *
 * @author Antonino Cucchiara
 */
package gpxsplitter;

import gpxsplitter.model.WaypointTest;
import gpxsplitter.tools.loaders.GpxMultiTrackFileLoaderTest;
import gpxsplitter.tools.builders.GpxRouteFileBuilderTest;
import gpxsplitter.tools.loaders.GpxRouteFileLoaderTest;
import gpxsplitter.tools.builders.GpxTrackFileBuilderTest;
import gpxsplitter.tools.loaders.GpxTrackFileLoaderTest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(
{
    WaypointTest.class,
    GpxRouteFileLoaderTest.class,
    GpxTrackFileLoaderTest.class,
    GpxMultiTrackFileLoaderTest.class,
    ControllerTest.class,
    GpxTrackFileBuilderTest.class,
    GpxRouteFileBuilderTest.class
})
public class GpxSplitterTestSuite
{

    @BeforeClass
    public static void setUpClass() throws Exception
    {
    }

    @AfterClass
    public static void tearDownClass() throws Exception
    {
    }

    @Before
    public void setUp() throws Exception
    {
    }

    @After
    public void tearDown() throws Exception
    {
    }
}
