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
package gpxsplitter.tools.builders;

import gpxsplitter.model.GpxType;
import gpxsplitter.view.View;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

public abstract class GpxFileBuilder {

    public static final String GPX_EXTENSION = ".gpx";
    static final String GPX_VERSION = "1.1";

    /**
     * This method will build a set of GPX files given the file name, number of
     * GPX instructions per file and the number of files to be built.
     *
     * @param file
     * @param gpx
     * @param preferedInstrNum
     * @throws javax.xml.bind.JAXBException
     */
    public void build(File file, GpxType gpx, int preferedInstrNum) throws JAXBException {
        int fileNum = 1;
        List<GpxType> newGpxList = buildSplitGpx(gpx, preferedInstrNum);
        for (GpxType newGpx : newGpxList) {
            saveFile(new File(stripExtension(file.getAbsolutePath(), GPX_EXTENSION) + "-" + fileNum + GPX_EXTENSION), newGpx);
            fileNum++;
        }
    }

    /**
     * The method build split gpx uses the gpx document loaded and the preferred
     * number of instructions to build a list of Gpx files ready to be written
     * to file.
     *
     * @param gpx
     * @param preferredInstrNum
     * @return a list of gpx files
     */
    public abstract List<GpxType> buildSplitGpx(GpxType gpx, int preferredInstrNum);

    /**
     * The method create gpx template creates the start elements and heading of
     * a gpx document.
     *
     * @return a gpx document.
     */
    protected GpxType createGpxTemplate() {
        GpxType newGpx = new GpxType();
        newGpx.setVersion(GPX_VERSION);
        newGpx.setCreator(View.GPX_SPLITTER);
        return newGpx;
    }

    /**
     * This method is to be overriden in Unit tests.
     *
     * @param file
     * @param gpx document to write to the file
     * @throws IOException
     */
    void saveFile(File file, GpxType newGpxDocument) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(GpxType.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        JAXBElement jaxbElement = new JAXBElement(new QName("ROOT"), GpxType.class, newGpxDocument);
        jaxbMarshaller.marshal(jaxbElement, file);
    }

    /**
     * This method cleans the filename of the extension if it is there.
     *
     * @param file
     * @return the filename stripped of the extension
     */
    String stripExtension(String fileName, String extension) {
        if (fileName.endsWith(extension)) {
            return fileName.substring(0, (fileName.length() - extension.length()));
        } else {
            return fileName;
        }
    }

    /**
     * This method calculates how many files have to be created when splitting
     * the GPX to the wanted number of instructions.
     *
     * @param currNumOfInstr
     * @param desiredNumOfInstr
     * @return
     */
    int howManyFiles(int currNumOfInstr, int desiredNumOfInstr) {
        try {
            int numOfFiles = (currNumOfInstr / desiredNumOfInstr);
            /**
             * *
             * If there are any points left out one more file is going to be
             * created.
             */
            if ((currNumOfInstr % desiredNumOfInstr) > 0) {
                numOfFiles++;
            }
            return numOfFiles;
        } catch (ArithmeticException e) { //No instructions or invalid instructions output number
            return 0;
        }
    }
}
