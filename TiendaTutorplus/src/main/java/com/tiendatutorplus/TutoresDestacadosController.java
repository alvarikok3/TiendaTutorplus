package com.tiendatutorplus;

import com.tiendatutorplus.model.Tutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class TutoresDestacadosController {

    @GetMapping("/tutores-destacados")
    public String verTutoresDestacados(Model model) {
        List<Tutor> tutores = Arrays.asList(
                new Tutor("Luis", "user1.png", 4.8),
                new Tutor("Ana", "user2.png", 4.9)
        );
        model.addAttribute("tutores", tutores);
        return "destacados";
    }
}
