package com.microservico.microservicoimportexcel.resource;

import java.util.List;

import com.microservico.microservicoimportexcel.model.DataExcel;
import com.microservico.microservicoimportexcel.service.ReadCsvService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("csv")
public class ReadCsvResource {

    @Autowired
    private ReadCsvService readCsvService;

    @PostMapping
    public ResponseEntity<?> readFileCsv(@RequestParam("file") MultipartFile file) {
        List<DataExcel> list = this.readCsvService.contentFileCsv(file);
        if (list.isEmpty()) {    
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(list);
    }
}