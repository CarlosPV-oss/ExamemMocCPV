package com.example.Exa.emMocCPV.repository;

import com.example.Exa.emMocCPV.entity.Producto;

import java.util.List;
import java.util.Optional;

public interface JpaRepository<T, T1> {
    List<Producto> findByPrecio(float precio);

    List<Producto> findByCategoria(String categoria);

    List<Producto> findAll();

    Optional<Producto> findById(Long id);

    List<Producto> findByPrecioAndCategoria(float precio, String categoria);

    Producto save(Producto producto);

    void delete(Producto value);
}
