package com.example.EcoMarket.Repository;

import com.example.EcoMarket.Model.Model_Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteRepository {

    private List<Model_Cliente> clientes = new ArrayList<>();

    public List<Model_Cliente> obtenerClientes() {
        return clientes;
    }

}
