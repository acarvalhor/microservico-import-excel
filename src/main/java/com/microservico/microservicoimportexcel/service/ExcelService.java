package com.microservico.microservicoimportexcel.service;

import com.microservico.microservicoimportexcel.wrapper.CidadeEstadoWrapper;
import com.microservico.microservicoimportexcel.wrapper.DadosWrapper;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ExcelService {

    List<DadosWrapper> csv(MultipartFile multipartFile) throws IOException;

    List<DadosWrapper> findCapitaisOrderByName();

    List<CidadeEstadoWrapper> findBiggerSmallerCityByState();

    List<CidadeEstadoWrapper> numberOfCityByState();

    List<DadosWrapper> findDataCityByIdIBGE(Double id);

    List<CityName> findCityByUf(String uf);

    DadosWrapper insertDados(DadosWrapper dadosWrapper);
}
