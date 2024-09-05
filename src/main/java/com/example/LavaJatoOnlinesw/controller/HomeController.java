package com.example.LavaJatoOnlinesw.controller;

import com.example.LavaJatoOnlinesw.model.Cliente;
import com.example.LavaJatoOnlinesw.repository.ClienteRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class HomeController {

    @Autowired
    private ClienteRepository clienteRepository;



    @GetMapping("/")
    public String home(HttpServletRequest request, Model model) {
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("usuarioLogado")) {
                    model.addAttribute("usuarioLogado", cookie.getValue());


                    model.addAttribute("clientes",clienteRepository.findAll());
                    return "index.html";
                }
            }
        }
                return "login.html";
    }
}
