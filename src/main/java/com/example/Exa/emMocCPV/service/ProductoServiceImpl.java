package com.example.Exa.emMocCPV.service;

import com.example.Exa.emMocCPV.entity.Producto;
import com.example.Exa.emMocCPV.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<Producto> findAllProductos() {
        return this.productoRepository.findAll();
    }

    @Override
    public Optional<Producto> findProducto(Long id) {
        return this.productoRepository.findById(id);
    }

    @Override
    public List<Producto> findByCategoria(String categoria) {
        return this.productoRepository.findByCategoria(categoria);
    }

    @Override
    public List<Producto> findByPrecio(float precio) {
        return this.productoRepository.findByPrecio(precio);
    }

    @Override
    public List<Producto> findByPrecioAndCategoria(float precio, String categoria) {
        return this.productoRepository.findByPrecioAndCategoria(precio, categoria);
    }

    @Override
    public Producto addProducto(Producto producto) {
        // Establece la fecha de creación automáticamente si no viene
        if (producto.getFechaCreacion() == null) {
            producto.setFechaCreacion(LocalDate.now());
        }
        return this.productoRepository.save(producto);
    }

    @Override
    public void eliminarProductoById(Long productoId) {
        this.productoRepository.deleteById(productoId);
    }

    @Override
    public Producto modificarProducto(Long productoId, Producto producto) {
        Optional<Producto> productoBbdd = this.productoRepository.findById(productoId);
        if (productoBbdd.isPresent()) {
            producto.setId(productoId);
            // Mantiene la fecha de creación original
            producto.setFechaCreacion(productoBbdd.get().getFechaCreacion());
            return this.productoRepository.save(producto);
        }
        return null;
    }
}
