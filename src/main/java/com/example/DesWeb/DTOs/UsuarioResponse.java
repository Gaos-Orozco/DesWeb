package com.example.DesWeb.DTOs;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UsuarioResponse {
    private Long  id;
    private String nombre;
    private String apellido;
   
}
