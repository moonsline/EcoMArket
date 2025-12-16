package com.example.EcoMarket.hateoas;

import com.example.EcoMarket.Model.Model_Administrador;
import org.springframework.hateoas.RepresentationModel;

public class AdministradorModel extends RepresentationModel<AdministradorModel> {

    private int idAdmin;
    private String nombre;
    private String email;
    private String rol;

    public AdministradorModel(Model_Administrador admin) {
        this.idAdmin = admin.getIdAdmin();
        this.nombre = admin.getNombre();
        this.email = admin.getEmail();
        this.rol = admin.getRol();
    }

    public int getIdAdmin() { return idAdmin; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public String getRol() { return rol; }
}
// Nota: no se incluye el campo password en el modelo HATEOAS por razones de seguridad. Solo se conserva en el modelo JPA.
// Sería ideal replicarlo con las otras entidades, pero ya veremos.