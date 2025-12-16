package com.example.EcoMarket;

import com.example.EcoMarket.Model.Model_EmpleadoVentas;
import com.example.EcoMarket.Repository.EmpleadoVentasRepository;
import com.example.EcoMarket.Service.EmpleadoVentasService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ExampleEmpleadoVentasTest {

    @MockBean
    private EmpleadoVentasRepository empleadoVentasRepository;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmpleadoVentasService empleadoVentasServiceMock;

    @Test
    @DisplayName("FindAll test")
    void testEmpleadoVentasServiceMock() {
        List<Model_EmpleadoVentas> empleados = List.of(
                new Model_EmpleadoVentas(1, "Juan Python", "juan@mail.com", "pass", "ventas", "direccion 1"),
                new Model_EmpleadoVentas(2, "Ana Java", "ana@mail.com", "pass", "ventas", "direccion 2"),
                new Model_EmpleadoVentas(3, "Luis C#", "luis@mail.com", "pass", "ventas", "direccion 3"),
                new Model_EmpleadoVentas(4, "Maria JS", "maria@mail.com", "pass", "ventas", "direccion 4")
        );
        when(empleadoVentasRepository.findAll()).thenReturn(empleados);

        List<Model_EmpleadoVentas> empleadoVentas = empleadoVentasRepository.findAll();
        assertNotNull(empleadoVentas);
        assertEquals(4, empleadoVentas.size());
    }

    @Test
    @DisplayName("Rectificar nombre empleadoVentas")
    void testFindEmpleadoVentas() {
        Model_EmpleadoVentas empleado = new Model_EmpleadoVentas(1, "Juan Python", "juan@mail.com", "pass", "ventas", "direccion 1");
        when(empleadoVentasRepository.findById(1)).thenReturn(Optional.of(empleado));

        Model_EmpleadoVentas prueba = empleadoVentasRepository.findById(1).get();
        assertNotNull(prueba);
        assertEquals("Juan Python", prueba.getNombre());
    }

    @Test
    @DisplayName("Test controller - GET /empleados-ventas")
    void testControllerGetAll() {
        try {
            mockMvc.perform(get("/empleados-ventas"))
                    .andExpect(status().isOk());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            fail();
        }
    }

    @Test
    @DisplayName("Actualizar nombre empleadoVentas")
    void testUpdateEmpleadoVentas() {
        Model_EmpleadoVentas empleado = new Model_EmpleadoVentas(1, "Juan Python", "juan@mail.com", "pass", "ventas", "direccion 1");
        when(empleadoVentasRepository.findById(1)).thenReturn(Optional.of(empleado));

        Model_EmpleadoVentas prueba = empleadoVentasRepository.findById(1).get();
        assertNotNull(prueba);
        prueba.setNombre("Jose Python");

        // Simula el guardado y la búsqueda posterior
        when(empleadoVentasRepository.save(prueba)).thenReturn(prueba);
        when(empleadoVentasRepository.findById(1)).thenReturn(Optional.of(prueba));

        empleadoVentasRepository.save(prueba);
        Model_EmpleadoVentas empleadoVentasActualizado = empleadoVentasRepository.findById(1).get();
        assertEquals("Jose Python", empleadoVentasActualizado.getNombre());
    }
}
