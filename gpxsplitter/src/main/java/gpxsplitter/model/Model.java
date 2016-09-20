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
 *
 * @author antonio
 */
public class Model {

    private File sourceFile;
    private Gpx sourceGpx;

    public void loadGpxFile(File gpxFile) throws JAXBException, FileNotFoundException, FileNotValidException, IOException {
        this.sourceFile = gpxFile;
        final GpxFileLoader gpxLoader = new GpxFileLoader();
        this.sourceGpx = gpxLoader.load(new FileInputStream(gpxFile));
    }

    GpxFileBuilder getGpxBuilder() throws FileNotValidException {
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

    public void saveGpx(File file, int desiredInstrNum) throws FileNotValidException, JAXBException {
        GpxFileBuilder gpxBuilder = getGpxBuilder();
        gpxBuilder.build(file, getSourceGpx().getUnderlying(), desiredInstrNum);
    }

    public File getSourceFile() {
        return sourceFile;
    }

    public Gpx getSourceGpx() {
        return sourceGpx;
    }

}
