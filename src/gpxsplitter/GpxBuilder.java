package gpxsplitter;

import java.io.IOException;
import org.apache.xmlbeans.XmlException;

/**
 *
 * @author anc6
 */
interface GpxBuilder {
    public void build(String fileName, int totalInstructionsNumber, int filesNum) throws XmlException, IOException;
}
