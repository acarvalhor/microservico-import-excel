package com.microservico.microservicoimportexcel.service.imp;

import com.microservico.microservicoimportexcel.service.ExcelService;
import com.microservico.microservicoimportexcel.wrapper.DadosWrapper;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ExcelImplement implements ExcelService {

    @Override
    public List<DadosWrapper> csv(MultipartFile multipartFile) throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get(multipartFile.getOriginalFilename()));
        CsvToBean<DadosWrapper> csvtoBean = new CsvToBeanBuilder(reader).withType(DadosWrapper.class).build();
        List<DadosWrapper> list = csvtoBean.parse();

        for (DadosWrapper pessoa : list) {
            System.out.println(pessoa);
        }

        return list;
    }

}
