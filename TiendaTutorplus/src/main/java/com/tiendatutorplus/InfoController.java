package com.tiendatutorplus;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import java.util.Arrays;

@Controller
public class InfoController {

    @GetMapping("/nosotros")
    public String nosotros(Model model) {
        model.addAttribute("titulo", "Nosotros");
        return "nosotros";
    }

    @GetMapping("/preguntas")
    public String preguntas(Model model) {
        model.addAttribute("titulo", "Preguntas Frecuentes");
        return "preguntas";
    }

    @GetMapping("/galeria")
    public String galeria(Model model) {
        model.addAttribute("titulo", "Galería");
        // Lista de URLs de imagenes (puedes reemplazar con tus rutas locales)
        model.addAttribute("imagenes", Arrays.asList(
            "https://via.placeholder.com/300x200",
            "https://via.placeholder.com/300x200",
            "https://via.placeholder.com/300x200"
        ));
        return "galeria";
    }
}