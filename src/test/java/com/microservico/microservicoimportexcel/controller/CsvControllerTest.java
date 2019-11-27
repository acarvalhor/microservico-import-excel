package com.microservico.microservicoimportexcel.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservico.microservicoimportexcel.wrapper.DadosWrapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CsvControllerTest {

    @Autowired
    public WebApplicationContext context;

    private MockMvc mvc;

    private ObjectMapper objectMapper;

    private List<DadosWrapper> dadosWrapperList;

    @Before
    public void setup(){
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        objectMapper = new ObjectMapper();
        dadosWrapperList = buildListDadosWrapper();
    }

    private List<DadosWrapper> buildListDadosWrapper() {
        List<DadosWrapper> list = new ArrayList<>();
        list.add(DadosWrapper.builder().name("teste").capital("true").alternative_names("alexandre").
                ibge_id(123214555.00).lat("-12365465").lon("465464").mesoregion("ale").microregion("Ale").
                no_accents("ale").uf("AL").qtdCidades(20).id(60000).build());
        list.add(DadosWrapper.builder().name("luiz").capital("true").alternative_names("luiz").
                ibge_id(6546456.65464).lat("-987654").lon("6543212").mesoregion("luiz").microregion("Luiz").
                no_accents("luiz").uf("L").qtdCidades(20).id(60000).build());
        return list;
    }

    @Test
    public void testaRequisicaoIdSucesso() throws Exception {
        String url = "/numberOfCityByState";
//        this.mvc.perform(get(url)).andExpect(status().isOk()).andExpect(objectMapper.writeValueAsString());
    }
}
