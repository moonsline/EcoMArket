package com.example.EcoMarket;


import com.example.EcoMarket.Model.Model_EmpleadoVentas;
import com.example.EcoMarket.Repository.EmpleadoVentasRepository;
import com.example.EcoMarket.Service.EmpleadoVentasService;

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
public class ExampleEmpleadoVentasTest {
    @Autowired
    EmpleadoVentasRepository empleadoVentasRepository;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private EmpleadoVentasService empleadoVentasServiceMock;

    @Test
    @DisplayName("FindAll test")
    void testEmpleadoVentasServiceMock(){
        List<Model_EmpleadoVentas> empleadoVentas = empleadoVentasRepository.findAll();
        assertNotNull(empleadoVentas);
        assertEquals(4, empleadoVentas.size());
    }

    @Test
    @DisplayName("Rectificar nombre empleadoVentas")
    void testFindEmpleadoVentas(){
        Model_EmpleadoVentas prueba = empleadoVentasRepository.findById(1).get();
        assertNotNull(prueba);
        assertEquals("Juan Python", prueba.getNombre());
    }

    @Test
    @DisplayName("Test controller")
    void testController(){
        when(empleadoVentasServiceMock.listarEmpleadoVentas()).thenReturn("Lista completa");

        try{
            mockMvc.perform(get("/empleados"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("Lista completa"));
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            fail();
        }

    }

    @Test
    @DisplayName("Actualizar nombre empleadoVentas")
    void testUpdateEmpleadoVentas(){
        Model_EmpleadoVentas prueba = empleadoVentasRepository.findById(1).get();
        assertNotNull(prueba);
        prueba.setNombre("Jose Python");
        empleadoVentasRepository.save(prueba);
        Model_EmpleadoVentas empleadoVentasActualizado = empleadoVentasRepository.findById(1).get();
        assertEquals("Jose Python", empleadoVentasActualizado.getNombre());
    }
}
