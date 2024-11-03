package com.ssvs.seguro_salud_vida_sana.models;

public class AuthenticationRequest {
    private String correo;
    private String contrasena;

    public AuthenticationRequest() {
    }

    public AuthenticationRequest(String correo, String contrasena) {
        this.correo = correo;
        this.contrasena = contrasena;
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
}
