package com.ssvs.seguro_salud_vida_sana.models;

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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "ci")
  private String ci;

  @Column(name = "correo")
  private String correo;

  @Column(name = "contrasena")
  private String contrasena;

  @Column(name = "nombre")
  private String nombre;

  @Column(name = "apellido")
  private String apellido;

  @Column(name = "esta_activo")
  private boolean estaActivo;

  // Relacion Usuario con Rol n a 1
  @ManyToOne
  @JoinColumn(name = "rol_id")
  @JsonIgnoreProperties("usuarios")
  private Rol rol;

  // Relacion Usuario con Asegurado 1 a 1
  @OneToOne(mappedBy = "usuario", fetch = FetchType.LAZY)
  @JsonIgnore
  private Asegurado asegurado;
  // Relacion Usuario con Medico 1 a 1
  @OneToOne(mappedBy = "usuario", fetch = FetchType.LAZY)
  @JsonIgnore
  private Medico medico;

  // Constructor por defecto
  public Usuario() {
  }

  // Constructor con parámetros
  public Usuario(String ci, String correo, String contrasena, String nombre, String apellido, char sexo, boolean esta_activo) {
    this.ci = ci;
    this.correo = correo;
    this.contrasena = contrasena;
    this.nombre = nombre;
    this.apellido = apellido;
    this.estaActivo = esta_activo;
  }
  // Getters y Setters
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getCi() {
    return ci;
  }
  public void setCi(String ci) {
    this.ci = ci;
  }
  public String getCorreo() {
    return correo;
  }
  public void setCorreo(String correo) {
    this.correo = correo;
  }
  public String getContrasena() {
    return contrasena;
  }
  public void setContrasena(String contrasena) {
    this.contrasena = contrasena;
  }
  public String getNombre(){
    return nombre;
  }
  public void setNombre(String nombre){
    this.nombre = nombre;
  }
  public String getApellido(){
    return apellido;
  }
  public void setApellido(String apellido){
    this.apellido = apellido;
  }

  public boolean isEstaActivo(){
    return estaActivo;
  }
  
  public void setEstaActivo(boolean estaActivo){
    this.estaActivo = estaActivo;
  }

  public Rol getRol(){
    return rol;
  }
  public void setRol(Rol rol){
    this.rol = rol;
  }

  public Asegurado setAsegurado(){
    return asegurado;
  }
  public void setAsegurado(Asegurado asegurado){
    this.asegurado= asegurado;
  }
  public Medico setMedico(){
    return medico;
  }
  public void setMedico(Medico medico){
    this.medico = medico;
  }
}
