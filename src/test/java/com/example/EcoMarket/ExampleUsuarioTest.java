package com.example.EcoMarket;

import com.example.EcoMarket.Model.Model_Usuario;
import com.example.EcoMarket.Repository.UsuarioRepository;
import com.example.EcoMarket.Service.UsuarioService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ExampleUsuarioTest {

    @MockBean
    private UsuarioRepository usuarioRepository;

    @MockBean
    private UsuarioService usuarioService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("FindAll Test")
    void testUsuarioServiceMock() {
        Model_Usuario usuario = new Model_Usuario();
        when(usuarioRepository.findAll()).thenReturn(Arrays.asList(usuario, usuario, usuario, usuario, usuario, usuario));
        List<Model_Usuario> usuarios = usuarioRepository.findAll();
        assertNotNull(usuarios);
        assertEquals(6, usuarios.size());
    }

    @Test
    @DisplayName("Rectificar nombre usuario")
    void testFindUsuario() {
        Model_Usuario prueba = new Model_Usuario();
        prueba.setNombre("Usuario 1");
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(prueba));
        Model_Usuario result = usuarioRepository.findById(1).get();
        assertNotNull(result);
        assertEquals("Usuario 1", result.getNombre());
    }

    @Test
    @DisplayName("Test controller de usuario")
    void testController() {
        Model_Usuario usuario = new Model_Usuario();
        usuario.setId(1); // Asigna un ID válido
        usuario.setNombre("Usuario 1");
        when(usuarioService.obtenerTodos()).thenReturn(Arrays.asList(usuario));
        try {
            mockMvc.perform(get("/usuarios"))
                    .andExpect(status().isOk());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            fail();
        }
    }

    @Test
    @DisplayName("Actualizar nombre usuario")
    void testUpdateUsuarioName() {
        Model_Usuario usuario = new Model_Usuario();
        usuario.setNombre("Usuario 1");
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        // Simula actualización
        Model_Usuario usuarioActualizado = usuarioRepository.findById(1).get();
        usuarioActualizado.setNombre("Usuario_1");
        usuarioRepository.save(usuarioActualizado);

        // Verifica actualización
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuarioActualizado));
        Model_Usuario result = usuarioRepository.findById(1).get();
        assertEquals("Usuario_1", result.getNombre());
    }
}
