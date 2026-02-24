package com.example.DesWeb.controller;

import com.example.DesWeb.DTOs.CreateUsuarioRequest;
import com.example.DesWeb.DTOs.UsuarioResponse;
import com.example.DesWeb.service.UsuarioService;
import org.springframework.web.bind.annotation.*;
import com.example.DesWeb.model.Usuario;
import com.example.DesWeb.DTOs.NombreCompletoResponse;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UsuarioController {
    private final UsuarioService userService;

    public UsuarioController(UsuarioService service) {
        this.userService = service;
    }

    //1 Crear usuario (CON AUTH)
    @PostMapping
    public UsuarioResponse crearUsuario(@RequestBody CreateUsuarioRequest userReq){
        return userService.crear(userReq);
    }

      //2 Obtener todos (SIN AUTH)
      @GetMapping("/usuarios")
      public List <Usuario> listar(){
        return userService.listar();
      }

      //3 Obtener por id (CON AUTH)
      @GetMapping("/usuarios/{id}")
      public Usuario obtenerPorId(@PathVariable Long id){
        return userService.buscarPorId(id);
      }

      //4 usuario con parametros (SIN AUTH)
      @GetMapping("/params")
      public NombreCompletoResponse params (@RequestParam String nombre,
                                            @RequestParam String apellido){
        return new NombreCompletoResponse(nombre + " " + apellido);
      }
}
