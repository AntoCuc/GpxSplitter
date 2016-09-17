/**
 * Builds gpx routes from a gpx.
 *
 * @author Antonino Cucchiara
 */
package gpxsplitter.tools.builders;

import gpxsplitter.model.GpxType;
import gpxsplitter.model.RteType;
import java.util.ArrayList;
import java.util.List;

public final class GpxRouteFileBuilder extends GpxFileBuilder {

    @Override
    public List<GpxType> buildSplitGpx(GpxType inputGpx, int preferredInstrNum) {
        List<GpxType> outputGpxList = new ArrayList<>();
        List<RteType> inputRoutes = inputGpx.getRte();
        inputRoutes.stream().forEach((inputRoute) -> {
            int outputFilesNum = howManyFiles(inputRoute.getRtept().size(), preferredInstrNum);

            int currentWaypoint = 0;

            int currentFile = 1;

            while (currentFile <= outputFilesNum) {
                GpxType newGpx = createGpxTemplate();
                List<RteType> newRouteList = newGpx.getRte();

                int waypointsInRoute = inputRoute.getRtept().size();
                if (waypointsInRoute <= preferredInstrNum) {
                    newRouteList.add(inputRoute);
                } else {
                    RteType newRoute = new RteType();
                    for (int i = 0; i < preferredInstrNum; i++) {
                        newRoute.getRtept().add(inputRoute.getRtept().get(currentWaypoint));
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
