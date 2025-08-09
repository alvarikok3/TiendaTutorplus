package com.tiendatutorplus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IngresarController {

    @GetMapping("/ingresar")
    public String login() {
        return "ingresar";
    }
}
