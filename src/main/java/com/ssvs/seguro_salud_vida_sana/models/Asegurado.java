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
@Table(name = "asegurado")
public class Asegurado {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "tipo_sangre")
  private String tipoSangre;

  @Column(name = "sexo")
  private char sexo;

  @Column(name = "fecha_nacimiento")
  private LocalDate fechaNacimiento;

  // Relacion Asegurado con Usuario 1 a 1
  @OneToOne
  @JoinColumn(name = "usuario_id")
  @JsonIgnoreProperties({"asegurado","rol"})
  private Usuario usuario;

  // Relacion Asegurado con Cupo 1 a n
  @OneToMany(mappedBy = "asegurado", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JsonIgnore
  private Set<Cupo> cupos;

  // Relacion Asegurado con HistoriaClinica 1 a 1
  @OneToOne
  @JoinColumn(name = "historia_clinica_id")
  @JsonIgnoreProperties("asegurado")
  private HistoriaClinica historiaClinica;

  // Constructor por defecto
  public Asegurado() {
  }

  // Constructor con parámetros
  public Asegurado(String tipoSangre, char sexo, LocalDate fechaNacimiento) {
    this.tipoSangre = tipoSangre;
    this.sexo = sexo;
    this.fechaNacimiento = fechaNacimiento;
  }

  // Getters y Setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTipoSangre() {
    return tipoSangre;
  }

  public void setTipoSangre(String tipoSangre) {
    this.tipoSangre = tipoSangre;
  }

  public char getSexo() {
    return sexo;
  }

  public void setSexo(char sexo) {
    this.sexo = sexo;
  }

  public LocalDate getFechaNacimiento() {
    return fechaNacimiento;
  }

  public void setFechaNacimiento(LocalDate fechaNacimiento) {
    this.fechaNacimiento = fechaNacimiento;
  }

  public Usuario getUsuario(){
    return usuario;
  }
  public void setUsuario(Usuario usuario){
    this.usuario = usuario;
  }

  public Set<Cupo> getCupos() {
    return cupos;
  }

  public void setCupos(Set<Cupo> cupos) {
    this.cupos = cupos;
  }

  public HistoriaClinica getHistoriaClinica(){
    return historiaClinica;
  }

  public void setHistoriaClinica(HistoriaClinica historiaClinica){
    this.historiaClinica = historiaClinica;
  }

}
