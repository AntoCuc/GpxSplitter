/**
 * Tests gpx route file builder.
 *
 * @author Antonino Cucchiara
 */
package gpxsplitter.tools;

import gpxsplitter.model.Gpx;
import gpxsplitter.model.GpxType;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;

public final class GpxRouteFileBuilderTest extends GpxFileBuilderTest
{

    @Override
    public GpxFileBuilder getGpxFileBuilder(Gpx gpx)
    {
        return new GpxRouteFileBuilder(gpx);
    }

    @Override
    public GpxType getGpxType()
    {
        return GpxType.Route;
    }

    @Override
    public List<Element> getWaypoints(Document doc)
    {
        return doc.getRootElement().getChild(Gpx.RTE_TAG).getChildren();
    }
}
