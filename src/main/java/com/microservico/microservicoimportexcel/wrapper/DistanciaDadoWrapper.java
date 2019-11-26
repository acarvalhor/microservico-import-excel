package com.microservico.microservicoimportexcel.wrapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DistanciaDadoWrapper {

    private Double latInicial;
    private Double longInicial;
    private String cidadeLatLongInicial;
    private String ufLatLongInicial;
    private Double latFinal;
    private Double longFinal;
    private String cidadeLatLongFinal;
    private String ufLatLongFinal;
}
