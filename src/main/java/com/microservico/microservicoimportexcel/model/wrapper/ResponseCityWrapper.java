package com.microservico.microservicoimportexcel.model.wrapper;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCityWrapper implements Serializable {

    private static final long serialVersionUID = 1L;

    private Double distance;
    @JsonProperty(value = "cities")
    private List<CityWrapper> citiesWrapper;
}