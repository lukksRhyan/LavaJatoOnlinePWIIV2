package com.example.LavaJatoOnlinesw.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Cookie;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    @GetMapping("/")
    public String home(HttpServletRequest request, Model model) {
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
            model.addAttribute("usuarioLogado", usuarioLogado);

            return "index.html";
        }

        return "login";
    }
}
