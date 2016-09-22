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
import gpxsplitter.model.loader.GpxFileLoader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.xml.bind.JAXBException;

/**
 * Encapsulates GpxSplitter business logic.
 * @author Antonino Cucchiara
 */
public class Model {

    /**
     * The file used to source GPX content.
     */
    private File sourceFile;
    /**
     * The bound GPX content.
     */
    private Gpx sourceGpx;

    /**
     * Load and Cache a GPX file.
     * @param gpxFile to load
     * @throws JAXBException if unable to load
     * @throws FileNotFoundException if the file path is not valid
     * @throws FileNotValidException if not a GPX file
     * @throws IOException if no rights to read the file
     */
    public final void loadGpxFile(final File gpxFile)
            throws JAXBException, FileNotFoundException,
            FileNotValidException, IOException {
        this.sourceFile = gpxFile;
        final GpxFileLoader gpxLoader = new GpxFileLoader();
        this.sourceGpx = gpxLoader.load(new FileInputStream(gpxFile));
    }

    /**
     * Assign a GPX builder based on the GPX descriptor.
     * @return the appropriate file builder
     * @throws FileNotValidException if unable to parse
     */
    final GpxFileBuilder getGpxBuilder() throws FileNotValidException {
        GpxFileBuilder gpxBuilder;

        switch (sourceGpx.getDescriptor()) {
            case Track:
                gpxBuilder = new GpxTrackFileBuilder();
                break;
            case Route:
                gpxBuilder = new GpxRouteFileBuilder();
                break;
            default:
                throw new FileNotValidException();
        }
        return gpxBuilder;
    }

    /**
     * Save the GPX resource.
     * @param filePath to save to
     * @param desiredInstrNum per file
     * @throws FileNotValidException if unable to un-bind
     * @throws JAXBException if faulty file format is detected
     */
    public final void saveGpx(final String filePath, final int desiredInstrNum)
            throws FileNotValidException, JAXBException {
        GpxFileBuilder gpxBuilder = getGpxBuilder();
        gpxBuilder.build(filePath,
                getSourceGpx().getUnderlying(), desiredInstrNum);
    }

    /**
     * Provides the source file.
     * @return the source file
     */
    public final File getSourceFile() {
        return sourceFile;
    }

    /**
     * Provides the source bound GPX.
     * @return the Gpx
     */
    public final Gpx getSourceGpx() {
        return sourceGpx;
    }

}
