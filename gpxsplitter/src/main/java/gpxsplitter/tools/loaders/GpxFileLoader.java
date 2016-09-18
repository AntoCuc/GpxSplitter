/**
 * Gpx loader builds a Gpx from a gpx markup file.
 *
 * @author Antonino Cucchiara
 */
package gpxsplitter.tools.loaders;

import gpxsplitter.GpxDescriptor;
import gpxsplitter.tools.*;
import gpxsplitter.model.GpxType;
import java.io.InputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class GpxFileLoader implements GpxLoader {

    public static GpxDescriptor getType(GpxType gpxType) throws FileNotValidException {
        if (gpxType == null) {
            throw new FileNotValidException();
        } else if (gpxType.getTrk().size() > 0) {
            return GpxDescriptor.Track;
        } else if (gpxType.getRte().size() > 0) {
            return GpxDescriptor.Route;
        } else {
            throw new FileNotValidException();
        }
    }

    public static boolean isGpxMultitrack(GpxType gpxType) {
        return gpxType.getTrk().size() > 1;
    }

    public static int getTracksNum(GpxType gpxType) {
        return gpxType.getTrk().size();
    }

    @Override
    public GpxType load(InputStream inputStream) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(GpxType.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        JAXBElement<GpxType> gpxRoot = (JAXBElement<GpxType>) jaxbUnmarshaller.unmarshal(inputStream);
        return gpxRoot.getValue();
    }
}
