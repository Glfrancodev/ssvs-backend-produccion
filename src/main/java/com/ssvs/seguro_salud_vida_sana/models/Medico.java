package com.ssvs.seguro_salud_vida_sana.models;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "medico")
public class Medico {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "item")
  private String item;

  // Relacion Medico con Usuario 1 a 1
  @OneToOne
  @JoinColumn(name = "usuario_id")
  @JsonIgnoreProperties({"medico","rol"})
  private Usuario usuario;

  // Relacion Medico con PermisoAusencia 1 a n
  @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JsonIgnore
  private Set<PermisoAusencia> permisoAusencias;

  // Relacion Medico con MedicoEspecialidad 1 a n
  @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JsonIgnore
  private Set<MedicoEspecialidad> medicoEspecialidades;

  // Relacion Medico con Notificacion 1 a n
  @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JsonIgnore
  private Set<Notificacion> notificaciones;

  // Constructor por defecto
  public Medico() {
  }

  // Constructor con parámetros
  public Medico(String item) {
    this.item = item;
  }

  // Getters y Setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getItem() {
    return item;
  }

  public void setItem(String item) {
    this.item = item;
  }
  public Usuario getUsuario(){
    return usuario;
  }
  public void setUsuario(Usuario usuario){
    this.usuario = usuario;
  }

  public Set<PermisoAusencia> getPermisoAusencias() {
    return permisoAusencias;
  }

  public void setPermisoAusencias(Set<PermisoAusencia> permisoAusencias) {
    this.permisoAusencias = permisoAusencias;
  }

  public Set<MedicoEspecialidad> getMedicoEspecialidades() {
    return medicoEspecialidades;
  }

  public void setMedicoEspecialidades(Set<MedicoEspecialidad> medicoEspecialidades) {
    this.medicoEspecialidades = medicoEspecialidades;
  }

  public Set<Notificacion> getNotificaciones() {
    return notificaciones;
  }

  public void setNotificaciones(Set<Notificacion> notificaciones) {
    this.notificaciones = notificaciones;
  }

}
