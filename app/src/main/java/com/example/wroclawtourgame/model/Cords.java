package com.example.wroclawtourgame.model;

import java.io.Serializable;

public class Cords implements Serializable {

    private double longitude;
    private double latitude;

    public Cords(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Cords(String sCords) {
        try {
            String[] splitted = sCords.split(", ");
            latitude = Double.parseDouble(splitted[0]);
            longitude = Double.parseDouble(splitted[1]);
        } catch (Exception e) {
            throw new IllegalArgumentException("Incorrect cords format");
        }
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * from https://stackoverflow.com/questions/3694380/calculating-distance-between-two-points-using-latitude-longitude
     * Calculate distance between two points in latitude and longitude taking
     * into account height difference. If you are not interested in height
     * difference pass 0.0. Uses Haversine method as its base.
     * <p>
     * lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters
     * el2 End altitude in meters
     *
     * @returns Distance in Meters
     */
    public double getDistanceFrom(Cords another) {
        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(this.latitude - another.latitude);
        double lonDistance = Math.toRadians(this.longitude - another.longitude);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(another.latitude)) * Math.cos(Math.toRadians(this.latitude))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        distance = Math.pow(distance, 2);

        return Math.sqrt(distance);
    }
}
