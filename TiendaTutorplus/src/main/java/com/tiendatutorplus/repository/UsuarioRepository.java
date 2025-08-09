package com.tiendatutorplus.repository;

import com.tiendatutorplus.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Para EL login
    Optional<Usuario> findByCorreo(String correo);

    // Validación de registro
    boolean existsByCorreo(String correo);

    // Listar solo usuarios activos/inactivos
    List<Usuario> findByActivoTrue();
    List<Usuario> findByActivoFalse();
}
