package com.example.EcoMarket;

import com.example.EcoMarket.Model.Model_Producto;
import com.example.EcoMarket.Repository.ProductoRepository;
import com.example.EcoMarket.Service.ProductoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureMockMvc

// esta clase sirve de ejemplo de las clases test, copiando lo que hizo el profe
// no esta conectada a base de datos, va a dar error

public class ExampleProductoTest {
    @Autowired
    ProductoRepository productoRepository;

    @MockitoBean
    ProductoService  productServiceMock;


    @DisplayName("FindAll Test")
        void testProductServiceMock(){
            List<Model_Producto> productos = productoRepository.findAll();
            assertNotNull(productos);
            assertEquals(1,productos.size());

    }

    @Test
    @DisplayName("Test findProduct")
    void testFindProduct(){
        Model_Producto prueba = productoRepository.findById(1).get();
        assertNotNull(prueba);
        assertEquals(prueba.getNombre(), prueba.getNombre());

    }
}




