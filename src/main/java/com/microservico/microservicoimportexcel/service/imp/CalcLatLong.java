package com.microservico.microservicoimportexcel.service.imp;

import org.springframework.stereotype.Service;

import static java.lang.Math.atan2;
import static java.lang.StrictMath.sqrt;

@Service
public class CalcLatLong {


    public Long calculaDIstancia(Double latInicial, Double longInicial, Double latFinal, Double longFinal ) {

        Double d2r = 0.017453292519943295769236;

        Double dlong = (longFinal - longInicial) * d2r;
        Double dlat = (latFinal - latInicial) * d2r;

        Double temp_sin = Math.sin(dlat / 2.0);
        Double temp_cos = Math.cos(latInicial * d2r);
        Double temp_sin2 = Math.sin(dlong / 2.0);

        Double a = (temp_sin * temp_sin) + (temp_cos * temp_cos) * (temp_sin2 * temp_sin2);
        Double c = 2.0 * atan2(sqrt(a), sqrt(1.0 - a));
        System.out.println(6368.1 * c);
        return (long) (6368.1 * c);

    }

}
