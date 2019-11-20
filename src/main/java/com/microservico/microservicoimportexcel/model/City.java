package com.microservico.microservicoimportexcel.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class City implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "id_ibge")
    private String idIbge;
    private String uf;
    private String name;
    private String capital;
    @Column(name = "longitude")
    private String lon;
    @Column(name = "latitude")
    private String lat;
    @Column(name = "no_acents")
    private String noAcents;
    @Column(name = "alternative_names")
    private String alternativeNames;
    private String microregion;
    private String mesoregion;
}