package com.tiendatutorplus.repository;

import com.tiendatutorplus.model.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
    
    Optional<Administrador> findByCorreo(String correo);
}
