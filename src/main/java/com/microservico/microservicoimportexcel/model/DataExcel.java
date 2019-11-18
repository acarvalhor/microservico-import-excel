package com.microservico.microservicoimportexcel.model;

import java.io.Serializable;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DataExcel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String idIbge;
    private String uf;
    private String name;
    private String capital;
    private String lon;
    private String lat;
    private String noAcents;
    private String alternativeNames;
    private String microregion;
    private String mesoregion;
}