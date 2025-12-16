package com.example.EcoMarket;

import com.example.EcoMarket.Model.Model_Producto;
import com.example.EcoMarket.Repository.ProductoRepository;
import com.example.EcoMarket.Service.ProductoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ExampleProductoTest {

    @MockBean
    private ProductoRepository productoRepository;

    @MockBean
    private ProductoService productoService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("FindAll Test")
    void testProductServiceMock() {
        Model_Producto producto = new Model_Producto();
        when(productoRepository.findAll()).thenReturn(Arrays.asList(producto));
        List<Model_Producto> productos = productoRepository.findAll();
        assertNotNull(productos);
        assertEquals(1, productos.size());
    }

    @Test
    @DisplayName("Rectificar precio producto")
    void testFindProduct() {
        Model_Producto prueba = new Model_Producto();
        prueba.setPrecio(800);
        when(productoRepository.findById(1)).thenReturn(Optional.of(prueba));
        Model_Producto result = productoRepository.findById(1).get();
        assertNotNull(result);
        assertEquals(800, result.getPrecio());
    }

    @Test
    @DisplayName("Test controller productos GET /productos")
    void testControllerProductos() {
        try {
            Model_Producto producto = new Model_Producto();
            when(productoService.obtenerTodos()).thenReturn(Arrays.asList(producto));
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
        Model_Producto producto = new Model_Producto();
        producto.setNombre("Bebida desechable Coca-Cola Normal 1.5L");
        when(productoRepository.findById(12)).thenReturn(Optional.of(producto));
        when(productoRepository.save(producto)).thenReturn(producto);

        // Simula actualización
        Model_Producto productoActualizado = productoRepository.findById(12).get();
        productoActualizado.setNombre("Bebida desechable Coca-Cola Normal 1.5L");
        productoRepository.save(productoActualizado);

        // Verifica actualización
        when(productoRepository.findById(12)).thenReturn(Optional.of(productoActualizado));
        Model_Producto result = productoRepository.findById(12).get();
        assertEquals("Bebida desechable Coca-Cola Normal 1.5L", result.getNombre());
    }
}
