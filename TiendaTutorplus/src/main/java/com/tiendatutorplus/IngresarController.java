package com.tiendatutorplus;

import com.tiendatutorplus.model.Tutor;
import com.tiendatutorplus.model.Estudiante;
import com.tiendatutorplus.repository.TutorRepository;
import com.tiendatutorplus.repository.EstudianteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IngresarController {

    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @GetMapping("/ingresar")
    public String mostrarFormularioIngreso(Model model) {
        model.addAttribute("mensaje", "");
        return "ingresar";
    }

    @PostMapping("/ingresar")
    public String validarIngreso(
            @RequestParam("correo") String correo,
            @RequestParam("contrasena") String contrasena,
            @RequestParam("tipo") String tipo,
            Model model) {

        if (tipo.equals("profesor")) {
            Tutor tutor = tutorRepository.findByCorreoAndContrasena(correo, contrasena);
            if (tutor != null) {
                model.addAttribute("mensaje", "Bienvenido Profesor: " + tutor.getNombre() + " "+ tutor.getApellido1() + " "+ tutor.getApellido2());
            } else {
                model.addAttribute("mensaje", "Correo o contraseña incorrectos Profesor.");
            }

        } else if (tipo.equals("estudiante")) {
            Estudiante estudiante = estudianteRepository.findByCorreoAndContrasena(correo, contrasena);
            if (estudiante != null) {
                model.addAttribute("mensaje", "Bienvenido Estudiante: " + estudiante.getNombre() + " " + estudiante.getApellido1() +" "+ estudiante.getApellido2());
            } else {
                model.addAttribute("mensaje", "Correo o contraseña incorrectos Estudiante.");
            }

        } else {
            model.addAttribute("mensaje", "Debe seleccionar un tipo de usuario.");
        }

        return "ingresar";
    }
}
