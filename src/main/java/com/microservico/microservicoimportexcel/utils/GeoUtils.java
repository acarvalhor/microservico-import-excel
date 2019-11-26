package com.microservico.microservicoimportexcel.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeoUtils {

    private double latitude;
    private double longititude;
    private static final double EARTH_RADIUS_KM = 6.371;

    public double distanceInKm(GeoUtils geo) {
        double firstLatToRad = Math.toRadians(getLatitude());
        double secondLatToRad = Math.toRadians(geo.getLatitude());
        double deltaLongitudeInRad = Math.toRadians(geo.getLongititude() - getLongititude());

        return Math.acos(Math.cos(firstLatToRad) * Math.cos(secondLatToRad)
                * Math.cos(deltaLongitudeInRad) + Math.sin(firstLatToRad)
                * Math.sin(secondLatToRad)) * EARTH_RADIUS_KM;
    }

    /*public static void main(String[] args) {
        GeoUtils geo1 = GeoUtils.builder().latitude(-7.5932225939).longititude(-72.9165010261).build();
        GeoUtils geo2 = GeoUtils.builder().latitude(-3.8520214008).longititude(-32.4351863281).build();
        System.out.println("Distância entre " + geo1 + " e " + geo2 + ": " + geo1.distanceInKm(geo2));
    }*/
}