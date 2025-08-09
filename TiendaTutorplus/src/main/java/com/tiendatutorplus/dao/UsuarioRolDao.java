package com.tiendatutorplus.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class UsuarioRolDao {
    private final JdbcTemplate jdbcTemplate;
    public UsuarioRolDao(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    public List<String> findRoleNamesByCorreo(String correo) {
        String sql = "SELECT r.nombre FROM roles r JOIN usuario_rol ur ON ur.rol_id = r.id WHERE ur.correo = ?";
        return jdbcTemplate.query(sql, ps -> ps.setString(1, correo), (rs, i) -> rs.getString(1));
    }
}
