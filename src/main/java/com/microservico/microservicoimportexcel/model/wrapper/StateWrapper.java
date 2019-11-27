package com.microservico.microservicoimportexcel.model.wrapper;

import java.io.Serializable;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StateWrapper implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String state;
    private Long numberOfCities;
}