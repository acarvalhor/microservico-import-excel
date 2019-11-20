package com.microservico.microservicoimportexcel.controller;

import com.microservico.microservicoimportexcel.repository.ExcelRepository;
import com.microservico.microservicoimportexcel.service.ExcelService;
import com.microservico.microservicoimportexcel.wrapper.DadosWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class CsvController {

    @Autowired
    ExcelService excelService;

    @RequestMapping(value = "/extractCsv", method = RequestMethod.POST)
    public ResponseEntity<?> sendCSV(@RequestParam("file")MultipartFile multipartFile) throws IOException {

        return ResponseEntity.ok(this.excelService.csv(multipartFile));
    }

    @RequestMapping(value = "/findCapitaisOrdenadaPorNome", method = RequestMethod.GET)
    public ResponseEntity<?> findCapitaisOrderByName(){
        List<DadosWrapper> dadosWrapper = excelService.findCapitaisOrderByName();
        return ResponseEntity.ok(dadosWrapper);
    }
    
}
