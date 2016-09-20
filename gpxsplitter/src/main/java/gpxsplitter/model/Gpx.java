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

/**
 * Wrapper class abstracting the GPX file.
 *
 * @author Antonino Cucchiara
 */
public class Gpx {

    /**
     * The Java Binding Gpx Object.
     */
    private final GpxType gpxType;

    /**
     * Initialise the Gpx with the binding type.
     *
     * @param newGpxType the underlying object.
     */
    public Gpx(final GpxType newGpxType) {
        this.gpxType = newGpxType;
    }

    /**
     * Delegate the retrieval of the version to the binding object.
     *
     * @return the Gpx loaded version
     */
    public final String getVersion() {
        return this.gpxType.getVersion();
    }

    /**
     * Retrieve the underlying binding object.
     *
     * @return the binding object
     */
    public final GpxType getUnderlying() {
        return this.gpxType;
    }

    /**
     * Evaluate the Gpx loaded and return its descriptor.
     *
     * @return the GpxDescriptor
     * @throws FileNotValidException in the event a file is not supported
     */
    public final GpxDescriptor getDescriptor() throws FileNotValidException {
        if (gpxType.getTrk().size() > 0) {
            return GpxDescriptor.Track;
        } else if (gpxType.getRte().size() > 0) {
            return GpxDescriptor.Route;
        } else {
            throw new FileNotValidException();
        }
    }

    /**
     * Evaluate whether the Gpx is multi track-segment.
     *
     * @return true if multi-segment
     */
    public final boolean isMultitrack() {
        return getTracksNum() > 1;
    }

    /**
     * Retrieve the number of track segments in the GPX.
     *
     * @return the number of segments
     */
    public final int getTracksNum() {
        return gpxType.getTrk().size();
    }
}
