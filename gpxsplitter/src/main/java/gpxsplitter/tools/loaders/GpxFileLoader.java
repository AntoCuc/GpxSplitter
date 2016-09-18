/**
 * Gpx loader builds a Gpx from a gpx markup file.
 *
 * @author Antonino Cucchiara
 */
package gpxsplitter.tools.loaders;

import gpxsplitter.model.Gpx;
import gpxsplitter.model.GpxType;
import java.io.InputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class GpxFileLoader implements GpxLoader {

    @Override
    public Gpx load(InputStream inputStream) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(GpxType.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        JAXBElement<GpxType> gpxRoot = (JAXBElement<GpxType>) jaxbUnmarshaller.unmarshal(inputStream);
        return new Gpx(gpxRoot.getValue());
    }
}
