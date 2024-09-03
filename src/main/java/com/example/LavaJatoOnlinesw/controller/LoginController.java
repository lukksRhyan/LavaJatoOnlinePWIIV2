package com.example.LavaJatoOnlinesw.controller;

import com.example.LavaJatoOnlinesw.model.Cliente;
import com.example.LavaJatoOnlinesw.model.Usuario;

import com.example.LavaJatoOnlinesw.repository.ClienteRepository;
import com.example.LavaJatoOnlinesw.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/login")
    public String index() {
        return "login.html"; // Retorna o nome do template (index.html) sem a extensão
    }

    @PostMapping("/login")
    public String login(@RequestParam String nomeUsuario, @RequestParam String senha, HttpServletResponse response, Model model){
        Usuario usuario = usuarioRepository.findByUsername(nomeUsuario);

        if(usuario != null && usuario.getPassword().equals(senha)){
            Cookie cookie = new Cookie("usuarioLogado", nomeUsuario);
            cookie.setMaxAge(7 * 24 * 60 * 60);
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            response.addCookie(cookie);

            model.addAttribute("usuarioLogado", nomeUsuario);

            List<Cliente> clientes = clienteRepository.findAll();

            model.addAttribute("clientes", clientes);

            return "index.html";
       }
        model.addAttribute("mensagemErro", "Usuario ou senha incorreto");
       return "login.html";
    }
    @PostMapping("/signup")
    public String signup(@RequestParam String nomeUsuario, @RequestParam String senha, HttpServletResponse response){
        if(usuarioRepository.findByUsername(nomeUsuario) == null){

            Usuario usuario = new Usuario(nomeUsuario,senha);
            usuarioRepository.save(usuario);

            Cookie cookie = new Cookie("usuarioLogado", nomeUsuario);
            cookie.setMaxAge(7 * 24 * 60 * 60);
            cookie.setHttpOnly(true);
            cookie.setPath("/");

            response.addCookie(cookie);


      }

      //TODO: Salvar nos cookies
        return "index.html";
    }

}