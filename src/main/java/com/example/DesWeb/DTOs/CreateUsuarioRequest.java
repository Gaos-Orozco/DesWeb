package com.example.DesWeb.DTOs;

import lombok.*;

@Data
public class CreateUsuarioRequest {
    private String nombre;
    private String apellido;
    private String user;
    private String password;
}
