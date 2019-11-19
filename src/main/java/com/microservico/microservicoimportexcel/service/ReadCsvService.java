package com.microservico.microservicoimportexcel.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import com.microservico.microservicoimportexcel.model.City;
import com.microservico.microservicoimportexcel.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ReadCsvService {

    @Autowired
    private CityRepository cityRepository;

    public List<City> contentFileCsv(MultipartFile file) {
        List<City> dataList = new ArrayList<>();
        String line = "";
        String csvSeparator = ",";
        try {
            InputStream inputStream = file.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.startsWith("ibge_id") && !line.endsWith("mesoregion")) {
                    String[] dataFileCsv = line.split(csvSeparator);
                    dataList.add(saveCity(dataFileCsv));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();   
        }
        return dataList;
    }

    private City saveCity(String[] dataFileCsv) {
        return this.cityRepository.save(buildCity(dataFileCsv));
    }

    private City buildCity(String[] dataFileCsv) {
        return City.builder()
                .idIbge(dataFileCsv[0])
                .uf(dataFileCsv[1])
                .name(dataFileCsv[2])
                .capital(dataFileCsv[3])
                .lon(dataFileCsv[4])
                .lat(dataFileCsv[5])
                .noAcents(dataFileCsv[6])
                .alternativeNames(dataFileCsv[7])
                .microregion(dataFileCsv[8])
                .mesoregion(dataFileCsv[9])
                .build();
    }
}