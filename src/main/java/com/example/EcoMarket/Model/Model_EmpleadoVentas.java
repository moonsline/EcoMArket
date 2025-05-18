package com.example.EcoMarket.Model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Model_EmpleadoVentas extends Model_Usuario {
    private List<String> historialVentas;

}
