package com.example.EcoMarket;


import com.example.EcoMarket.Model.Model_Proveedor;
import com.example.EcoMarket.Repository.ProveedorRepository;
import com.example.EcoMarket.Service.ProveedorService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest
@AutoConfigureMockMvc
public class ExampleProveedorTest {
    @Autowired
    ProveedorRepository proveedorRepository;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    private ProveedorService proveedorServiceMock;

    @Test
    @DisplayName("FindAll test")
    void testProveedorServiceeMock(){
        List<Model_Proveedor> proveedores = proveedorRepository.findAll();
        assertNotNull(proveedores);
        assertEquals(1, proveedores.size());
    }

    @Test
    @DisplayName("Rectificar nombre proveedor")
    void testProvedor(){
        Model_Proveedor prueba = proveedorRepository.findById(1).get();
        assertNotNull(prueba);
        assertEquals("Distribuidora Villalobos", prueba.getNombre());
    }

    @Test
    @DisplayName("Test controller")
    void testController(){
        when(proveedorServiceMock.listarProveedor()).thenReturn("Lista completa");
        try {
            mockMvc.perform(get("/proveedores"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("Lista completa"));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            fail();
        }
    }

    @Test
    @DisplayName("Actualizar contacto proveedor")
    void testUpdateProveedorContacto() {
        Model_Proveedor proveedor = proveedorRepository.findById(1).get();
        assertNotNull(proveedor);
        proveedor.setContacto("Abarzuafriend@correo.cl");
        proveedorRepository.save(proveedor);
        Model_Proveedor proveedorActualizado = proveedorRepository.findById(1).get();
        assertEquals("Abarzuafriend@correo.cl", proveedorActualizado.getContacto());
    }
}
