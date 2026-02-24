package com.example.DesWeb.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.DesWeb.DTOs.CreateUsuarioRequest;
import com.example.DesWeb.DTOs.UsuarioResponse;
import com.example.DesWeb.model.Usuario;
import com.example.DesWeb.repository.UsuarioRepository;


@Service
public class UsuarioService {
    private final UsuarioRepository userRepo;
    private final PasswordEncoder encoder;

    public UsuarioService(UsuarioRepository userRepo, PasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.encoder = encoder;
    }

    public UsuarioResponse crear(CreateUsuarioRequest userReq){
        if(userReq.getUser() == null || userReq.getPassword() == null){
            throw new IllegalArgumentException("El usuario O la contraseña no pueden ser nulos");
        }
        if (userRepo.existsByUser(userReq.getUser())) {
            throw new IllegalArgumentException("El nombre de usuario ya existe");
        }

        Usuario user = Usuario.builder()
        .nombre(userReq.getNombre())
        .apellido(userReq.getApellido())
        .user(userReq.getUser())
        .password(encoder.encode(userReq.getPassword()))
        .build();

        Usuario Userguardado = userRepo.save(user);
        return new UsuarioResponse(
            Userguardado.getId(),
            Userguardado.getNombre().toUpperCase(),
            Userguardado.getApellido().toUpperCase()
         );
    }
    
    public List<Usuario> listar(){
        return userRepo.findAll();
    }

    public Usuario buscarPorId (Long id){
        return userRepo.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
    }
}
