/*
 * The MIT License
 *
 * Copyright 2016 Antonino Cucchiara.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package gpxsplitter.model;

import gpxsplitter.model.exception.FileNotValidException;
import gpxsplitter.model.builder.GpxFileBuilder;
import gpxsplitter.model.builder.GpxRouteFileBuilder;
import gpxsplitter.model.builder.GpxTrackFileBuilder;
import gpxsplitter.model.generated.GpxType;
import gpxsplitter.model.loader.GpxFileLoader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

/**
 * Encapsulates GpxSplitter business logic.
 *
 * @author Antonino Cucchiara
 */
public class Model {

    /**
     * File path used to source GPX content.
     */
    private String sourceFilePath;
    /**
     * The bound GPX content.
     */
    private Gpx sourceGpx;

    /**
     * Load and Cache a GPX file.
     *
     * @param gpxFileName to load
     * @throws JAXBException if unable to load
     * @throws FileNotFoundException if the file path is not valid
     * @throws FileNotValidException if not a GPX file
     * @throws IOException if no rights to read the file
     */
    public final void loadGpxFile(final String gpxFileName)
            throws JAXBException, FileNotFoundException,
            FileNotValidException, IOException {
        this.sourceFilePath = gpxFileName;
        final GpxFileLoader gpxLoader = new GpxFileLoader();
        this.sourceGpx = gpxLoader.load(new FileInputStream(gpxFileName));
    }

    /**
     * Assign a GPX builder based on the GPX descriptor.
     *
     * @return the appropriate file builder
     * @throws FileNotValidException if unable to parse
     */
    final GpxFileBuilder getGpxBuilder() throws FileNotValidException {
        GpxFileBuilder gpxBuilder = null;
        if (sourceGpx.getDescriptor() == GpxDescriptor.Track) {
            gpxBuilder = new GpxTrackFileBuilder();
        } else if (sourceGpx.getDescriptor() == GpxDescriptor.Route) {
            gpxBuilder = new GpxRouteFileBuilder();
        }
        return gpxBuilder;
    }

    /**
     * Save the GPX resource.
     *
     * @param filePath to save to
     * @param desiredInstrNum per file
     * @return the list of bound GPX
     * @throws FileNotValidException if unable to un-bind
     * @throws JAXBException if faulty file format is detected
     */
    public final List<GpxType> splitGpx(final String filePath,
            final int desiredInstrNum)
            throws FileNotValidException, JAXBException {
        final GpxFileBuilder gpxBuilder = getGpxBuilder();
        return gpxBuilder.build(filePath,
                getSourceGpx().getUnderlying(), desiredInstrNum);
    }

    /**
     * This method is to be overriden in Unit tests.
     *
     * @param outputStream to use for the new GPX files
     * @param newGpxDocument is the document to write to the file
     * @throws JAXBException if cannot write
     */
    public void saveGpx(final OutputStream outputStream,
            final GpxType newGpxDocument)
            throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(GpxType.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        QName rootNode = new QName("ns2:gpx");
        JAXBElement jaxbElement
                = new JAXBElement(rootNode, GpxType.class, newGpxDocument);
        jaxbMarshaller.marshal(jaxbElement, outputStream);
    }

    /**
     * Provides the source file.
     *
     * @return the source file name
     */
    public final String getSourceFilePath() {
        return sourceFilePath;
    }

    /**
     * Provides the source bound GPX.
     *
     * @return the Gpx
     */
    public final Gpx getSourceGpx() {
        return sourceGpx;
    }

}
