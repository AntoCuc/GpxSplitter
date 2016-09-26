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
import gpxsplitter.model.generated.TrkType;
import gpxsplitter.model.generated.TrksegType;
import gpxsplitter.model.generated.WptType;
import java.util.ArrayList;
import java.util.List;

/**
 * The class {@code GpxTrackBuilder} class builds split GPX track files.
 *
 * <p>Treats each Track Segment as if it was a file on its own right. This
 * ensures that when splitting multi-segmented track files Gpx Splitter
 * maintains data consistency.
 * <p>An approach treating the entire Track as one GPX would, on some Garmin
 * devices using segments to separate day rides, "squash" the entire history
 * as if it was one big blob of Waypoints corrupting the data set.
 * <p>Such behavior may result in a number of split GPX files larger than
 * initially thought.
 *
 * @author Antonino Cucchiara
 * @see TrksegType
 * @see TrkType
 * @see WptType
 * @since GpxSplitter 0.1
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
