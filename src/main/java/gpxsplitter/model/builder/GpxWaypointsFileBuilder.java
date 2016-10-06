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
import gpxsplitter.model.generated.WptType;
import java.util.ArrayList;
import java.util.List;

/**
 * Builds Gpx Waypoint-based files.
 *
 * @author Antonino Cucchiara
 * @since 0.4
 */
public final class GpxWaypointsFileBuilder extends GpxFileBuilder {

    @Override
    public List<GpxType> buildSplitGpx(
            final GpxType inputGpx, final int preferredInstrNum) {
        final List<GpxType> outGpxList = new ArrayList<>();
        final List<WptType> inWptList = inputGpx.getWpt();
        final int inWptNum = inWptList.size();
        final int outFilesNum = howManyFiles(inWptNum, preferredInstrNum);
        int currFile = 1;
        int currWptNum = 0;
        while (currFile <= outFilesNum) {
            final GpxType newGpx = createGpxTemplate();
            final List<WptType> outWptList = newGpx.getWpt();
            for (int i = 0; i < preferredInstrNum; i++) {
                outWptList.add(inWptList.get(currWptNum));
                currWptNum++;
            }
            outGpxList.add(newGpx);
            currFile++;
        }
        return outGpxList;
    }
}
