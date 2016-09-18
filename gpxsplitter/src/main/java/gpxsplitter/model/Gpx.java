package gpxsplitter.model;

import gpxsplitter.GpxDescriptor;
import gpxsplitter.tools.FileNotValidException;

public class Gpx {

    private final GpxType gpxType;

    public Gpx(GpxType gpxType) {
        this.gpxType = gpxType;
    }

    public String getVersion() {
        return this.gpxType.getVersion();
    }

    public GpxType getUnderlying() {
        return this.gpxType;
    }

    public GpxDescriptor getDescriptor() throws FileNotValidException {
        if (gpxType.getTrk().size() > 0) {
            return GpxDescriptor.Track;
        } else if (gpxType.getRte().size() > 0) {
            return GpxDescriptor.Route;
        } else {
            throw new FileNotValidException();
        }
    }

    public boolean isMultitrack() {
        return getTracksNum() > 1;
    }

    public int getTracksNum() {
        return gpxType.getTrk().size();
    }
}
