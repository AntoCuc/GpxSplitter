/**
 * Tests gpx route file builder.
 *
 * @author Antonino Cucchiara
 */
package gpxsplitter.Tools;

import gpxsplitter.Model.Gpx;
import gpxsplitter.Model.GpxType;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.junit.Test;
import static org.junit.Assert.*;

public final class GpxRouteFileBuilderTest extends GpxFileBuilderTest
{

    @Test
    public void testBuild() throws Exception
    {
    }

    @Test
    public void testBuildGpx()
    {
    }

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
