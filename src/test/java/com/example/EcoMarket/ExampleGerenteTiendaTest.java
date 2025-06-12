package com.example.EcoMarket;


import com.example.EcoMarket.Model.Model_EmpleadoVentas;
import com.example.EcoMarket.Model.Model_GerenteTienda;
import com.example.EcoMarket.Repository.GerenteTiendaRepository;
import com.example.EcoMarket.Service.GerenteTiendaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ExampleGerenteTiendaTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private GerenteTiendaRepository gerenteTiendaRepository;
    @Autowired
    private GerenteTiendaService gerenteTiendaServiceMock;

    @Test
    @DisplayName("FindAll test")
    void testGerenteTiendaServiceMock(){
        List<Model_GerenteTienda> gerenteTiendas = gerenteTiendaRepository.findAll();
        assertNotNull(gerenteTiendas);
        assertEquals(1, gerenteTiendas.size());
    }

    @Test
    @DisplayName("Rectificar nombre empleadoVentas")
    void testFindGerenteTienda(){
        Model_GerenteTienda prueba = gerenteTiendaRepository.findById(1).get();
        assertNotNull(prueba);
        assertEquals("Orlando Sepulveda", prueba.getNombre());
    }

    @Test
    @DisplayName("Test controller")
    void testController(){
        when(gerenteTiendaServiceMock.listarGerenteTienda()).thenReturn("Lista completa");

        try{
            mockMvc.perform(get("/gerentes"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("Lista completa"));
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            fail();
        }

    }

    @Test
    @DisplayName("Actualizar nombre gerenteTienda")
    void testUpdateGerenteTienda(){
        Model_GerenteTienda prueba = gerenteTiendaRepository.findById(1).get();
        assertNotNull(prueba);
        prueba.setNombre("Orlandios Sepulveda");
        gerenteTiendaRepository.save(prueba);
        Model_GerenteTienda gerenteTiendaActualizado = gerenteTiendaRepository.findById(1).get();
        assertEquals("Orlandios Sepulveda", gerenteTiendaActualizado.getNombre());
    }
}
