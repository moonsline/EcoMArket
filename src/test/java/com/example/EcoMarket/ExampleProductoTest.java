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

// esta clase sirve de ejemplo de las clases test, copiando lo que hizo el profe
// no esta conectada a base de datos, va a dar error

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
        assertEquals(1, productos.size());

    }

    @Test
    @DisplayName("Rectificar precio producto")
    void testFindProduct() {
        Model_Producto prueba = productoRepository.findById(1).get();
        assertNotNull(prueba);
        assertEquals(800, prueba.getPrecio());

    }

    @Test
    @DisplayName("Test controller")
    void testController() {
        //Indicamos que el retorno de listarProductos se identificara con el valor ingresado en thenReturn
        when(productServiceMock.listaProducto()).thenReturn("Lista completa");

        //Bloque Try Except/Catch
        //Nos permite probar una funcionalidad de codigo o un segmento de codigo y si este falla
        //Se captura por medio de Catch(Exception var) y ejecuta un control de error
        try {
            //MockMvc Nos permite realizar consultas HTTPMethod
            //perform nos permite ejecutar dichas llamadas y luego ingresamos el metodo HTTP correspondiente
            //adicionalmente podemos agregar parametros u variables adicionales de ser requerido
            //Finalmente andExcept nos permite indicar que esperamos de dicha respuesta HTTP
            //Tanto codigo como contenido
            mockMvc.perform(get("/productos"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("Lista completa"));

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            fail();
        }
    }
}


