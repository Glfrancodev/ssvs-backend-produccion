package com.ssvs.seguro_salud_vida_sana.models;

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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "consulta")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_consulta")
    private LocalDate fechaConsulta;

    @Column(name = "motivo_consulta")
    private String motivoConsulta;

    @Column(name = "diagnostico")
    private String diagnostico;

    @Column(name = "notas")
    private String nota;

    // Relacion Consulta con Cupo 1 a 1
    @OneToOne
    @JoinColumn(name = "cupo_id")
    @JsonIgnoreProperties({"consulta"})
    private Cupo cupo;
    
    // Relacion Consulta con HistoriaClinica n a 1
    @ManyToOne()  // O CascadeType.REMOVE si solo quieres eliminar la relación
    @JoinColumn(name = "historia_clinica_id")
    @JsonIgnoreProperties({"consultas"})
    private HistoriaClinica historiaClinica;

    // Relacion Consulta con Tratamiento 1 a 1
    @OneToOne(mappedBy = "consulta", fetch = FetchType.LAZY)
    @JsonIgnore
    private Tratamiento tratamiento;

    // Relacion Consulta con Archivo 1 a n
    @OneToMany(mappedBy = "consulta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Archivo> archivos;

    // Constructor por defecto
    public Consulta() {
    }

    // Constructor con parámetros
    public Consulta(LocalDate fechaConsulta, String motivoConsulta, String diagnostico, String nota) {
        this.fechaConsulta = fechaConsulta;
        this.motivoConsulta = motivoConsulta;
        this.diagnostico = diagnostico;
        this.nota = nota;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaConsulta() {
        return fechaConsulta;
    }

    public void setFechaConsulta(LocalDate fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }

    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getNota(){
        return nota;
    }

    public void setNota(String nota){
        this.nota = nota;
    }

    public HistoriaClinica getHistoriaClinica(){
        return historiaClinica;
    }

    public void setHistoriaClinica(HistoriaClinica historiaClinica){
        this.historiaClinica = historiaClinica;
    }

    public Cupo getCupo(){
        return cupo;
    }

    public void setCupo(Cupo cupo){
        this.cupo = cupo;
    }

    public Tratamiento getTratamiento(){
        return tratamiento;
    }

    public void setTratamiento(Tratamiento tratamiento){
        this.tratamiento = tratamiento;
    }

    public Set<Archivo> getArchivos() {
        return archivos;
    }
    
    public void setArchivos(Set<Archivo> archivos) {
        this.archivos = archivos;
    }

}
