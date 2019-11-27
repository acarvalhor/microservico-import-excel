package com.microservico.microservicoimportexcel.service;

import com.microservico.microservicoimportexcel.model.City;
import com.microservico.microservicoimportexcel.service.imp.ReadCsvServiceImp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ReadCsvServiceTest {

    @InjectMocks
    private ReadCsvServiceImp service;

    private City city;

    @Before
    public void init() {
        this.city = buildCity();
    }

    @Test
    public void shouldSaveCity() {
        //TODO implementar tests
    }

    private City buildCity() {
        return City.builder()
                .id(1)
                .lat("")
                .lon("")
                .name("")
                .idIbge("")
                .capital("")
                .mesoregion("")
                .microregion("")
                .alternativeNames("")
                .build();
    }
}