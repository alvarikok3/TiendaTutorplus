package com.tiendatutorplus;

import com.tiendatutorplus.model.Tutor;
import com.tiendatutorplus.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FormularioController {

    @Autowired
    private TutorRepository tutorRepository;

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
        model.addAttribute("tutor", new Tutor());
        return "registro";
    }

    @GetMapping("/ayuda")
    public String mostrarAyuda() {
        return "ayuda";
    }

    @PostMapping("/registro")
    public String procesarRegistro(@ModelAttribute("tutor") Tutor tutor, BindingResult result, Model model) {
        if (tutor.getNombre().isEmpty() || tutor.getApellido1().isEmpty()
                || tutor.getApellido2().isEmpty() || tutor.getCorreo().isEmpty()
                || tutor.getEspecialidad().isEmpty()) {

            result.rejectValue("nombre", null, "Todos los campos son obligatorios.");
            return "registro";
        }

        // Guardar en la base de datos
        tutorRepository.save(tutor);

        model.addAttribute("mensaje", "Registro completado con éxito.");
        model.addAttribute("tutor", new Tutor()); // limpia el formulario
        return "registro";
    }
}
