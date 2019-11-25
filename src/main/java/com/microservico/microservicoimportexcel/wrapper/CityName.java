package com.microservico.microservicoimportexcel.wrapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
public class CityName implements Serializable {

    private String cityName;

    public CityName(String cityName){
        this.cityName = cityName;
    }

}
