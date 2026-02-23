package com.example.DesWeb.repository;
import com.example.DesWeb.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;   

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByUser(String user);
}
