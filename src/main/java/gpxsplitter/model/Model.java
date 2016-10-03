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
    private GpxType sourceGpx;

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
        final GpxDescriptor gpxDescriptor = getDescriptor(sourceGpx);
        if (gpxDescriptor == GpxDescriptor.Track) {
            gpxBuilder = new GpxTrackFileBuilder();
        } else if (gpxDescriptor == GpxDescriptor.Route) {
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
        return gpxBuilder.buildSplitGpx(sourceGpx, desiredInstrNum);
    }

    /**
     * This method is to be overriden in Unit tests.
     *
     * @param outputStream to use for the new GPX files
     * @param newGpxDocument is the document to write to the file
     * @throws JAXBException if cannot write
     */
    public final void saveGpx(final OutputStream outputStream,
            final GpxType newGpxDocument)
            throws JAXBException {
        final JAXBContext jaxbContext = JAXBContext.newInstance(GpxType.class);
        final Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        final QName rootNode = new QName("ns2:gpx");
        final JAXBElement jaxbElement
                = new JAXBElement(rootNode, GpxType.class, newGpxDocument);
        jaxbMarshaller.marshal(jaxbElement, outputStream);
    }

    /**
     * Provides the source file.
     *
     * @return the source file name
     */
    public final String getSourceFilePath() {
        return stripExtension(sourceFilePath, GpxFileBuilder.GPX_EXTENSION);
    }

    /**
     * Provides the source bound GPX.
     *
     * @return the Gpx
     */
    public final GpxType getSourceGpx() {
        return sourceGpx;
    }

    /**
     * Evaluate the Gpx loaded and return its descriptor.
     *
     * @param gpxType to evaluate
     * @return the GpxDescriptor
     * @throws FileNotValidException in the event a file is not supported
     */
    public final GpxDescriptor getDescriptor(final GpxType gpxType)
            throws FileNotValidException {
        if (gpxType.getTrk().size() > 0) {
            return GpxDescriptor.Track;
        } else if (gpxType.getRte().size() > 0) {
            return GpxDescriptor.Route;
        } else if (gpxType.getWpt().size() > 0) {
            return GpxDescriptor.Waypoint;
        } else {
            throw new FileNotValidException();
        }
    }

    /**
     * Determines whether the string can be parsed as an integer.
     *
     * @param input the unsecured input
     * @return true if the input is an integer
     */
    public final boolean isValidInteger(final String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * This method cleans the filename of the extension if it is there.
     *
     * @param name of the file
     * @param extension to strip
     * @return the filename stripped of the extension
     */
    public final String stripExtension(final String name,
            final String extension) {
        if (name.endsWith(extension)) {
            return name.substring(0, (name.length() - extension.length()));
        } else {
            return name;
        }
    }
}
