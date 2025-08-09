package com.tiendatutorplus.controller;

import com.tiendatutorplus.model.Estudiante;
import com.tiendatutorplus.model.Tutor;
import com.tiendatutorplus.repository.EstudianteRepository;
import com.tiendatutorplus.repository.TutorRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class FormularioController {

    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    // Para insertar en usuario_rol fácilmente
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Usa el PasswordEncoder que definiste en SecurityConfig (delegating/bcrypt)
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/contacto")
    public String verContacto() {
        return "contacto";
    }

    @GetMapping("/testimonios")
    public String verTestimonos() {
        return "testimonios";
    }

    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("mensaje", "");
        return "registro";
    }

    @GetMapping("/ayuda")
    public String mostrarAyuda() {
        return "ayuda";
    }

    @PostMapping("/registro")
    public String registrarUsuario(
            @RequestParam("nombre") String nombre,
            @RequestParam("apellido1") String apellido1,
            @RequestParam("apellido2") String apellido2,
            @RequestParam("correo") String correo,
            @RequestParam("contrasena") String contrasena,
            @RequestParam("tipo") String tipo,
            @RequestParam(value = "especialidad", required = false) String especialidad,
            Model model) {

        // Normaliza el tipo
        String tipoNorm = tipo == null ? "" : tipo.trim().toLowerCase();

        if ("profesor".equals(tipoNorm)) {
            // Validaciones de unicidad entre tablas
            Optional<Estudiante> estudianteExistente = estudianteRepository.findByCorreo(correo);
            if (estudianteExistente != null) {
                model.addAttribute("mensaje", "Este correo ya está registrado como estudiante.");
                return "registro";
            }
            Optional<Tutor> profesorExistente = tutorRepository.findByCorreo(correo);
            if (profesorExistente != null) {
                model.addAttribute("mensaje", "Este correo ya está registrado como profesor.");
                return "registro";
            }

            // Guardar tutor con contraseña ENCRIPTADA
            Tutor tutor = new Tutor();
            tutor.setNombre(nombre);
            tutor.setApellido1(apellido1);
            tutor.setApellido2(apellido2);
            tutor.setCorreo(correo);
            tutor.setContrasena(passwordEncoder.encode(contrasena)); // <- encripta
            tutor.setEspecialidad(especialidad != null ? especialidad : "");
            tutorRepository.save(tutor);

            // Asignar rol ROLE_TUTOR en usuario_rol
            asignarRolPorCorreo(correo, "ROLE_TUTOR");

            model.addAttribute("mensaje", "Profesor registrado exitosamente.");

        } else if ("estudiante".equals(tipoNorm)) {
            // Validaciones de unicidad entre tablas
            Optional<Tutor> profesorExistente = tutorRepository.findByCorreo(correo);
            if (profesorExistente != null) {
                model.addAttribute("mensaje", "Este correo ya está registrado como profesor.");
                return "registro";
            }
            Optional<Estudiante> estudianteExistente = estudianteRepository.findByCorreo(correo);
            if (estudianteExistente != null) {
                model.addAttribute("mensaje", "Este correo ya está registrado como estudiante.");
                return "registro";
            }

            // Guardar estudiante con contraseña ENCRIPTADA
            Estudiante estudiante = new Estudiante();
            estudiante.setNombre(nombre);
            estudiante.setApellido1(apellido1);
            estudiante.setApellido2(apellido2);
            estudiante.setCorreo(correo);
            estudiante.setContrasena(passwordEncoder.encode(contrasena)); // <- encripta
            estudianteRepository.save(estudiante);

            // Asignar rol ROLE_ESTUDIANTE en usuario_rol
            asignarRolPorCorreo(correo, "ROLE_ESTUDIANTE");

            model.addAttribute("mensaje", "Estudiante registrado exitosamente.");

        } else {
            model.addAttribute("mensaje", "Debe seleccionar un tipo de usuario válido (profesor o estudiante).");
        }

        return "registro";
    }

    /**
     * Inserta (si no existe) el rol para el correo en la tabla usuario_rol
     */
    private void asignarRolPorCorreo(String correo, String nombreRol) {
        // Obtiene id del rol
        Integer rolId = jdbcTemplate.queryForObject(
                "SELECT id FROM roles WHERE nombre = ?",
                Integer.class, nombreRol);

        if (rolId == null) {
            throw new IllegalStateException("No existe el rol: " + nombreRol);
        }

        // Evita duplicados: inserta solo si no existe
        Integer count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM usuario_rol WHERE correo = ? AND rol_id = ?",
                Integer.class, correo, rolId);

        if (count != null && count == 0) {
            jdbcTemplate.update(
                    "INSERT INTO usuario_rol (correo, rol_id) VALUES (?, ?)",
                    correo, rolId
            );
        }
    }
}
