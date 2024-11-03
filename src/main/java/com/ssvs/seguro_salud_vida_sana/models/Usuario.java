package com.ssvs.seguro_salud_vida_sana.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "correo")
  private String correo;

  @Column(name = "contrasena")
  private String contrasena;

  @Column(name = "nombre")
  private String nombre;

  @Column(name = "apellido")
  private String apellido;

  @Column(name = "sexo")
  private char sexo;
  @Column(name = "esta_activo")
  private boolean estaActivo;

  // Relacion Usuario con Rol n a 1
  @ManyToOne
  @JoinColumn(name = "rol_id")
  @JsonIgnoreProperties("usuarios")
  private Rol rol;

  // // Relacion Usuario con Direccion 1 a n
  // @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  // @JsonIgnore
  // private Set<Direccion> direcciones;

  // // Relacion Usuario con UsuarioDireccion 1 a n
  // @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  // @JsonIgnore
  // private Set<UsuarioSucursal> usuarioSucursales;

  // // Relacion Usuario con NotaVenta 1 a n
  // @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  // @JsonIgnore
  // private Set<NotaVenta> notasVentas;

  // Constructor por defecto
  public Usuario() {
  }

  // Constructor con parámetros
  public Usuario(String correo, String contrasena, String nombre, String apellido, char sexo, boolean esta_activo) {
    this.correo = correo;
    this.contrasena = contrasena;
    this.nombre = nombre;
    this.apellido = apellido;
    this.sexo = sexo;
    this.estaActivo = esta_activo;
  }
  // Getters y Setters
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
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
  public char getSexo(){
    return sexo;
  }
  public void setSexo(char sexo){
    this.sexo = sexo;
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

  // public Set<UsuarioSucursal> getUsuarioSucursales(){
  //   return usuarioSucursales;
  // }
  // public void setUsuarioSucursales(Set<UsuarioSucursal> usuarioSucursales){
  //   this.usuarioSucursales = usuarioSucursales;
  // }

  // public Set<Direccion> getDirecciones(){
  //   return direcciones;
  // }
  // public void setDirecciones(Set<Direccion> direcciones){
  //   this.direcciones = direcciones;
  // }

  // public Set<NotaVenta> getNotasVentas(){
  //   return notasVentas;
  // }

  // public void setNotasVentas(Set<NotaVenta> notasVentas){
  //   this.notasVentas = notasVentas;
  // }

}
