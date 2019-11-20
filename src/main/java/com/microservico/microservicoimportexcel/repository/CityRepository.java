package com.microservico.microservicoimportexcel.repository;

import java.util.List;
import com.microservico.microservicoimportexcel.model.City;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends PagingAndSortingRepository<City, Integer> {
    
    List<City> findAllByCapital(String capital, Sort sort);
}