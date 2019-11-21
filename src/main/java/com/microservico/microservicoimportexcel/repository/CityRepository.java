package com.microservico.microservicoimportexcel.repository;

import java.util.List;
import com.microservico.microservicoimportexcel.model.City;
import com.microservico.microservicoimportexcel.model.wrapper.CityWrapper;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends PagingAndSortingRepository<City, Integer> {
    
    List<City> findAllByCapital(String capital, Sort sort);

    @Query(nativeQuery = true, value = "SELECT NEW CityWrapper(uf AS state, COUNT(uf) AS number) FROM City GROUP BY uf")
	List<CityWrapper> findStatesWithTheLargestAndSmallestNumberOfCities();
}