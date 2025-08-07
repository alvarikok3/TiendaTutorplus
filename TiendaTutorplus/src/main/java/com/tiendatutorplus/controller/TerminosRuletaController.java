package com.tiendatutorplus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TerminosRuletaController {

    @GetMapping("/terminos-ruleta")
    public String verTerminosRuleta() {
        return "terminos-ruleta"; // nombre exacto del html sin extensión
    }
}
