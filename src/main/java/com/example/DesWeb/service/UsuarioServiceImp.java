package com.example.DesWeb.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.DesWeb.DTOs.CreateUsuarioRequest;
import com.example.DesWeb.DTOs.UsuarioResponse;
import com.example.DesWeb.model.Usuario;
import com.example.DesWeb.repository.UsuarioRepository;

@Service
public class UsuarioServiceImp implements UsuarioService {

    private final UsuarioRepository userRepo;
    private final PasswordEncoder encoder;

    public UsuarioServiceImp(UsuarioRepository userRepo, PasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.encoder = encoder;
    }

    @Override
    public UsuarioResponse crear(CreateUsuarioRequest userReq) {

        if (userReq.getUser() == null || userReq.getUser().isBlank()
                || userReq.getPassword() == null || userReq.getPassword().isBlank()) {
            throw new IllegalArgumentException("El usuario y la contraseña son obligatorios");
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

        Usuario userGuardado = userRepo.save(user);

        return new UsuarioResponse(
                userGuardado.getId(),
                userGuardado.getNombre() != null ? userGuardado.getNombre().toUpperCase() : null,
                userGuardado.getApellido() != null ? userGuardado.getApellido().toUpperCase() : null
        );
    }
    
    @Override
    public List<UsuarioResponse> listar() {
        return userRepo.findAll()
                .stream()
                .map(u -> new UsuarioResponse(u.getId(), u.getNombre(), u.getApellido()))
                .toList();
    }

    @Override
    public UsuarioResponse buscarPorId(Long id) {
        Usuario u = userRepo.findById(id)
                .orElseThrow(() ->
        new ResponseStatusException(
            HttpStatus.NOT_FOUND, "Usuario No encntrado" + id
        )
);

        return new UsuarioResponse(
            u.getId(),
            u.getNombre(),
            u.getApellido());
    }
}