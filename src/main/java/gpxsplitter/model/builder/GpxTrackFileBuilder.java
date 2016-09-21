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
import gpxsplitter.model.TrkType;
import gpxsplitter.model.TrksegType;
import gpxsplitter.model.WptType;
import java.util.ArrayList;
import java.util.List;

/**
 * Builds GPX track files.
 * @author Antonino Cucchiara
 */
public final class GpxTrackFileBuilder extends GpxFileBuilder {

    @Override
    public List<GpxType> buildSplitGpx(
            final GpxType gpx, final int preferredInstrNum) {
        List<GpxType> gpxList = new ArrayList<>();
        List<TrkType> tracks = gpx.getTrk();
        tracks.stream().forEach((track) -> {
            track.getTrkseg().forEach((trackSeg) -> {
                final int trackSegNum = trackSeg.getTrkpt().size();
                int splitFiles = howManyFiles(trackSegNum, preferredInstrNum);

                int currentWaypoint = 0;
                int fileNum = 1;
                while (fileNum <= splitFiles) {

                    GpxType newGpx = createGpxTemplate();
                    TrkType newTrack = new TrkType();
                    newTrack.setName(track.getName() + fileNum);
                    TrksegType newTrackSegment = new TrksegType();
                    newTrack.getTrkseg().add(newTrackSegment);

                    List<TrkType> newTrackList = newGpx.getTrk();

                    int waypointsInTrackSegment = trackSeg.getTrkpt().size();
                    if (waypointsInTrackSegment < preferredInstrNum) {
                        newTrackList.add(track);
                    } else {
                        for (int i = 0; i < preferredInstrNum; i++) {
                            final List<WptType> waypoint = trackSeg.getTrkpt();
                            WptType oldWaypoint = waypoint.get(currentWaypoint);
                            newTrackSegment.getTrkpt().add(oldWaypoint);
                            currentWaypoint++;
                        }
                        newTrackList.add(newTrack);
                    }
                    gpxList.add(newGpx);
                    fileNum++;
                }
            });
        });
        return gpxList;
    }
}
