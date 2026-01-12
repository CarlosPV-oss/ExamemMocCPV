package com.example.Exa.emMocCPV.service;

import com.example.Exa.emMocCPV.entity.Producto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductoService {

    List<Producto> findAllProductos();
    Optional<Producto> findProducto(Long id);
    List<Producto> findByCategoria(String categoria);
    List<Producto> findByPrecio(float precio);

    List<Producto> findByPrecioAndCategoria(float precio, String categoria);

    Producto addProducto(Producto producto);
    void eliminarProductoById(Long productoId);


    Producto modificarProducto(Long productoId, Producto producto);
}
