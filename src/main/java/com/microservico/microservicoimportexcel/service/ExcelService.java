package com.microservico.microservicoimportexcel.service;

import com.microservico.microservicoimportexcel.wrapper.CidadeEstadoWrapper;
import com.microservico.microservicoimportexcel.wrapper.CidadeWrapper;
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

    List<CidadeWrapper> cityByUf(String uf);
}
