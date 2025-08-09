package com.tiendatutorplus.controller;

import com.tiendatutorplus.model.Plan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class PlanesController {

    @GetMapping("/planes")
    public String verPlanes(Model model) {
        List<Plan> planes = Arrays.asList(
            new Plan("Básico", "Acceso limitado a tutores", 4500),
            new Plan("Intermedio", "Acceso a todos los tutores", 7500),
            new Plan("Premium", "Acceso total + tutorías personalizadas", 11500)
        );
        model.addAttribute("planes", planes);
        return "planes";
    }
}
