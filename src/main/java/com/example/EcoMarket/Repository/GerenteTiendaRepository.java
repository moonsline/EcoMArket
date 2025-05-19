package com.example.EcoMarket.Repository;

import com.example.EcoMarket.Model.Model_GerenteTienda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GerenteTiendaRepository extends JpaRepository<Model_GerenteTienda, Integer> {
}
