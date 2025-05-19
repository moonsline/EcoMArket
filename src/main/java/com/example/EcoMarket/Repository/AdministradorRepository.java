package com.example.EcoMarket.Repository;

import com.example.EcoMarket.Model.Model_Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministradorRepository extends JpaRepository<Model_Administrador, Integer> {
}
