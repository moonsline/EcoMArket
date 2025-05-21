package com.example.EcoMarket.Service;


import com.example.EcoMarket.Model.Model_Cliente;
import com.example.EcoMarket.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public String agregarCliente(Model_Cliente cliente) {
        clienteRepository.save(cliente);
        return "Cliente agregado";
    }
    public String listarCliente() {
        String output ="";
        for (Model_Cliente cliente : clienteRepository.findAll()){
            output += "ID Cliente:" +cliente.getIdCliente()+ "\n";
            output += "Nombre Cliente:" +cliente.getNombre()+ "\n";
            output += "Email Cliente:" +cliente.getEmail()+ "\n";
            output += "Password Cliente:" +cliente.getPassword()+"\n";
            output += "Rol Cliente:" +cliente.getRol()+ "\n";
            output += "Direccion Cliente:" +cliente.getDireccion()+ "\n";
        }
        if (output.isEmpty()){
            return "No se encontro el cliente";
         }else {
            return output;
        }
    }

    public String obtenerCliente(int idCliente) {
        String output ="";
        if (clienteRepository.existsById(idCliente)){
            Model_Cliente cliente = clienteRepository.findById(idCliente).get();
            output += "ID Cliente:" +cliente.getIdCliente()+ "\n";
            output += "Nombre:" +cliente.getNombre()+ "\n";
            output += "Password Cliente:" +cliente.getPassword()+"\n";
            output += "Rol:" +cliente.getRol()+ "\n";
            output += "Direccion:" +cliente.getDireccion()+ "\n";
            return output;
        }else{
            return "No se encontro el cliente";
        }
    }

    public String eliminarCliente(int idCliente) {
        if (clienteRepository.existsById(idCliente)){
            clienteRepository.deleteById(idCliente);
            return "Cliente eliminado";
        }else{
            return "No se encontro el cliente";
        }
    }

    public String actualizarCliente(int idCliente, Model_Cliente cliente) {
        if (clienteRepository.existsById(idCliente)){
            Model_Cliente buscado = clienteRepository.findById(idCliente).get();
            buscado.setNombre(cliente.getNombre());
            buscado.setEmail(cliente.getEmail());
            buscado.setPassword(cliente.getPassword());
            buscado.setRol(cliente.getRol());
            buscado.setDireccion(cliente.getDireccion());
            clienteRepository.save(buscado);
            return "Cliente actualizado";
        }else{
            return "No se encontro el cliente";
        }
    }






}
