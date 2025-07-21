package com.tiendatutorplus;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecomendacionesController {

    @GetMapping("/recomendaciones")
    public String mostrarRecomendaciones() {
        return "recomendaciones"; // Esto busca recomendaciones.html en /templates
    }
}
