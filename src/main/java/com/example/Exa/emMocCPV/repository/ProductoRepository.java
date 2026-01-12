package com.example.Exa.emMocCPV.repository;

import com.example.Exa.emMocCPV.entity.Producto;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {


    List<Producto> findByCategoria(String categoria);

    List<Producto> findByPrecio(float precio);

    List<Producto> findByPrecioAndCategoria(float precio, String categoria);
}
