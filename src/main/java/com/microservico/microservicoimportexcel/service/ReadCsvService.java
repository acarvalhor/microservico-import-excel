package com.microservico.microservicoimportexcel.service;

import java.util.List;
import com.microservico.microservicoimportexcel.model.wrapper.ResponseWrapper;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface ReadCsvService<T> {

    T saveCity(T t);

    T findCityByCodeIbge(String codeIbge);

    List<?> getStatesWithNumberOfCities();

    List<?> findAllCitiesByState(String state);

    ResponseWrapper deleteCityById(Integer id);

    List<T> saveAllContentFileCsv(MultipartFile file);

    List<T> findAllCapitalsOrderByName(Pageable pageable);

    ResponseWrapper findNumberOfRecordsPerColumn(String column);

    List<?> findStatesWithTheLargestAndSmallestNumberOfCities();

    List<T> filterDataColumnFileCsv(String column, String search);
}