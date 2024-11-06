package com.ssvs.seguro_salud_vida_sana.models;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
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
@Table(name = "medicoEspecialidad")
public class MedicoEspecialidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relacion MedicoEspecialidad con Medico n a 1
    @ManyToOne()  // O CascadeType.REMOVE si solo quieres eliminar la relación
    @JoinColumn(name = "medico_id")
    @JsonIgnoreProperties("medicoEspecialidades")
    private Medico medico;
    // Relacion MedicoEspecialidad con Especialidad n a 1
    @ManyToOne()  // O CascadeType.REMOVE si solo quieres eliminar la relación
    @JoinColumn(name = "especialidad_id")
    @JsonIgnoreProperties("medicoEspecialidades")
    private Especialidad especialidad;
    // Relacion MedicoEspecialidad con Horario 1 a n
    @OneToMany(mappedBy = "medicoEspecialidad", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Horario> horarios;
    // Constructor por defecto 
    public MedicoEspecialidad() {
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public Set<Horario> getHorarios() {
        return horarios;
    }
    
    public void setHorarios(Set<Horario> horarios) {
        this.horarios = horarios;
    }
}
