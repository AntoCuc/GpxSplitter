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
        return TestMedia.getTestRoute();
    }

    @Override
    public GpxType getExpectedType()
    {
        return GpxType.Route;
    }
}
