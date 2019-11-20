package com.microservico.microservicoimportexcel.service.imp;

import com.microservico.microservicoimportexcel.repository.ExcelRepository;
import com.microservico.microservicoimportexcel.service.ExcelService;
import com.microservico.microservicoimportexcel.wrapper.DadosWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelImplement implements ExcelService {

    @Autowired
    ExcelRepository excelRepository;

    @Override
    public List<DadosWrapper> csv(MultipartFile multipartFile) throws IOException {
        BufferedReader br;
        List<DadosWrapper> resultDadosWrappers = new ArrayList<>();
        try {

            String line;
            InputStream is = multipartFile.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                if(!line.startsWith("ibge_id")) {
                    String[] content = line.split(",");
                    resultDadosWrappers.add(buildTeste(content));
                }
            }

            excelRepository.saveAll(resultDadosWrappers);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return resultDadosWrappers;
    }

    private DadosWrapper buildTeste(String[] content) {
        return DadosWrapper.builder()
                .ibge_id(Double.valueOf(content[0]))
                .uf(content[1])
                .name(content[2])
                .capital(content[3])
                .lon(content[4])
                .lat(content[5])
                .no_accents(content[6])
                .alternative_names(content[7])
                .microregion(content[8])
                .mesoregion(content[9])
                .build();
    }

    @Override
    public List<DadosWrapper> findCapitaisOrderByName() {
        return excelRepository.findCapitaisOrderByName();
    }
}
