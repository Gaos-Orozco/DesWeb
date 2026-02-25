package com.example.DesWeb.service;
import java.util.List;

import com.example.DesWeb.DTOs.CreateUsuarioRequest;
import com.example.DesWeb.DTOs.NombreCompletoResponse;
import com.example.DesWeb.DTOs.UsuarioResponse;

public interface UsuarioService {
    UsuarioResponse crear(CreateUsuarioRequest userReq);
    List<UsuarioResponse> listar();
    UsuarioResponse buscarPorId(Long id);
    NombreCompletoResponse buscarNombreCompleto(String nombre, String apellido);

}
