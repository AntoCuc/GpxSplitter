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

import gpxsplitter.model.GpxType;
import gpxsplitter.model.RteType;
import gpxsplitter.model.WptType;
import java.util.ArrayList;
import java.util.List;

/**
 * Builds Gpx Route files.
 * @author Antonino Cucchiara
 */
public final class GpxRouteFileBuilder extends GpxFileBuilder {

    @Override
    public List<GpxType> buildSplitGpx(
            final GpxType inputGpx, final int preferredInstrNum) {
        List<GpxType> outputGpxList = new ArrayList<>();
        List<RteType> inputRoutes = inputGpx.getRte();
        inputRoutes.stream().forEach((inputRoute) -> {
            int waypointsNum = inputRoute.getRtept().size();
            int outputFilesNum = howManyFiles(waypointsNum, preferredInstrNum);

            int currentWaypoint = 0;

            int currentFile = 1;

            while (currentFile <= outputFilesNum) {
                final GpxType newGpx = createGpxTemplate();
                final List<RteType> newRouteList = newGpx.getRte();

                int waypointsInRoute = inputRoute.getRtept().size();
                if (waypointsInRoute <= preferredInstrNum) {
                    newRouteList.add(inputRoute);
                } else {
                    final RteType newRoute = new RteType();
                    for (int i = 0; i < preferredInstrNum; i++) {
                        final List<WptType> waypoint = inputRoute.getRtept();
                        newRoute.getRtept().add(waypoint.get(currentWaypoint));
                        currentWaypoint++;
                    }
                    newRouteList.add(newRoute);
                }
                outputGpxList.add(newGpx);
                currentFile++; // Proceed to next file
            }
        });
        return outputGpxList;
    }
}
