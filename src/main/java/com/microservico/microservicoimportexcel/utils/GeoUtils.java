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
        GeoUtils geoCG = GeoUtils.builder().latitude(-20.4435).longititude(-54.6478).build();
        GeoUtils geoToquio = GeoUtils.builder().latitude(35.6894).longititude(139.692).build();
        System.out.println("Distância entre CG e Tóquio: " + geoCG.distanceInKm(geoToquio));
    }*/
}