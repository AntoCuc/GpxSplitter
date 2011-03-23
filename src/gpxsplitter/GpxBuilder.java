package gpxsplitter;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author anc6
 */
interface GpxBuilder {
    public void build(File fileName, int totalInstructionsNumber, int filesNum) throws IOException;
}
