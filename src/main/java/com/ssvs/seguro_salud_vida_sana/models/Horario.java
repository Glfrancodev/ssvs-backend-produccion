package com.ssvs.seguro_salud_vida_sana.models;

import java.time.LocalDate;
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

    @Column(name = "dia")
    private String dia;

    @Column(name = "hora_inicio")
    private LocalDate horaInicio;

    @Column(name = "hora_final")
    private LocalDate horaFinal;

    @Column(name = "estado")
    private boolean estado;

    // Relacion Horario con Medico n a 1
    @ManyToOne()  // O CascadeType.REMOVE si solo quieres eliminar la relación
    @JoinColumn(name = "medico_id")
    @JsonIgnoreProperties("horarios")
    private Medico medico;
    // Relacion Horario con Cupo 1 a n
    @OneToMany(mappedBy = "horario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Cupo> cupos;
    // Constructor por defecto
    public Horario() {
    }

    // Constructor con parámetros
    public Horario(String dia, LocalDate horaInicio, LocalDate horaFinal, boolean estado) {
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
        this.estado = estado;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public LocalDate getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalDate horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalDate getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(LocalDate horaFinal) {
        this.horaFinal = horaFinal;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Set<Cupo> getCupos() {
        return cupos;
    }
    
    public void setCupos(Set<Cupo> cupos) {
        this.cupos = cupos;
    }
}
