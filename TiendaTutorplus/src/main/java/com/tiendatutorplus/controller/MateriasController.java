package com.tiendatutorplus.controller;

import com.tiendatutorplus.model.Materia;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class MateriasController {

    @GetMapping("/materias")
    public String verMaterias(Model model) {
        List<Materia> materias = Arrays.asList(
                new Materia("Matemáticas", "Matematicas.jpg"),
                new Materia("Inglés", "Ingles.jpg"),
                new Materia("Ciencias", "Ciencias.jpg"),
                new Materia("Computación", "Computacion.jpg"),
                new Materia("Estudios Sociales", "Estudios Sociales.jpg")
        );

        model.addAttribute("materias", materias);
        return "materias";
    }
}
