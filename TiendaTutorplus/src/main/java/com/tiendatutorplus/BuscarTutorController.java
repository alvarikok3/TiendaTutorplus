package com.tiendatutorplus;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BuscarTutorController {

    @GetMapping("/buscar-tutor")
    public String mostrarFormularioBusqueda() {
        return "buscartutor";
    }
}
