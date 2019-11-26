package com.microservico.microservicoimportexcel.service.imp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.microservico.microservicoimportexcel.model.*;
import com.microservico.microservicoimportexcel.model.wrapper.*;
import com.microservico.microservicoimportexcel.repository.CityRepository;
import com.microservico.microservicoimportexcel.service.ReadCsvService;
import com.microservico.microservicoimportexcel.utils.GeoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ReadCsvServiceImp implements ReadCsvService<City> {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private CityRepository cityRepository;

    @Override
    public List<City> saveAllContentFileCsv(MultipartFile file) {
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

    @Override
    public List<City> findAllCapitalsOrderByName(Pageable pageable) {
        return this.cityRepository.findAllByCapital("true", Sort.by("name").ascending());
    }

    @Override
    public List<StateWrapper> findStatesWithTheLargestAndSmallestNumberOfCities() {
        List<StateWrapper> stateWrappers = this.getStatesWithNumberOfCities();
        StateWrapper stateWithTheLargestNumberOfCities = stateWrappers.stream()
            .max(Comparator.comparing(StateWrapper::getNumberOfCities)).orElseThrow(NoSuchElementException::new);
        StateWrapper stateWithTheSmallestNumberOfCities = stateWrappers.stream()
            .min(Comparator.comparing(StateWrapper::getNumberOfCities)).orElseThrow(NoSuchElementException::new);
        return Arrays.asList(stateWithTheLargestNumberOfCities, stateWithTheSmallestNumberOfCities);
    }

    @Override
    public List<StateWrapper> getStatesWithNumberOfCities() {
		return this.cityRepository.findStatesWithNumberOfCities();
    }
    
    @Override
	public City findCityByCodeIbge(String codeIbge) {
		return this.cityRepository.findByIdIbge(codeIbge);
    }

    @Override
    public List<CityWrapper> findAllCitiesByState(String state) {
		return this.cityRepository.findAllByUf(state);
	}
    
    @Override
    public City saveCity(City city) {
		return this.cityRepository.save(city);
    }

    @Override
	public ResponseWrapper deleteCityById(Integer id) {
        Optional<City> optionalCity = this.cityRepository.findById(id);
        if (optionalCity.isPresent()) {
            this.cityRepository.delete(optionalCity.get());
            return ResponseWrapper.builder().message("Successfully deleted city!").build();
        }
		return null;
    }

    @Override
    public List<City> filterDataColumnFileCsv(String column, String textSearch) {
        List<City> cities = new ArrayList<>();
        if (verifyExistsColumn(column)) {
            StringBuilder query = new StringBuilder("SELECT c FROM City c WHERE c.")
                .append(column).append(" LIKE '%").append(textSearch).append("%'");
            cities.addAll(this.entityManager.createQuery(query.toString(), City.class)
                .getResultList());
        } 
        return cities;
    }
    
    @Override
    public ResponseWrapper findNumberOfRecordsPerColumn(String column) {
        Integer totalRecords = 0;
        if(verifyExistsColumn(column)) {
            StringBuilder query = new StringBuilder("SELECT COUNT(c.").append(column).append(") FROM City c WHERE c.")
                .append(column).append(" IS NOT NULL AND c.").append(column).append(" != ''");
            totalRecords = this.entityManager.createQuery(query.toString(), Long.class)
                .getSingleResult().intValue();
        }
        return totalRecords != 0 ? ResponseWrapper.builder()
            .message("Number of column records [" + column + "]: " + totalRecords).build() : null;
    }

    @Override
    public ResponseWrapper findNumberTotalOfRecords() {
		return ResponseWrapper.builder().message("Number total of records: " + this.cityRepository.count()).build();
    }
    
    @Override
    public ResponseCityWrapper getTheTwoFurthestCitiesBasedOnLocation() {
        List<ResponseCityWrapper> responseCityWrappers = new ArrayList<>();
        //SELECT * FROM city WHERE longitude = (SELECT MAX(c.longitude) FROM city c);
        //SELECT * FROM city WHERE longitude = (SELECT MIN(c.longitude) FROM city c);
		return null;
    }
    
    private ResponseCityWrapper buildResponseCityWrapper(City cityMax, City cityMin) {
        return ResponseCityWrapper.builder().citiesWrapper(Arrays.asList(buildCity(cityMax), buildCity(cityMin)))
            .distance(distanceInKm(cityMax, cityMin)).build();
    }

    private Double distanceInKm(City city, City city2) {
        return GeoUtils.builder().latitude(Double.valueOf(city.getLat()))
            .longititude(Double.valueOf(city.getLon())).build().distanceInKm(GeoUtils.builder()
            .latitude(Double.valueOf(city2.getLat())).longititude(Double.valueOf(city2.getLon())).build());
    }

    private CityWrapper buildCity(City city) {
        return CityWrapper.builder().name(city.getName()).build();
    }

    private boolean verifyExistsColumn(String column) {
        Boolean existsColumn = false;
        switch(column.trim()) {
            case "uf": case "name": case "ibge_id": case "idIbge": case "capital": case "lon": case "lat": 
            case "no_acents": case "noAcents": case "alternative_names": case "alternativeNames": 
            case "microregion": case "mesoregion":
                existsColumn = true;
                break;
            default:
                break;
        }
        return existsColumn;
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