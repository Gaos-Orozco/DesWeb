package com.example.DesWeb.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.DesWeb.DTOs.CreateUsuarioRequest;
import com.example.DesWeb.DTOs.NombreCompletoResponse;
import com.example.DesWeb.DTOs.UsuarioResponse;
import com.example.DesWeb.service.UsuarioServiceImp;

@RestController
@RequestMapping("/api")
public class UsuarioController {
    private final UsuarioServiceImp userService;

    public UsuarioController(UsuarioServiceImp service) {
        this.userService = service;
    }

    //1 Crear usuario (CON AUTH)
    @PostMapping("/usuarios")
    public UsuarioResponse crearUsuario(@RequestBody CreateUsuarioRequest userReq){
        return userService.crear(userReq);
    }

      //2 Obtener todos (SIN AUTH)
      @GetMapping("/usuarios")
      public List <UsuarioResponse> listarUsuarios(){
        return userService.listar();
      }

      //3 Obtener por id (CON AUTH)
      @GetMapping("/usuarios/{id}")
      public UsuarioResponse obtenerPorId(@PathVariable Long id){
        return userService.buscarPorId(id);
      }

      //4 usuario con parametros (SIN AUTH)
      @GetMapping("/params")
      public NombreCompletoResponse params (@RequestParam String nombre,
                                            @RequestParam String apellido){
        return new NombreCompletoResponse(nombre + " " + apellido);
      }
}
