package com.microservico.microservicoimportexcel.service.imp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.microservico.microservicoimportexcel.model.City;
import com.microservico.microservicoimportexcel.model.wrapper.CityWrapper;
import com.microservico.microservicoimportexcel.repository.CityRepository;
import com.microservico.microservicoimportexcel.service.ReadCsvService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ReadCsvServiceImp implements ReadCsvService<City> {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public List<City> saveContentFileCsv(MultipartFile file) {
        List<City> dataList = new ArrayList<>();
        String line = "";
        String csvSeparator = ",";
        try {
            InputStream inputStream = file.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.startsWith("ibge_id") && !line.endsWith("mesoregion")) {
                    String[] dataFileCsv = line.split(csvSeparator);
                    dataList.add(buildCity(dataFileCsv));
                }
            }
            this.cityRepository.saveAll(dataList);
        } catch (Exception e) {
            e.printStackTrace();   
        }
        return dataList;
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

    @Override
    public List<City> findAllCapitalsOrderByName(Pageable pageable) {
        return this.cityRepository.findAllByCapital("true", Sort.by("name").ascending());
    }

	public List<CityWrapper> findStatesWithTheLargestAndSmallestNumberOfCities() {
        List<CityWrapper> citiesWrapper = this.cityRepository.findStatesWithTheLargestAndSmallestNumberOfCities();
        return null;
	}
}