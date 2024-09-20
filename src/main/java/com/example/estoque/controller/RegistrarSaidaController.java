package com.example.estoque.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegistrarSaidaController {

    @GetMapping("/registrarSaida")
    public String registrarSaida() {
        return "registrarSaida";
    }
}
