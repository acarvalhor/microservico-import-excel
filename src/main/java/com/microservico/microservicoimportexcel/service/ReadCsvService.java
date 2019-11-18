package com.microservico.microservicoimportexcel.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import com.microservico.microservicoimportexcel.model.DataExcel;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ReadCsvService {

    public List<DataExcel> contentFileCsv(MultipartFile file) {
        List<DataExcel> dataList = new ArrayList<>();
        String line = "";
        String csvSeparator = ",";
        try {
            InputStream inputStream = file.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.startsWith("ibge_id") && !line.endsWith("mesoregion")) {
                    String[] dataFileCsv = line.split(csvSeparator);
                    dataList.add(buildDataExcel(dataFileCsv));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();   
        }
        return dataList;
    }

    private DataExcel buildDataExcel(String[] dataFileCsv) {
        return DataExcel.builder()
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