package com.example.EcoMarket;


import com.example.EcoMarket.Model.Model_Administrador;
import com.example.EcoMarket.Model.Model_Producto;
import com.example.EcoMarket.Repository.AdministradorRepository;
import com.example.EcoMarket.Service.AdministradorService;
import com.example.EcoMarket.Controller.AdministradorController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ExampleAdministradorTest {
    @MockBean
    AdministradorRepository administradorRepository;

    @Autowired
    MockMvc mockMvc;
    @MockBean
    private AdministradorService administradorServiceMock;

    @Test
    @DisplayName("FindAll Test")
    void testAdministradorServiceMock() {
        List<Model_Administrador> administradores = administradorRepository.findAll();
        assertNotNull(administradores);
        assertEquals(6, administradores.size());
    }

    @Test
    @DisplayName("Rectificar nombre administrador")
    void testFindAdministrador() {
        Model_Administrador prueba = administradorRepository.findById(1).get();
        assertNotNull(prueba);
        assertEquals("Limon", prueba.getNombre());
    }

    //test de controller para que sea similar al anterior con catch, etc pero que sirva para HATEOAS
    @Test
    @DisplayName("Test controller HATEOAS ")
    void testControllerHateoas() {
        try{
            mockMvc.perform(get("/administradores"))
                    .andExpect(status().isOk());

        }catch(Exception ex){
            System.out.println(ex.getMessage());
            fail();
        }
    }

    @Test
    void testObtenerPorId(){
        Model_Administrador admin = new Model_Administrador(22,"Carlos","carlosabarzua@gmail.com","pass1234","admin");
        when(administradorServiceMock.obtenerPorId(22)).thenReturn(admin);
        Model_Administrador resultadoadmin = administradorServiceMock.obtenerPorId(22);
        assertEquals("Carlos", resultadoadmin.getNombre());
    }

    @Test
    @DisplayName("Actualizar nombre admin")
    void testUpdateAministradorName() {
        Model_Administrador administrador = new Model_Administrador(1, "AntiguoNombre", "admin@mail.com", "pass", "admin");
        when(administradorRepository.findById(1)).thenReturn(Optional.of(administrador));
        when(administradorRepository.save(administrador)).thenReturn(administrador);

        // Actualizar el nombre del administrador
        administrador.setNombre("Limon");
        administradorRepository.save(administrador);

        // Recuperar el administrador actualizado
        when(administradorRepository.findById(1)).thenReturn(Optional.of(administrador));
        Model_Administrador administradorActualizado = administradorRepository.findById(1).get();

        assertEquals("Limon", administradorActualizado.getNombre());
    }
    
    @Test
    @DisplayName("Lanzar excepción si administrador no existe")
    void testAdministradorNoExiste() {
        when(administradorServiceMock.obtenerPorId(99))
                .thenThrow(new RuntimeException("Administrador no encontrado con ID: 99"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            administradorServiceMock.obtenerPorId(99);
        });
        assertEquals("Administrador no encontrado con ID: 99", exception.getMessage());
    }

}
