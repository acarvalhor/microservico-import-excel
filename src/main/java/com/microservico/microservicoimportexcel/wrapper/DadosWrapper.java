package com.microservico.microservicoimportexcel.wrapper;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "DadosCidade")
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DadosWrapper implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double ibge_id;
    private String uf;
    private String name;
    private String capital;
    private String lon;
    private String lat;
    private String no_accents;
    private String alternative_names;
    private String microregion;
    private String mesoregion;

    @Transient
    private Integer qtdCidades;

    public DadosWrapper(long qtdCidades, String uf){
        this.uf = uf;
        this.qtdCidades = (int) qtdCidades;
    }

}
