package com.microservico.microservicoimportexcel.resource;

import java.util.List;

import com.microservico.microservicoimportexcel.model.City;
import com.microservico.microservicoimportexcel.model.wrapper.CityWrapper;
import com.microservico.microservicoimportexcel.service.imp.ReadCsvServiceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("cities")
public class ReadCsvResource {

    @Autowired
    private ReadCsvServiceImp readCsvService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFileCsv(@RequestParam("file") MultipartFile file) {
        List<City> cities = this.readCsvService.saveContentFileCsv(file);
        if (cities.isEmpty()) {    
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(cities);
    }

    @GetMapping("/capitals")
    public ResponseEntity<?> getCapitals(Pageable pageable) {
        List<City> cities = this.readCsvService.findAllCapitalsOrderByName(pageable);
        if (cities.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(cities);
    }

    @GetMapping("/statesWithTheLargestAndSmallestNumberOfCities")
    public ResponseEntity<?> getStatesWithTheLargestAndSmallestNumberOfCities() {
        List<CityWrapper> citiesWrapper = this.readCsvService.findStatesWithTheLargestAndSmallestNumberOfCities();
        return null;
    }

}