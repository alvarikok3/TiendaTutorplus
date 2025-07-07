package com.tiendatutorplus.repository;

import com.tiendatutorplus.model.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorRepository extends JpaRepository<Tutor, Integer> {

    Tutor findByCorreoAndContrasena(String correo, String contrasena);

    Tutor findByCorreo(String correo);
}
