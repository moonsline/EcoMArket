package com.example.EcoMarket.Service;

import com.example.EcoMarket.Model.Model_Cliente;
import com.example.EcoMarket.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepo;

    public Model_Cliente agregarCliente(Model_Cliente cliente) {
        return clienteRepo.save(cliente);
    }

    public List<Model_Cliente> obtenerTodos() {
        return clienteRepo.findAll();
    }

    public Model_Cliente obtenerPorId(int id) {
        return clienteRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    public Model_Cliente actualizarCliente(int id, Model_Cliente cliente) {
        Model_Cliente existente = obtenerPorId(id);
        existente.setNombre(cliente.getNombre());
        existente.setEmail(cliente.getEmail());
        existente.setPassword(cliente.getPassword());
        existente.setRol(cliente.getRol());
        existente.setDireccion(cliente.getDireccion());
        return clienteRepo.save(existente);
    }

    public String eliminarCliente(int id) {
        if (clienteRepo.existsById(id)) {
            clienteRepo.deleteById(id);
            return "Cliente eliminado correctamente";
        } else {
            return "Cliente no encontrado";
        }
    }
}
