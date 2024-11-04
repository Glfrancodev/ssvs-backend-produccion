package com.ssvs.seguro_salud_vida_sana.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "rolPermiso")
public class RolPermiso {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne()  // O CascadeType.REMOVE si solo quieres eliminar la relación
  @JoinColumn(name = "rol_id")
  @JsonIgnoreProperties("rolPermisos")
  private Rol rol;
  
  @ManyToOne()
  @JoinColumn(name = "permiso_id")
  @JsonIgnoreProperties("rolPermisos")
  private Permiso permiso;

  // Constructor por defecto
  public RolPermiso() {
  }

  // Getters y Setters
  public Long getId() {
    return id;
  }
  
  public void setId(Long id) {
    this.id = id;
  }

  public Rol getRol() {
    return rol;
  }
  public void setRol(Rol rol) {
    this.rol = rol;
  }
  
  public Permiso getPermiso() {
    return permiso;
  }
  public void setPermiso(Permiso permiso) {
    this.permiso = permiso;
  }
}
