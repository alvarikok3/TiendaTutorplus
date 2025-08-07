package com.tiendatutorplus;

import com.tiendatutorplus.model.Tutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TutoresDestacadosController {

    @GetMapping("/tutores-destacados")
    public String verTutoresDestacados(@RequestParam(name = "especialidad", required = false) String especialidad,
            Model model) {

        List<Tutor> todosLosTutores = Arrays.asList(
                new Tutor("Luis", "Matemáticas", 4.8, "user1.png", "Apasionado por enseñar números."),
                new Tutor("Ana", "Inglés", 4.9, "user2.png", "Con 10 años de experiencia internacional."),
                new Tutor("Carlos", "Física", 4.7, "user3.png", "Explica temas complejos con sencillez."),
                new Tutor("Laura", "Química", 4.6, "user4.png", "Especialista en secundaria y universidad.")
        );

        // Agregamos campos extra para cada tutor
        todosLosTutores.get(0).setModalidad("Virtual");
        todosLosTutores.get(0).setNivel("Universidad");

        todosLosTutores.get(1).setModalidad("Presencial");
        todosLosTutores.get(1).setNivel("Secundaria");

        todosLosTutores.get(2).setModalidad("Virtual");
        todosLosTutores.get(2).setNivel("Universidad");

        todosLosTutores.get(3).setModalidad("Presencial");
        todosLosTutores.get(3).setNivel("Secundaria");

        List<Tutor> tutoresFiltrados = (especialidad == null || especialidad.isEmpty())
                ? todosLosTutores
                : todosLosTutores.stream()
                        .filter(t -> t.getEspecialidad().equalsIgnoreCase(especialidad))
                        .collect(Collectors.toList());

        model.addAttribute("tutores", tutoresFiltrados);
        model.addAttribute("especialidadSeleccionada", especialidad);
        return "destacados";
    }
}
