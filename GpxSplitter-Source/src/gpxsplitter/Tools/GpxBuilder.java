/**
 * Blue print for all gpx builders.
 *
 * @author Antonino Cucchiara
 */

package gpxsplitter.tools;

import java.io.File;
import java.io.IOException;

interface GpxBuilder {
    public void build(File fileName, int preferedInstrNum) throws IOException;
}
