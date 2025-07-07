package com.tiendatutorplus;

import com.tiendatutorplus.model.Estudiante;
import com.tiendatutorplus.model.Tutor;
import com.tiendatutorplus.repository.EstudianteRepository;
import com.tiendatutorplus.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FormularioController {

    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

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

        if (tipo.equals("profesor")) {
            // Validar que no exista como estudiante
            Estudiante estudianteExistente = estudianteRepository.findByCorreo(correo);
            if (estudianteExistente != null) {
                model.addAttribute("mensaje", "Este correo ya está registrado como estudiante.");
                return "registro";
            }

            // Validar que no exista ya como profesor
            Tutor profesorExistente = tutorRepository.findByCorreo(correo);
            if (profesorExistente != null) {
                model.addAttribute("mensaje", "Este correo ya está registrado como profesor.");
                return "registro";
            }

            Tutor tutor = new Tutor();
            tutor.setNombre(nombre);
            tutor.setApellido1(apellido1);
            tutor.setApellido2(apellido2);
            tutor.setCorreo(correo);
            tutor.setContrasena(contrasena);
            tutor.setEspecialidad(especialidad != null ? especialidad : "");
            tutorRepository.save(tutor);

            model.addAttribute("mensaje", "Profesor registrado exitosamente.");

        } else if (tipo.equals("estudiante")) {
            // Validar que no exista como profesor
            Tutor profesorExistente = tutorRepository.findByCorreo(correo);
            if (profesorExistente != null) {
                model.addAttribute("mensaje", "Este correo ya está registrado como profesor.");
                return "registro";
            }

            // Validar que no exista ya como estudiante
            Estudiante estudianteExistente = estudianteRepository.findByCorreo(correo);
            if (estudianteExistente != null) {
                model.addAttribute("mensaje", "Este correo ya está registrado como estudiante.");
                return "registro";
            }

            Estudiante estudiante = new Estudiante();
            estudiante.setNombre(nombre);
            estudiante.setApellido1(apellido1);
            estudiante.setApellido2(apellido2);
            estudiante.setCorreo(correo);
            estudiante.setContrasena(contrasena);
            estudianteRepository.save(estudiante);

            model.addAttribute("mensaje", "Estudiante registrado exitosamente.");
        } else {
            model.addAttribute("mensaje", "Debe seleccionar un tipo de usuario válido.");
        }

        return "registro";
    }

}
