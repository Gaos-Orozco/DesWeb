package com.example.DesWeb.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
  
import com.example.DesWeb.model.Usuario;   

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByUser(String user);
    Optional<Usuario> findByUser(String user);
}
