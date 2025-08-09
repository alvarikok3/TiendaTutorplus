package com.tiendatutorplus.repository;

import com.tiendatutorplus.model.Estudiante;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {

    Optional<Estudiante> findByCorreo(String correo);
}
