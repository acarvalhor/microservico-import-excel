package com.microservico.microservicoimportexcel.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("csv")
public class ReadCsvResource {

    @PostMapping
    public ResponseEntity<?> readFileCsv(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(file);
    }
}