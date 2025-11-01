package dev.cmartinez.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HolaController {

    @GetMapping("/hola")
    public String holaCrack() {
        return "¡Hola crack! Spring Boot con Docker funcionando 🚀 - por cmartinez";
    }
    
    @GetMapping("/")
    public String home() {
        return "¡Mi primer Spring Boot moderno está vivo! 💻 - Desarrollado por cmartinez";
    }
}
