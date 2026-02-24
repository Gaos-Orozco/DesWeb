package com.example.DesWeb.DTOs;

public class NombreCompletoResponse {
    private String nombreCompleto;

    public NombreCompletoResponse(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getNombreCompleto() 
    { 
        return nombreCompleto; 
    }
}