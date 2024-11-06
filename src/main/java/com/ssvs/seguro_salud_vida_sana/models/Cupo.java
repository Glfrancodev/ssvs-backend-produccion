package com.ssvs.seguro_salud_vida_sana.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "cupo")
public class Cupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero")
    private int numero;

    @Column(name = "hora")
    private LocalTime hora;

    @Column(name = "estado")
    private String estado = "Libre";

    // Relacion Cupo con Horario n a 1
    @ManyToOne()  // O CascadeType.REMOVE si solo quieres eliminar la relación
    @JoinColumn(name = "horario_id")
    @JsonIgnoreProperties({"cupos","medicoEspecialidad"})
    private Horario horario;

    // Relacion Cupo con Asegurado n a 1
    @ManyToOne()  // O CascadeType.REMOVE si solo quieres eliminar la relación
    @JoinColumn(name = "asegurado_id")
    @JsonIgnoreProperties({"cupos","usuario"})
    private Asegurado asegurado;

    // Constructor por defecto
    public Cupo() {
    }

    // Constructor con parámetros
    public Cupo(int numero, LocalTime hora, String estado) {
        this.numero = numero;
        this.hora = hora;
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

    public LocalTime gethora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getEstado() {
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
