package com.example.LavaJatoOnlinesw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String index() {
        return "login.html"; // Retorna o nome do template (index.html) sem a extensão
    }
}