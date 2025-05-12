package com.jhoel.api_alumnos;

import jakarta.persistence.*;
@Entity
public class Alumnos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID del alumno, generado automáticamente por la base de datos

    private String nombre; // Nombre del alumno

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}