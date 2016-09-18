/**
 * Blue print for all gpx loaders.
 *
 * @author Antonino Cucchiara
 */

package gpxsplitter.tools.loaders;

import gpxsplitter.model.Gpx;
import java.io.InputStream;
import javax.xml.bind.JAXBException;

public interface GpxLoader {
    public Gpx load(InputStream inputStream) throws JAXBException;
}
