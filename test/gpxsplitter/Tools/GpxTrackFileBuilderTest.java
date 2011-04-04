/**
 * Tests gpx track file builder.
 *
 * @author Antonino Cucchiara
 */
package gpxsplitter.Tools;

import org.jdom.Document;
import gpxsplitter.Model.Gpx;
import gpxsplitter.Model.GpxType;
import java.util.List;
import org.jdom.Element;

public final class GpxTrackFileBuilderTest extends GpxFileBuilderTest
{

    @Override
    public GpxFileBuilder getGpxFileBuilder(Gpx gpx)
    {
        return new GpxTrackFileBuilder(gpx);
    }

    @Override
    public GpxType getGpxType()
    {
        return GpxType.Track;
    }

    @Override
    public List<Element> getWaypoints(Document doc)
    {
        return doc.getRootElement().getChild(Gpx.TRK_TAG).getChild(Gpx.TRACKSEGMENT_TAG).getChildren();
    }
}