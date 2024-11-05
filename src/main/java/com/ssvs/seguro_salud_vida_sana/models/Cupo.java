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
@Table(name = "cupo")
public class Cupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero")
    private int numero;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "estado")
    private String estado;

    // Relacion Cupo con Horario n a 1
    @ManyToOne()  // O CascadeType.REMOVE si solo quieres eliminar la relación
    @JoinColumn(name = "horario_id")
    @JsonIgnoreProperties("cupos")
    private Horario horario;

    // Relacion Cupo con Asegurado n a 1
    @ManyToOne()  // O CascadeType.REMOVE si solo quieres eliminar la relación
    @JoinColumn(name = "asegurado_id")
    @JsonIgnoreProperties("cupos")
    private Asegurado asegurado;

    // Constructor por defecto
    public Cupo() {
    }

    // Constructor con parámetros
    public Cupo(int numero, LocalDate fecha, String estado) {
        this.numero = numero;
        this.fecha = fecha;
        this.estado = estado;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String isEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public Asegurado getAsegurado() {
        return asegurado;
    }

    public void setAsegurado(Asegurado asegurado) {
        this.asegurado = asegurado;
    }
}
