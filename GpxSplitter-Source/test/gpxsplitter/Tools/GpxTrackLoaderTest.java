/**
 * Builds a Gpx from a gpx track markup file and tests its features.
 *
 * @author Antonino Cucchiara
 */
package gpxsplitter.tools;

import gpxsplitter.Tools.TestMedia;
import gpxsplitter.model.GpxType;

/**
 *
 * @author Antonio
 */
public final class GpxTrackLoaderTest extends GpxLoaderTest
{

    @Override
    public String getData()
    {
        return TestMedia.getTestTrack();
    }

    @Override
    public GpxType getExpectedType()
    {
        return GpxType.Track;
    }
}
