package com.example.EcoMarket.hateoas;

import com.example.EcoMarket.Model.Model_Cliente;
import org.springframework.hateoas.RepresentationModel;

public class ClienteModel extends RepresentationModel<ClienteModel> {

    private int idCliente;
    private String nombre;
    private String email;
    private String rol;
    private String direccion;

    public ClienteModel(Model_Cliente cliente) {
        this.idCliente = cliente.getIdCliente();
        this.nombre = cliente.getNombre();
        this.email = cliente.getEmail();
        this.rol = cliente.getRol();
        this.direccion = cliente.getDireccion();
    }

    public int getIdCliente() { return idCliente; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public String getRol() { return rol; }
    public String getDireccion() { return direccion; }
}
