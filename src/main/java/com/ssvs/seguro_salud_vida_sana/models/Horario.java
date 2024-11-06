package com.ssvs.seguro_salud_vida_sana.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "horario")
public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "hora_inicio")
    private LocalTime horaInicio;

    @Column(name = "hora_final")
    private LocalTime horaFinal;

    // Relacion Horario con MedicoEspecialidad n a 1
    @ManyToOne()  // O CascadeType.REMOVE si solo quieres eliminar la relación
    @JoinColumn(name = "medico_especialidad_id")
    @JsonIgnoreProperties("horarios")
    private MedicoEspecialidad medicoEspecialidad;
    // Relacion Horario con Cupo 1 a n
    @OneToMany(mappedBy = "horario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Cupo> cupos;
    // Constructor por defecto
    public Horario() {
    }

    // Constructor con parámetros
    public Horario(LocalDate fecha, LocalTime horaInicio, LocalTime horaFinal) {
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(LocalTime horaFinal) {
        this.horaFinal = horaFinal;
    }

    public MedicoEspecialidad getMedicoEspecialidad() {
        return medicoEspecialidad;
    }

    public void setMedicoEspecialidad(MedicoEspecialidad medicoEspecialidad) {
        this.medicoEspecialidad = medicoEspecialidad;
    }

    public Set<Cupo> getCupos() {
        return cupos;
    }
    
    public void setCupos(Set<Cupo> cupos) {
        this.cupos = cupos;
    }
}
