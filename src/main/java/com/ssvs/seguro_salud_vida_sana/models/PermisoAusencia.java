package com.ssvs.seguro_salud_vida_sana.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "permisoAusencia")
public class PermisoAusencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_permiso")
    private LocalDate fechaPermiso;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "estado")
    private String estado = "Revisión";

    // Relacion PermisoAusencia con Medico n a 1
    @ManyToOne
    @JoinColumn(name = "medico_id")
    @JsonIgnoreProperties("permisoAusencias")
    private Medico medico;

    // Constructor por defecto
    public PermisoAusencia() {
    }

    // Constructor con parámetros
    public PermisoAusencia(LocalDate fechaPermiso, String descripcion, String estado) {
        this.fechaPermiso = fechaPermiso;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaPermiso() {
        return fechaPermiso;
    }

    public void setFechaPermiso(LocalDate fechaPermiso) {
        this.fechaPermiso = fechaPermiso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Medico getMedico(){
        return medico;
    }
    public void setMedico(Medico medico){
        this.medico = medico;
    }
}
