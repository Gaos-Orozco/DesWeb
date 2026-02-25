package com.example.DesWeb.DTOs;

public class UsuarioResponse {

    private Long id;
    private String nombre;
    private String apellido;

    public UsuarioResponse(Long id, String nombre, String apellido) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }
}