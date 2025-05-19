package com.example.EcoMarket.Repository;

import com.example.EcoMarket.Model.Model_Logistica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogisticaRepository extends JpaRepository<Model_Logistica, Integer> {
}
