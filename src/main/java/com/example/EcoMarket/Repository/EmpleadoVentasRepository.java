package com.example.EcoMarket.Repository;

import com.example.EcoMarket.Model.Model_EmpleadoVentas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoVentasRepository extends JpaRepository<Model_EmpleadoVentas, Integer> {
}
