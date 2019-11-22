package com.microservico.microservicoimportexcel.resource;

import java.util.List;

import com.microservico.microservicoimportexcel.model.City;
import com.microservico.microservicoimportexcel.model.wrapper.CityWrapper;
import com.microservico.microservicoimportexcel.service.imp.ReadCsvServiceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("cities")
public class ReadCsvResource {

    @Autowired
    private ReadCsvServiceImp readCsvService;

    /**
     * 1. Ler o arquivo CSV das cidades para a base de dados;
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public ResponseEntity<?> uploadFileCsv(@RequestParam("file") MultipartFile file) {
        List<City> cities = this.readCsvService.saveAllContentFileCsv(file);
        if (cities.isEmpty()) {    
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(cities);
    }

    /**
     * 2. Retornar somente as cidades que são capitais ordenadas por nome;
     * @param pageable
     * @return
     */
    @GetMapping("/capitals")
    public ResponseEntity<?> getCapitals(Pageable pageable) {
        List<City> cities = this.readCsvService.findAllCapitalsOrderByName(pageable);
        if (cities.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(cities);
    }

    /**
     * 3. Retornar o nome do estado com a maior e menor quantidade de cidades e a quantidade de cidades;
     * @return
     */
    @GetMapping("/statesWithTheLargestAndSmallestNumberOfCities")
    public ResponseEntity<?> getStatesWithTheLargestAndSmallestNumberOfCities() {
        List<CityWrapper> citiesWrapper = this.readCsvService.findStatesWithTheLargestAndSmallestNumberOfCities();
        if (citiesWrapper.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(citiesWrapper);
    }

    /**
     * 4. Retornar a quantidade de cidades por estado;
     * @return
     */
    @GetMapping("/statesWithNumberOfCities")
    public ResponseEntity<?> getStatesWithNumberOfCities() {
        List<CityWrapper> citiesWrapper = this.readCsvService.getStatesWithNumberOfCities();
        if (citiesWrapper.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(citiesWrapper);
    }

    /**
     * 5. Obter os dados da cidade informando o id do IBGE;
     * @param codeIbge
     * @return
     */
    @GetMapping("/searchCityByCodeIbge")
    public ResponseEntity<?> searchCityByCodeIbge(@RequestParam("code") String codeIbge) {
        City city = this.readCsvService.findCityByCodeIbge(codeIbge);
        if (city == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(city);
    }

    /**
     * 6. Retornar o nome das cidades baseado em um estado selecionado;
     * @param state
     * @return
     */
    @GetMapping("/searchAllCitiesByState")
    public ResponseEntity<?> searchCitiesByState(@RequestParam String state) {
        List<City> cities = this.readCsvService.findAllCitiesByState(state);
        if (cities.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(cities);
    }

    /**
     * 7. Permitir adicionar uma nova Cidade;
     * @param city
     * @return 
     */
    @PostMapping("/save")
    public ResponseEntity<?> saveCity(@RequestBody City city) {
        City newCity = this.readCsvService.saveCity(city);
        if (newCity == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(newCity);
    }

    /**
     * 8. Permitir deletar uma cidade;
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> saveCity(@PathVariable("id") Integer id) {
        Boolean deleted = this.readCsvService.deleteCityById(id);
        if (!deleted) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok("Successfully deleted city!");
    }

    /**
     * 9. Permitir selecionar uma coluna (do CSV) e através dela entrar com uma string para filtrar. 
     * Retornar assim todos os objetos que contenham tal string;
     * @return
     */
    @GetMapping("/filterDataColumnFileCsv")
    public ResponseEntity<?> filterDataColumnFileCsv() {
        // TODO implementar item 9
        return null;
    }

}