package com.tiendatutorplus.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsuarioRolRepository {

    @Query(
      value = "SELECT r.nombre " +
              "FROM roles r " +
              "JOIN usuario_rol ur ON ur.rol_id = r.id " +
              "WHERE ur.correo = :correo",
      nativeQuery = true)
    List<String> findRoleNamesByCorreo(@Param("correo") String correo);
}
