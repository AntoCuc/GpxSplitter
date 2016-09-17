/**
 * Blue print for all gpx builders.
 *
 * @author Antonino Cucchiara
 */

package gpxsplitter.tools.builders;

import gpxsplitter.model.GpxType;
import java.io.File;
import javax.xml.bind.JAXBException;

interface GpxBuilder {
    public void build(File file, GpxType gpx, int preferedInstrNum) throws JAXBException;
}
