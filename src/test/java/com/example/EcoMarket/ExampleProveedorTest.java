package com.example.EcoMarket;

import com.example.EcoMarket.Model.Model_Proveedor;
import com.example.EcoMarket.Repository.ProveedorRepository;
import com.example.EcoMarket.Service.ProveedorService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExampleProveedorTest {
    @Mock
    ProveedorRepository proveedorRepository;

    @InjectMocks
    ProveedorService proveedorService;

    @Test
    @DisplayName("FindAll test con mock")
    void testProveedorServiceeMock() {
        Model_Proveedor proveedor = new Model_Proveedor();
        proveedor.setId(1);
        proveedor.setNombre("Distribuidora Villalobos");
        when(proveedorRepository.findAll()).thenReturn(List.of(proveedor));

        List<Model_Proveedor> proveedores = proveedorService.obtenerTodos();
        assertNotNull(proveedores);
        assertEquals(1, proveedores.size());
    }

    @Test
    @DisplayName("Rectificar nombre proveedor")
    void testProvedor() {
        Model_Proveedor proveedor = new Model_Proveedor();
        proveedor.setId(1);
        proveedor.setNombre("Distribuidora Villalobos");
        when(proveedorRepository.findById(1)).thenReturn(Optional.of(proveedor));

        Model_Proveedor prueba = proveedorService.obtenerPorId(1);
        assertNotNull(prueba);
        assertEquals("Distribuidora Villalobos", prueba.getNombre());
    }

    @Test
    @DisplayName("Actualizar contacto proveedor")
    void testUpdateProveedorContacto() {
        Model_Proveedor proveedor = new Model_Proveedor();
        proveedor.setId(1);
        proveedor.setContacto("Abarzuafriend@correo.cl");
        when(proveedorRepository.findById(1)).thenReturn(Optional.of(proveedor));
        when(proveedorRepository.save(proveedor)).thenReturn(proveedor);

        proveedor.setContacto("nuevo@correo.cl");
        proveedorRepository.save(proveedor);
        when(proveedorRepository.findById(1)).thenReturn(Optional.of(proveedor));
        Model_Proveedor proveedorActualizado = proveedorService.obtenerPorId(1);
        assertEquals("nuevo@correo.cl", proveedorActualizado.getContacto());
    }
    @Test
    @DisplayName("Lanzar excepción si proveedor no existe")
    void testProveedorNoExiste() {
        when(proveedorRepository.findById(99)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            proveedorService.obtenerPorId(99);
        });
        assertEquals("Proveedor no encontrado con ID: 99", exception.getMessage());
    }
}
