package com.microservico.microservicoimportexcel.service.imp;

import com.microservico.microservicoimportexcel.exception.NotFoundException;
import com.microservico.microservicoimportexcel.repository.ExcelRepository;
import com.microservico.microservicoimportexcel.service.ExcelService;
import com.microservico.microservicoimportexcel.wrapper.CidadeEstadoWrapper;
import com.microservico.microservicoimportexcel.wrapper.CityName;
import com.microservico.microservicoimportexcel.wrapper.DadosWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class ExcelImplement implements ExcelService {

    @Autowired
    ExcelRepository excelRepository;

    @PersistenceContext
    EntityManager entityManager;

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

    @Override
    public List<CidadeEstadoWrapper> findBiggerSmallerCityByState() {
        ArrayList<CidadeEstadoWrapper> numeroCidadeByEstado = new ArrayList<>();
        excelRepository.findCapitaisOrderByName().stream().forEach((dadosWrapper) -> {
            Integer numeroCidades = excelRepository.numberOfCityByState(dadosWrapper.getUf());
            CidadeEstadoWrapper cidadeEstadoWrapper = new CidadeEstadoWrapper().builder().
                    numeroCidades(numeroCidades).nomeEstado(dadosWrapper.getUf()).build();
            numeroCidadeByEstado.add(cidadeEstadoWrapper);

        });

        return   maiorMenorNumeroDeCidades(numeroCidadeByEstado);
    }

    private List<CidadeEstadoWrapper> maiorMenorNumeroDeCidades(ArrayList<CidadeEstadoWrapper> numeroCidadeByEstado) {
        List<CidadeEstadoWrapper> listMaiorMenorValor = new ArrayList<>();
        Collections.sort(numeroCidadeByEstado,MAIS_CIDADES);
        listMaiorMenorValor.add(numeroCidadeByEstado.get(0));
        Collections.sort(numeroCidadeByEstado,MENOS_CIDADES);
        listMaiorMenorValor.add(numeroCidadeByEstado.get(numeroCidadeByEstado.size() - 1));
      return listMaiorMenorValor;
    }

    @Override
    public List<CidadeEstadoWrapper> numberOfCityByState() {
       List<DadosWrapper> objects = excelRepository.numberOfCityByState();
       List<CidadeEstadoWrapper> cidadeEstadoWrapperList =
               objects.stream().map(dado -> new CidadeEstadoWrapper(dado.getQtdCidades(), dado.getUf())).collect(toList());

        return cidadeEstadoWrapperList;
    }

    @Override
    public List<DadosWrapper> findDataCityByIdIBGE(Double id) {
        List<DadosWrapper> dadosWrappers = excelRepository.dataCityByIdIBGE(id);
        return dadosWrappers;
    }

    @Override
    public List<CityName> findCityByUf(String uf) {
        List<CityName> cityNames = excelRepository.findCityByUf(uf);
        return cityNames;
    }

    @Override
    public DadosWrapper insertDados(DadosWrapper dadosWrapper) {
        return excelRepository.save(dadosWrapper);
    }

    @Override
    public String deleteDados(DadosWrapper dadosWrapper) {
        try {
            excelRepository.delete(dadosWrapper);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return "Dados Excluídos com Sucesso";
    }

    @Override
    public List<DadosWrapper> finByColumn(String column, String text){
        if (ValidateColumn(column)) {
            return entityManager.createNativeQuery(
                    "SELECT d.* FROM dados_cidade d WHERE d." + column + " like '%" + text + "%'", DadosWrapper.class).getResultList();
        }
       throw new NotFoundException("Nome da Coluna Incorreto");
    }

    private boolean ValidateColumn(String column) {
        switch (column) {
            case "alternative_names":
            case "capital":
            case "ibge_id":
            case "lat":
            case "long":
            case "mesoregion":
            case "microregion":
            case "name":
            case "no_accents":
            case "uf":
                return true;
        }
        return false;
    }

    @Override
    public Long findNumberOfRecordsByColumn(String column) {
        if (ValidateColumn(column)) {
            return entityManager.createQuery(
                    "SELECT COUNT(d) FROM DadosWrapper d WHERE d." + column + " IS NOT NULL", Long.class).getSingleResult();
        }
        throw new NotFoundException("Nome da Coluna Incorreto");
    }

    private static final Comparator<CidadeEstadoWrapper> MAIS_CIDADES = (a1, a2) -> a2.getNumeroCidades() - a1.getNumeroCidades();
    private static final Comparator<CidadeEstadoWrapper> MENOS_CIDADES = (a1, a2) -> a2.getNumeroCidades() + a1.getNumeroCidades();
}
