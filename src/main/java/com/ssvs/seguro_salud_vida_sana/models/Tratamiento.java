package com.ssvs.seguro_salud_vida_sana.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tratamiento")
public class Tratamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha")
    private LocalDate fecha;

    // Relacion Tratamiento con Consulta 1 a 1
    @OneToOne
    @JoinColumn(name = "consulta_id")
    @JsonIgnoreProperties({"tratamiento"})
    private Consulta consulta;

    // Relacion Tratamiento con Receta 1 a n
    @OneToMany(mappedBy = "tratamiento", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Receta> recetas;

    // Constructor por defecto
    public Tratamiento() {
    }

    // Constructor con parámetros
    public Tratamiento(LocalDate fecha) {
        this.fecha = fecha;
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

    public Consulta getConsulta(){
        return consulta;
    }

    public void setConsulta(Consulta consulta){
        this.consulta = consulta;
    }
    
    public Set<Receta> getRecetas() {
        return recetas;
    }
    
    public void setRecetas(Set<Receta> recetas) {
        this.recetas = recetas;
    }
}
