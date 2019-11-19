package com.microservico.microservicoimportexcel.service.imp;

import com.microservico.microservicoimportexcel.service.ExcelService;
import com.microservico.microservicoimportexcel.wrapper.DadosWrapper;
import org.springframework.stereotype.Service;
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

    @Override
    public List<DadosWrapper> csv(MultipartFile multipartFile) throws IOException {
        BufferedReader br;
        List<String> result = new ArrayList<>();
        List<DadosWrapper> resultDadosWrappers = new ArrayList<>();
        try {

            String line;
            InputStream is = multipartFile.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                if(!line.startsWith("ibge_id")) {
                    String[] content = line.split(",");
                    resultDadosWrappers.add(buildTeste(content));
                    result.add(line);
                }
            }
            for (DadosWrapper pessoa : resultDadosWrappers) {
                System.out.println(pessoa.toString());
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return resultDadosWrappers;
    }

    private DadosWrapper buildTeste(String[] content) {
        DadosWrapper dadosWrapper = new DadosWrapper();
        dadosWrapper.setIbge_id(Double.valueOf(content[0]));
        dadosWrapper.setUf(content[1]);
        dadosWrapper.setName(content[2]);
        dadosWrapper.setCapital(content[3]);
        dadosWrapper.setLon(content[4]);
        dadosWrapper.setLat(content[5]);
        dadosWrapper.setNo_accents(content[6]);
        dadosWrapper.setAlternative_names(content[7]);
        dadosWrapper.setMicroregion(content[8]);
        dadosWrapper.setMesoregion(content[9]);
        return dadosWrapper;
    }


}
