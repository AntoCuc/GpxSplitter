/**
 * Builds gpx tracks from a gpx.
 *
 * @author Antonino Cucchiara
 */
package gpxsplitter.tools.builders;

import gpxsplitter.model.GpxType;
import gpxsplitter.model.TrkType;
import gpxsplitter.model.TrksegType;
import gpxsplitter.model.WptType;
import java.util.ArrayList;
import java.util.List;

public final class GpxTrackFileBuilder extends GpxFileBuilder {

    @Override
    public List<GpxType> buildSplitGpx(GpxType gpx, int preferredInstrNum) {
        List<GpxType> gpxList = new ArrayList<>();
        List<TrkType> tracks = gpx.getTrk();
        tracks.stream().forEach((track) -> {
            track.getTrkseg().forEach((trackSegment) -> {
                int splitFiles = howManyFiles(trackSegment.getTrkpt().size(), preferredInstrNum);

                int currentWaypoint = 0;
                int fileNum = 1;
                while (fileNum <= splitFiles) {

                    GpxType newGpx = createGpxTemplate();
                    TrkType newTrack = new TrkType();
                    newTrack.setName(track.getName() + fileNum);
                    TrksegType newTrackSegment = new TrksegType();
                    newTrack.getTrkseg().add(newTrackSegment);

                    List<TrkType> newTrackList = newGpx.getTrk();

                    int waypointsInTrackSegment = trackSegment.getTrkpt().size();
                    if (waypointsInTrackSegment < preferredInstrNum) {
                        newTrackList.add(track);
                    } else {
                        for (int i = 0; i < preferredInstrNum; i++) {
                            WptType oldWaypoint = trackSegment.getTrkpt().get(currentWaypoint);
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