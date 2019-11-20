package com.microservico.microservicoimportexcel.service;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface ReadCsvService<T> {

    List<T> saveContentFileCsv(MultipartFile file);

    List<T> findAllCapitalsOrderByName(Pageable pageable);
}