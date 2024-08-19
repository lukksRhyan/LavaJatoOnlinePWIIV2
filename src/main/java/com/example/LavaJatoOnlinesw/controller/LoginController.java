package com.example.LavaJatoOnlinesw.controller;

import com.example.LavaJatoOnlinesw.model.Usuario;

import com.example.LavaJatoOnlinesw.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/login")
    public String index() {
        return "login.html"; // Retorna o nome do template (index.html) sem a extensão
    }

    @PostMapping("/login")
    public String login(@RequestParam String nomeUsuario, @RequestParam String senha){
       if(usuarioRepository.findByUsername(nomeUsuario).getPassword() == senha){
           return "index.html";
       }
       return "login.html";
    }
    @PostMapping("/signup")
    public String signup(@RequestParam String nomeUsuario, @RequestParam String senha){
      if(usuarioRepository.findByUsername(nomeUsuario) != null){
          Usuario usuario = new Usuario(nomeUsuario,senha);
          usuarioRepository.save(usuario);
      }

      //TODO: Salvar nos cookies
        return "index.html";
    }

}