package com.example.LavaJatoOnlinesw.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    @GetMapping("/")
    public String home(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String usuarioLogado = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("usuarioLogado")) {
                    usuarioLogado = cookie.getValue();
                    break;
                }
            }
        }
        if (usuarioLogado != null && !usuarioLogado.isEmpty()) {
            return "index.html";
        }

        return "redirect:login.html";
    }
}
