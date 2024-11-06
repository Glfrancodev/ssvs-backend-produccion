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
@Table(name = "receta")
public class Receta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "medicamento")
    private String medicamento;

    @Column(name = "frecuencia")
    private String frecuencia;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_final")
    private LocalDate fechaFinal;

    // Relacion Receta con Tratamiento n a 1
    // Relacion Consulta con HistoriaClinica n a 1
    @ManyToOne()  // O CascadeType.REMOVE si solo quieres eliminar la relación
    @JoinColumn(name = "tratamiento_id")
    @JsonIgnoreProperties({"receta"})
    private Tratamiento tratamiento;

    // Constructor por defecto
    public Receta() {
    }

    // Constructor con parámetros
    public Receta(String medicamento, String frecuencia, LocalDate fechaInicio, LocalDate fechaFinal) {
        this.medicamento = medicamento;
        this.frecuencia = frecuencia;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMedicamento(){
        return medicamento;
    }

    public void setMedicamento(String medicamento){
        this.medicamento = medicamento;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(LocalDate fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public Tratamiento getTratamiento(){
        return tratamiento;
    }

    public void setTratamiento(Tratamiento tratamiento){
        this.tratamiento = tratamiento;
    }
}
