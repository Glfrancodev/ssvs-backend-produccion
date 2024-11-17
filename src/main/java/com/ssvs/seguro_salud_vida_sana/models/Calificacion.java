package com.ssvs.seguro_salud_vida_sana.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@Table(name = "calificacion")
public class Calificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "estrella", nullable = false)
    private int estrella;

    // Relacion Calificacion con Asegurado n a 1
    @ManyToOne()
    @JoinColumn(name = "asegurado_id")
    @JsonIgnoreProperties("calificaciones")
    private Asegurado asegurado;

    // Relacion Calificacion con Medico n a 1
    @ManyToOne()
    @JoinColumn(name = "medico_id")
    @JsonIgnoreProperties("calificaciones")
    private Medico medico;

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

    public Asegurado getAsegurado() {
        return asegurado;
    }

    public void setAsegurado(Asegurado asegurado) {
        this.asegurado = asegurado;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

}
