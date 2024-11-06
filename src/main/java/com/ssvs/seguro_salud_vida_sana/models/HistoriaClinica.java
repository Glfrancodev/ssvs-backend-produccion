package com.ssvs.seguro_salud_vida_sana.models;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "historiaClinica")
public class HistoriaClinica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relacion HistoriaClinica con Asegurado 1 a 1
    @OneToOne(mappedBy = "historiaClinica", fetch = FetchType.LAZY)
    @JsonIgnore
    private Asegurado asegurado;

    // Relacion HistoriaClinica con Consulta 1 a n
    @OneToMany(mappedBy = "historiaClinica", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Consulta> consultas;

    // Constructor por defecto
    public HistoriaClinica() {
    }

    // Constructor con parámetros
    public HistoriaClinica(Long id) {
        this.id = id;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Asegurado getAsegurado(){
        return asegurado;
    }

    public void setAsegurado(Asegurado asegurado){
        this.asegurado = asegurado;
    }

    public Set<Consulta> getConsultas() {
        return consultas;
    }
    
    public void setConsulta(Set<Consulta> consultas) {
        this.consultas = consultas;
    }
}
