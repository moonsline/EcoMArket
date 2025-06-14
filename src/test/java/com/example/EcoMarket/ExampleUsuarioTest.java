package com.example.EcoMarket;


import com.example.EcoMarket.Model.Model_Administrador;
import com.example.EcoMarket.Model.Model_Cliente;
import com.example.EcoMarket.Model.Model_Usuario;
import com.example.EcoMarket.Repository.UsuarioRepository;
import com.example.EcoMarket.Service.UsuarioService;
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
public class ExampleUsuarioTest {
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    private UsuarioService usuarioServiceMock;

    @Test
    @DisplayName("FindAll Test")
    void testUsuarioServiceMock() {
        List<Model_Usuario> usuarios = usuarioRepository.findAll();
        assertNotNull(usuarios);
        assertEquals(6, usuarios.size());
    }

    @Test
    @DisplayName("Rectificar nombre usuario")
    void testFindUsuario() {
        Model_Usuario prueba = usuarioRepository.findById(1).get();
        assertNotNull(prueba);
        assertEquals("Usuario 1", prueba.getNombre());
    }

    @Test
    @DisplayName("Test controller de usuario")
    void testController() {
        when(usuarioServiceMock.listarUsuarios()).thenReturn("Lista de usuarios completa");

        try {
            mockMvc.perform(get("/usuarios"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("Lista de usuarios completa"));

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            fail();
        }
    }

    @Test
    @DisplayName("Actualizar nombre usuario")
    void testUpdateUsuarioName() {
        Model_Usuario usuario = usuarioRepository.findById(1).get();
        assertNotNull(usuario);

        usuario.setNombre("Usuario_1");
        usuarioRepository.save(usuario);

        Model_Usuario usuarioActualizado = usuarioRepository.findById(1).get();
        assertEquals("Usuario_1", usuarioActualizado.getNombre());
    }
}
