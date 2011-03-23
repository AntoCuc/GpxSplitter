/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gpxsplitter;

import java.math.BigDecimal;

/**
 *
 * @author Antonio
 */
public class WayPoint
    {

        private final BigDecimal latitude;
        private final BigDecimal longitude;
        private final BigDecimal element;

        public WayPoint(BigDecimal latitude, BigDecimal longitude, BigDecimal element)
        {
            this.latitude = latitude;
            this.longitude = longitude;
            this.element = element;
        }

        public BigDecimal getElement()
        {
            return element;
        }

        public BigDecimal getLatitude()
        {
            return latitude;
        }

        public BigDecimal getLongitude()
        {
            return longitude;
        }
    }