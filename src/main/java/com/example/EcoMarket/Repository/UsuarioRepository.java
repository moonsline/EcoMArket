package com.example.EcoMarket.Repository;

import com.example.EcoMarket.Model.Model_Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Model_Usuario,Integer> {
}
