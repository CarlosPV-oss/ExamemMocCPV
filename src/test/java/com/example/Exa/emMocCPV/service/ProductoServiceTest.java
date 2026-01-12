package com.example.Exa.emMocCPV.service;

import com.example.Exa.emMocCPV.entity.Producto;
import com.example.Exa.emMocCPV.repository.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoServiceImpl productoService;

    @Test
    void findProductoTest() {
        // Preparar datos de prueba
        Producto productoEsperado = new Producto();
        productoEsperado.setId(1L);
        productoEsperado.setNombre("Laptop");
        productoEsperado.setPrecio(999.99f);
        productoEsperado.setCategoria("Electrónica");

        // Configurar el mock
        Mockito.when(productoRepository.findById(1L))
                .thenReturn(Optional.of(productoEsperado));

        // Ejecutar el método
        Optional<Producto> resultado = productoService.findProducto(1L);

        // Verificar resultados
        assertTrue(resultado.isPresent());
        assertEquals(productoEsperado.getNombre(), resultado.get().getNombre());

        // Verificar llamada al repositorio
        Mockito.verify(productoRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    void findAllProductosTest() {
        List<Producto> productosEsperados = new ArrayList<>();

        Producto producto1 = new Producto();
        producto1.setId(1L);
        producto1.setNombre("Laptop");
        producto1.setPrecio(999.99f);

        productosEsperados.add(producto1);

        Mockito.when(productoRepository.findAll()).thenReturn(productosEsperados);

        List<Producto> resultado = productoService.findAllProductos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());

        Mockito.verify(productoRepository, Mockito.times(1)).findAll();
    }

    @Test
    void addProductoTest() {
        Producto productoNuevo = new Producto();
        productoNuevo.setNombre("Tablet");
        productoNuevo.setPrecio(350.00f);

        Producto productoGuardado = new Producto();
        productoGuardado.setId(1L);
        productoGuardado.setNombre("Tablet");
        productoGuardado.setPrecio(350.00f);
        productoGuardado.setFechaCreacion(LocalDate.now());

        Mockito.when(productoRepository.save(Mockito.any(Producto.class)))
                .thenReturn(productoGuardado);

        Producto resultado = productoService.addProducto(productoNuevo);

        assertNotNull(resultado);
        assertEquals("Tablet", resultado.getNombre());

        Mockito.verify(productoRepository, Mockito.times(1))
                .save(Mockito.any(Producto.class));
    }

    @Test
    void eliminarProductoByIdTest() {
        Mockito.doNothing().when(productoRepository).deleteById(1L);

        productoService.eliminarProductoById(1L);

        Mockito.verify(productoRepository, Mockito.times(1)).deleteById(1L);
    }
}
