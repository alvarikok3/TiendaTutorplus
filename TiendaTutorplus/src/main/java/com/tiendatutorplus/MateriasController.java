package com.tiendatutorplus;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class MateriasController {

    @GetMapping("/materias")
    public String verMaterias(Model model) {
        List<String> materias = Arrays.asList("Matemáticas", "Inglés", "Ciencias", "Español");
        model.addAttribute("materias", materias);
        return "materias";
    }
}
