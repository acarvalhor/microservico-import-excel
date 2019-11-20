package com.microservico.microservicoimportexcel.repository;

import com.microservico.microservicoimportexcel.wrapper.DadosWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExcelRepository extends JpaRepository<DadosWrapper, Integer> {

    @Query("SELECT d FROM DadosWrapper d WHERE d.capital = 'TRUE' ORDER BY d.name")
    List<DadosWrapper> findCapitaisOrderByName();
}
