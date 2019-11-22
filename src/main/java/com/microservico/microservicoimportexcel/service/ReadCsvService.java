package com.microservico.microservicoimportexcel.service;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface ReadCsvService<T> {

    T saveCity(T t);

    Boolean deleteCityById(Integer id);

    T findCityByCodeIbge(String codeIbge);

    List<?> getStatesWithNumberOfCities();

    List<T> findAllCitiesByState(String state);

    List<T> saveAllContentFileCsv(MultipartFile file);

    List<T> findAllCapitalsOrderByName(Pageable pageable);

    List<?> findStatesWithTheLargestAndSmallestNumberOfCities();
}