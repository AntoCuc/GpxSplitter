/**
 * Blue print for all gpx loaders.
 *
 * @author Antonino Cucchiara
 */

package gpxsplitter.tools.loaders;

import gpxsplitter.model.Gpx;
import gpxsplitter.tools.FileNotValidException;
import java.io.IOException;
import org.jdom.JDOMException;

public interface GpxLoader {
    public Gpx load()  throws FileNotValidException, IOException, JDOMException;
}
