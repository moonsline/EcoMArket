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
    @Autowired
    AdministradorRepository administradorRepository;

    @Autowired
    MockMvc mockMvc;
    @Autowired
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
        assertEquals("Ariel Silva", prueba.getNombre());
    }

    //test de controller para que sea similar al anterior con catch, etc pero que sirva para HATEOAS
    @Test
    @DisplayName("Test controller HATEOAS ")
    void testControllerHateoas() {
        try{
            mockMvc.perform(get("administradores/"))
                    .andExpect(status().isOk());

        }catch(Exception ex){
            System.out.println(ex.getMessage());
            fail();
        }
    }

    @Test
    void testObtenerPorId(){
        Model_Administrador admin = new Model_Administrador(22,"Carlos","carlosabarzua@gmail.com","pass1234","admin");
        when(administradorRepository.findById(22)).thenReturn(Optional.of(admin));
        Model_Administrador resultadoadmin = administradorServiceMock.obtenerPorId(22);
        assertEquals("Carlos",resultadoadmin.getNombre());
    }

    @Test
    @DisplayName("Actualizar nombre admin")
    void testUpdateAministradorName() {
        // Buscar el producto por su ID
        Model_Administrador administrador = administradorRepository.findById(1).get();

        // Verificar que el producto existe
        assertNotNull(administrador);

        // Actualizar el nombre del producto
        administrador.setNombre("Limon");
        administradorRepository.save(administrador);

        // Recuperar el producto actualizado
        Model_Administrador administradorActualizado = administradorRepository.findById(1).get();

        // Verificar que el nombre se haya actualizado correctamente
        assertEquals("Limon", administradorActualizado.getNombre());
    }

}
