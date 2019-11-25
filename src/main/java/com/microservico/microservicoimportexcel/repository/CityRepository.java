package com.microservico.microservicoimportexcel.repository;

import java.util.List;
import com.microservico.microservicoimportexcel.model.City;
import com.microservico.microservicoimportexcel.model.wrapper.CityWrapper;
import com.microservico.microservicoimportexcel.model.wrapper.StateWrapper;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends PagingAndSortingRepository<City, Integer> {
    
    City findByIdIbge(String idIbge);
    
    List<City> findAllByCapital(String capital, Sort sort);

    @Query(value = "SELECT new com.microservico.microservicoimportexcel.model.wrapper.CityWrapper(name) "
        + " FROM City WHERE uf = :uf")
    List<CityWrapper> findAllByUf(@Param("uf") String state);

    @Query(value = "SELECT new com.microservico.microservicoimportexcel.model.wrapper.StateWrapper(uf, COUNT(uf))" 
        + " FROM City GROUP BY uf")
	List<StateWrapper> findStatesWithNumberOfCities();
}