package com.microservico.microservicoimportexcel.repository;

import com.microservico.microservicoimportexcel.wrapper.CidadeEstadoWrapper;
import com.microservico.microservicoimportexcel.wrapper.CityName;
import com.microservico.microservicoimportexcel.wrapper.DadosWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExcelRepository extends JpaRepository<DadosWrapper, Integer> {

    @Query("SELECT d FROM DadosWrapper d WHERE d.capital = 'TRUE' ORDER BY d.name")
    List<DadosWrapper> findCapitaisOrderByName();

    @Query("SELECT COUNT(d) FROM DadosWrapper d WHERE d.uf = :uf")
    Integer numberOfCityByState(String uf);

    @Query("SELECT new DadosWrapper(COUNT(d), d.uf) FROM DadosWrapper d group by d.uf")
    List<DadosWrapper> numberOfCityByState();

    @Query("SELECT d FROM DadosWrapper d where d.ibge_id = :id")
    List<DadosWrapper> dataCityByIdIBGE(Double id);

    @Query("SELECT new com.microservico.microservicoimportexcel.wrapper.CityName(d.name) FROM DadosWrapper d where d.uf = :uf")
    List<CityName> findCityByUf(String uf);
}
