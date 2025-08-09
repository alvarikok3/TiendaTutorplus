package com.tiendatutorplus.repository;

import com.tiendatutorplus.model.Tutor;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorRepository extends JpaRepository<Tutor, Long> {

    Optional<Tutor> findByCorreo(String correo);
}
