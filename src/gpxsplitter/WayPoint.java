/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gpxsplitter;

/**
 *
 * @author Antonio
 */
public class WayPoint
    {

        private final double latitude;
        private final double longitude;
        private final String element;

        public WayPoint(double latitude, double longitude, String element)
        {
            this.latitude = latitude;
            this.longitude = longitude;
            this.element = element;
        }

        public String getElement()
        {
            return element;
        }

        public double getLatitude()
        {
            return latitude;
        }

        public double getLongitude()
        {
            return longitude;
        }
    }