package com.example.EcoMarket;

import com.example.EcoMarket.Model.Model_Producto;
import com.example.EcoMarket.Repository.ProductoRepository;
import com.example.EcoMarket.Service.ProductoService;
import com.example.EcoMarket.Controller.ProductoController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc



public class ExampleProductoTest {
    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    ProductoService productServiceMock;

    @Test
    @DisplayName("FindAll Test")
    void testProductServiceMock() {
        List<Model_Producto> productos = productoRepository.findAll();
        assertNotNull(productos);
        assertEquals(15, productos.size());

    }

    @Test
    @DisplayName("Rectificar precio producto")
    void testFindProduct() {
        Model_Producto prueba = productoRepository.findById(1).get();
        assertNotNull(prueba);
        assertEquals(800, prueba.getPrecio());

    }

    @Test
    @DisplayName("Test controller productos GET /productos")
    void testControllerProductos() {
        try {
            mockMvc.perform(get("/productos"))
                    .andExpect(status().isOk());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            fail();
        }
    }
    @Test
    @DisplayName("Actualizar nombre producto")
    void testUpdateProductName() {
        // Buscar el producto por su ID
        Model_Producto producto = productoRepository.findById(12).get();

        // Verificar que el producto existe
        assertNotNull(producto);

        // Actualizar el nombre del producto
        producto.setNombre("Bebida desechable Coca-Cola Normal 1.5L");
        productoRepository.save(producto);

        // Recuperar el producto actualizado
        Model_Producto productoActualizado = productoRepository.findById(12).get();

        // Verificar que el nombre se haya actualizado correctamente
        assertEquals("Bebida desechable Coca-Cola Normal 1.5L", productoActualizado.getNombre());
    }
}


