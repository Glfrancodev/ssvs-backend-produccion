package com.ssvs.seguro_salud_vida_sana.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
}
