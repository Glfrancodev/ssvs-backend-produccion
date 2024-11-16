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
@Table(name = "archivo")
public class Archivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "tipo", nullable = false)
    private String tipo; // Tipo MIME del archivo (e.g., image/png, application/pdf)

    @Column(name = "url", nullable = false)
    private String url; // Ruta o URL del archivo almacenado

    @Column(name = "tamanio", nullable = false)
    private Long tamanio; // Tamaño del archivo en bytes

    // Relacion Archivo con Consulta n a 1
    @ManyToOne
    @JoinColumn(name = "consulta_id")
    @JsonIgnoreProperties("archivos")
    private Consulta consulta;

    // Constructor por defecto
    public Archivo() {
    }

    // Constructor con parámetros
    public Archivo(String nombre, String tipo, String url, Long tamanio) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.url = url;
        this.tamanio = tamanio;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getTamanio() {
        return tamanio;
    }

    public void setTamanio(Long tamanio) {
        this.tamanio = tamanio;
    }

    public Consulta getConsulta(){
        return consulta;
    }

    public void setConsulta(Consulta consulta){
        this.consulta = consulta;
    }
}
