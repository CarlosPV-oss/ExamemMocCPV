package com.example.Exa.emMocCPV.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nombre;

    @Column
    private String descripcion;

    @Column
    private String categoria;

    @Column
    private float precio;

    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;

    @Column
    private String observaciones;

    @Column
    private Integer cantidad;

    // Constructor vacío (necesario para JPA)
    public Producto() {
    }

    // ============= GETTERS =============

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public float getPrecio() {
        return precio;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    // ============= SETTERS =============

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
