package com.example.EcoMarket.Service;

import com.example.EcoMarket.Model.Model_Producto;
import com.example.EcoMarket.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    ProductoRepository productoRepository;

    public String agregarProducto(Model_Producto producto) {
        productoRepository.save(producto);
        return "Producto agregado";
    }

    public String listaProducto(){
        String output = "";
        for (Model_Producto producto: productoRepository.findAll()){
            output += "ID producto: " + producto.getId()+"\n";
            output += "Nombre producto: " + producto.getNombre()+ "\n";
            output += "Precio producto: " + producto.getPrecio()+"\n";
            output += "Stock producto: " + producto.getStock()+"\n";
        }
        if (output.isEmpty()){
            return "No se encontro producto";
        }else{
            return output;
        }
    }

    public String obtenerProducto(int id){
        String output = "";
        if (productoRepository.existsById(id)){
            Model_Producto producto = productoRepository.findById(id).get();
            output += "Id Producto: " + producto.getId()+"\n";
            output += "Nombre Producto: " + producto.getNombre()+"\n";
            output+= "Precio Producto: " + producto.getPrecio()+"\n";
            output+= "Stock Producto: " + producto.getStock()+"\n";
            return output;
        } else {
            return "No se encontro el producto";
        }
    }

    public String eliminarProducto(int id){
        if(productoRepository.existsById(id)){
            productoRepository.deleteById(id);
            return "Producto eliminado";
        }else{
            return "No se encontro el producto";
        }
    }

    public String actualizarProducto(int id, Model_Producto producto){
        if (productoRepository.existsById(id)){
            Model_Producto buscado = productoRepository.findById(id).get();
            buscado.setNombre(producto.getNombre());
            buscado.setPrecio(producto.getPrecio());
            buscado.setStock(producto.getStock());
            return "Producto Actualizado";
        }else {
            return "No se encontro Producto";
        }
    }

}
