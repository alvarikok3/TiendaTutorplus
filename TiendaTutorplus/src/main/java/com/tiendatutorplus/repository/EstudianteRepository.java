package com.tiendatutorplus.repository;

import com.tiendatutorplus.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteRepository extends JpaRepository<Estudiante, Integer> {

    Estudiante findByCorreoAndContrasena(String correo, String contrasena);

    Estudiante findByCorreo(String correo);
}

