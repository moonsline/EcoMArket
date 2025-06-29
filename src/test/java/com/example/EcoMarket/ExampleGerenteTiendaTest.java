package com.example.EcoMarket;

import com.example.EcoMarket.Model.Model_GerenteTienda;
import com.example.EcoMarket.Repository.GerenteTiendaRepository;
import com.example.EcoMarket.Service.GerenteTiendaService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ExampleGerenteTiendaTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GerenteTiendaRepository gerenteTiendaRepository;

    @MockBean
    private GerenteTiendaService gerenteTiendaService;

    @Test
    @DisplayName("FindAll test")
    void testGerenteTiendaServiceMock(){
        Model_GerenteTienda gerente = new Model_GerenteTienda();
        when(gerenteTiendaRepository.findAll()).thenReturn(Arrays.asList(gerente));
        List<Model_GerenteTienda> gerenteTiendas = gerenteTiendaRepository.findAll();
        assertNotNull(gerenteTiendas);
        assertEquals(1, gerenteTiendas.size());
    }

    @Test
    @DisplayName("Rectificar nombre empleadoVentas")
    void testFindGerenteTienda(){
        Model_GerenteTienda prueba = new Model_GerenteTienda();
        prueba.setNombre("Orlando Sepulveda");
        when(gerenteTiendaRepository.findById(1)).thenReturn(Optional.of(prueba));
        Model_GerenteTienda result = gerenteTiendaRepository.findById(1).get();
        assertNotNull(result);
        assertEquals("Orlando Sepulveda", result.getNombre());
    }

    @Test
    @DisplayName("Test controller")
    void testController(){
        when(gerenteTiendaService.listarGerenteTienda()).thenReturn("Lista completa");
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
        Model_GerenteTienda prueba = new Model_GerenteTienda();
        prueba.setNombre("Orlando Sepulveda");
        when(gerenteTiendaRepository.findById(1)).thenReturn(Optional.of(prueba));
        Model_GerenteTienda result = gerenteTiendaRepository.findById(1).get();
        assertNotNull(result);
        result.setNombre("Orlandios Sepulveda");
        when(gerenteTiendaRepository.save(result)).thenReturn(result);
        when(gerenteTiendaRepository.findById(1)).thenReturn(Optional.of(result));
        Model_GerenteTienda gerenteTiendaActualizado = gerenteTiendaRepository.findById(1).get();
        assertEquals("Orlandios Sepulveda", gerenteTiendaActualizado.getNombre());
    }
}
