package com.example.Exa.emMocCPV.controler;

import com.example.Exa.emMocCPV.entity.Producto;
import com.example.Exa.emMocCPV.service.ProductoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductoControllerTest {

    @Mock
    private ProductoService productoService;

    @InjectMocks
    private ProductoController productoController;

    @Test
    void addProducto() {
        // 1. Preparar datos de prueba
        Producto productoNuevo = new Producto();
        productoNuevo.setNombre("Monitor");
        productoNuevo.setPrecio(200.00f);
        productoNuevo.setCategoria("Electrónica");

        Producto productoGuardado = new Producto();
        productoGuardado.setId(1L);
        productoGuardado.setNombre("Monitor");
        productoGuardado.setPrecio(200.00f);
        productoGuardado.setCategoria("Electrónica");

        // 2. Configurar el mock
        Mockito.when(productoService.addProducto(Mockito.any(Producto.class)))
                .thenReturn(productoGuardado);

        // 3. Ejecutar el método
        Producto resultado = productoController.addProducto(productoNuevo);

        // 4. Verificar resultados
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Monitor", resultado.getNombre());

        // 5. Verificar que se llamó al servicio
        Mockito.verify(productoService, Mockito.times(1))
                .addProducto(Mockito.any(Producto.class));
    }

    @Test
    void deleteProducto() {
        // 1. Preparar datos de prueba
        Long productoId = 1L;

        // 2. Configurar el mock
        Mockito.doNothing().when(productoService).eliminarProductoById(productoId);

        // 3. Ejecutar el método
        productoController.deleteProducto(productoId);

        // 4. Verificar que se llamó al servicio
        Mockito.verify(productoService, Mockito.times(1)).eliminarProductoById(productoId);
    }

    @Test
    void modificarProducto() {
        // 1. Preparar datos de prueba
        Long productoId = 1L;

        Producto productoActualizado = new Producto();
        productoActualizado.setNombre("Laptop Actualizada");
        productoActualizado.setPrecio(1500.00f);
        productoActualizado.setCategoria("Electrónica");

        Producto productoModificado = new Producto();
        productoModificado.setId(productoId);
        productoModificado.setNombre("Laptop Actualizada");
        productoModificado.setPrecio(1500.00f);
        productoModificado.setCategoria("Electrónica");

        // 2. Configurar el mock
        Mockito.when(productoService.modificarProducto(Mockito.eq(productoId), Mockito.any(Producto.class)))
                .thenReturn(productoModificado);

        // 3. Ejecutar el método
        Producto resultado = productoController.modificarProducto(productoId, productoActualizado);

        // 4. Verificar resultados
        assertNotNull(resultado);
        assertEquals(productoId, resultado.getId());
        assertEquals("Laptop Actualizada", resultado.getNombre());
        assertEquals(1500.00f, resultado.getPrecio());

        // 5. Verificar que se llamó al servicio
        Mockito.verify(productoService, Mockito.times(1))
                .modificarProducto(Mockito.eq(productoId), Mockito.any(Producto.class));
    }

    @Test
    void getProductos() {
        // 1. Preparar datos de prueba
        List<Producto> productosEsperados = new ArrayList<>();

        Producto producto1 = new Producto();
        producto1.setId(1L);
        producto1.setNombre("Teclado");
        producto1.setPrecio(75.00f);
        producto1.setCategoria("Accesorios");

        Producto producto2 = new Producto();
        producto2.setId(2L);
        producto2.setNombre("Mouse");
        producto2.setPrecio(25.50f);
        producto2.setCategoria("Accesorios");

        productosEsperados.add(producto1);
        productosEsperados.add(producto2);

        // 2. Configurar el mock
        Mockito.when(productoService.findAllProductos())
                .thenReturn(productosEsperados);

        // 3. Ejecutar el método (sin filtros)
        List<Producto> resultado = productoController.getProductos(0.0f, "");

        // 4. Verificar resultados
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Teclado", resultado.get(0).getNombre());
        assertEquals("Mouse", resultado.get(1).getNombre());

        // 5. Verificar que se llamó al servicio
        Mockito.verify(productoService, Mockito.times(1)).findAllProductos();
    }

    @Test
    void getProducto() {
        // 1. Preparar datos de prueba
        Long productoId = 1L;

        Producto productoEsperado = new Producto();
        productoEsperado.setId(productoId);
        productoEsperado.setNombre("Mouse Gaming");
        productoEsperado.setPrecio(45.99f);
        productoEsperado.setCategoria("Gaming");

        // 2. Configurar el mock
        Mockito.when(productoService.findProducto(productoId))
                .thenReturn(Optional.of(productoEsperado));

        // 3. Ejecutar el método
        Optional<Producto> resultado = productoController.getProducto(productoId);

        // 4. Verificar resultados
        assertTrue(resultado.isPresent());
        assertEquals(productoId, resultado.get().getId());
        assertEquals("Mouse Gaming", resultado.get().getNombre());
        assertEquals(45.99f, resultado.get().getPrecio());

        // 5. Verificar que se llamó al servicio
        Mockito.verify(productoService, Mockito.times(1)).findProducto(productoId);
    }
}
