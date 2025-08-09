package com.tiendatutorplus.security;

import com.tiendatutorplus.dao.UsuarioRolDao;
import com.tiendatutorplus.model.Administrador;
import com.tiendatutorplus.model.Estudiante;
import com.tiendatutorplus.model.Tutor;
import com.tiendatutorplus.repository.AdministradorRepository;
import com.tiendatutorplus.repository.EstudianteRepository;
import com.tiendatutorplus.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AdministradorRepository adminRepo;
    @Autowired
    private TutorRepository tutorRepo;
    @Autowired
    private EstudianteRepository estRepo;
    @Autowired
    private UsuarioRolDao roleDao;

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        // Normaliza espacios por si el input viene con trim pendiente
        if (correo != null) {
            correo = correo.trim();
        }

        String password = null;

        // ADMIN
        try {
            Optional<Administrador> admOpt = adminRepo.findByCorreo(correo);
            if (admOpt != null && admOpt.isPresent()) {
                password = admOpt.get().getContrasena();
            }
        } catch (Exception ignore) {
        }

        // TUTOR
        if (password == null) {
            try {
                Optional<Tutor> tutOpt = tutorRepo.findByCorreo(correo);
                if (tutOpt != null && tutOpt.isPresent()) {
                    password = tutOpt.get().getContrasena();
                }
            } catch (Exception ignore) {
            }
        }

        // ESTUDIANTE
        if (password == null) {
            try {
                Optional<Estudiante> estOpt = estRepo.findByCorreo(correo);
                if (estOpt != null && estOpt.isPresent()) {
                    password = estOpt.get().getContrasena();
                }
            } catch (Exception ignore) {
            }
        }

        if (password == null) {
            throw new UsernameNotFoundException("No existe cuenta con correo: " + correo);
        }

        // Carga roles
        List<String> roleNames = roleDao.findRoleNamesByCorreo(correo);
        // Si por alguna razón no hay roles, al menos devuelve una lista vacía (autenticará pero no pasará a rutas con hasRole)
        Collection<GrantedAuthority> authorities = new HashSet<>();
        for (String r : roleNames) {
            authorities.add(new SimpleGrantedAuthority(r));
        }

        return new org.springframework.security.core.userdetails.User(
                correo,
                password, // OJO: estás usando NoOpPasswordEncoder -> debe ser tal cual está en la BD
                true, true, true, true,
                authorities
        );
    }
}
