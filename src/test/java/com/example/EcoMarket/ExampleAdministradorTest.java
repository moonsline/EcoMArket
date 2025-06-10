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

    @Test
    @DisplayName("Test controller")
    void testController() {
        //Indicamos que el retorno de listarAdministradores se identificara con el valor ingresado en thenReturn
        when(administradorServiceMock.listaAdminstrador()).thenReturn("Lista completa");

        //Bloque Try Except/Catch
        //Nos permite probar una funcionalidad de codigo o un segmento de codigo y si este falla
        //Se captura por medio de Catch(Exception var) y ejecuta un control de error
        try {
            //MockMvc Nos permite realizar consultas HTTPMethod
            //perform nos permite ejecutar dichas llamadas y luego ingresamos el metodo HTTP correspondiente
            //adicionalmente podemos agregar parametros u variables adicionales de ser requerido
            //Finalmente andExcept nos permite indicar que esperamos de dicha respuesta HTTP
            //Tanto codigo como contenido
            mockMvc.perform(get("/administradores"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("Lista completa"));

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            fail();
        }
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
