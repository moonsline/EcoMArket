package com.example.EcoMarket.Repository;

import com.example.EcoMarket.Model.Model_Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Repository

public interface ProductoRepository extends JpaRepository<Model_Producto, Integer> {

}
