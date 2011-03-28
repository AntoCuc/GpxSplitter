/**
 * Blue print for all gpx builders.
 *
 * @author Antonino Cucchiara
 */

package gpxsplitter;

import java.io.File;
import java.io.IOException;

interface GpxBuilder {
    public void build(File fileName, int totalInstructionsNumber, int filesNum) throws IOException;
}
