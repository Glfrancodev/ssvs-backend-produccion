package com.ssvs.seguro_salud_vida_sana.models;

import jakarta.persistence.*;

@Entity
@Table(name = "calificacion")
public class Calificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "estrella", nullable = false)
    private int estrella;

    // Constructor por defecto
    public Calificacion() {
    }

    // Constructor con parámetros
    public Calificacion(int estrella) {
        this.estrella = estrella;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getEstrella() {
        return estrella;
    }

    public void setEstrella(int estrella) {
        this.estrella = estrella;
    }
}
