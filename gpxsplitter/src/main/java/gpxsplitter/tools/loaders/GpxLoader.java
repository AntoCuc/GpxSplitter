/**
 * Blue print for all gpx loaders.
 *
 * @author Antonino Cucchiara
 */

package gpxsplitter.tools.loaders;

import gpxsplitter.model.GpxType;
import java.io.InputStream;
import javax.xml.bind.JAXBException;

public interface GpxLoader {
    public GpxType load(InputStream inputStream) throws JAXBException;
}
