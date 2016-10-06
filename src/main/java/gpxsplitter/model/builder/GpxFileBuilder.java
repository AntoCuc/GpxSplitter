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
package gpxsplitter.model.builder;

import gpxsplitter.model.generated.GpxType;
import gpxsplitter.view.View;
import java.util.List;

/**
 * Builds Gpx files given a binding object.
 *
 * @author Antonino Cucchiara
 * @since 0.1
 */
public abstract class GpxFileBuilder {

    /**
     * The GPX file extension.
     */
    public static final String GPX_EXTENSION = ".gpx";

    /**
     * The supported GPX file version.
     */
    static final String GPX_VERSION = "1.1";

    /**
     * The method build split gpx uses the gpx document loaded and the preferred
     * number of instructions to build a list of Gpx files ready to be written
     * to file.
     *
     * @param gpx is the bound file
     * @param preferredInstrNum is the number of instructions per file
     * @return a list of gpx files
     */
    public abstract List<GpxType> buildSplitGpx(
            GpxType gpx, int preferredInstrNum);

    /**
     * The method create gpx template creates the start elements and heading of
     * a gpx document.
     *
     * @return a gpx document.
     */
    protected final GpxType createGpxTemplate() {
        GpxType newGpx = new GpxType();
        newGpx.setVersion(GPX_VERSION);
        newGpx.setCreator(View.GPX_SPLITTER);
        return newGpx;
    }

    /**
     * This method calculates how many files have to be created when splitting
     * the GPX to the wished number of instructions.
     *
     * @param currNumOfInstr in each GPX file
     * @param desiredNumOfInstr each file should contain
     * @return how many files should be generated
     */
    final int howManyFiles(
            final int currNumOfInstr, final int desiredNumOfInstr) {
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
        } catch (ArithmeticException e) {
            return 0;
        }
    }
}
