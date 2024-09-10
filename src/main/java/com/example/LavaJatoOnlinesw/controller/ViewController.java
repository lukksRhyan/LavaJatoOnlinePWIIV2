package com.example.LavaJatoOnlinesw.controller;

import com.example.LavaJatoOnlinesw.model.Usuario;

import com.example.LavaJatoOnlinesw.repository.ClienteRepository;
import com.example.LavaJatoOnlinesw.repository.UsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class ViewController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/login")
    public String index(HttpServletRequest request, Model model) {

        if(IsUsuarioLogado(request,model)){
            return "index.html";
        }
        return "login.html";
    }

    @PostMapping("/login")
    public String login(@RequestParam String nomeUsuario, @RequestParam String senha, HttpServletResponse response, Model model){
        Usuario usuario = usuarioRepository.findByUsername(nomeUsuario);

        if(usuario != null && usuario.getPassword().equals(senha)){

            salvarUsuarioCookie(nomeUsuario,model,response);

            return "index.html";
       }
        model.addAttribute("mensagemErro", "Usuario ou senha incorreto");
       return "login.html";
    }
    @PostMapping("/signup")
    public String signup(@RequestParam String nomeUsuario, @RequestParam String senha, HttpServletResponse response, Model model){
        Usuario usuario = usuarioRepository.findByUsername(nomeUsuario);

        if(usuario == null){
            usuario = new Usuario(nomeUsuario,senha);
            usuarioRepository.save(usuario);
        }

        salvarUsuarioCookie(nomeUsuario,model,response);

        if(usuario.getPassword().equals(senha)){
            model.addAttribute("mensagem", "Usuario já cadastrado");
            return "login.html";
        }

        return "index.html";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, Model model) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("usuarioLogado")) {
                    cookie.setMaxAge(0);
                    cookie.setValue(null);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
            }
        }

        model.addAttribute("mensagem", "Você foi desconectado com sucesso.");

        return "login.html";
    }

    private void salvarUsuarioCookie(String nomeUsuario, Model model, HttpServletResponse response){

        Cookie cookie = new Cookie("usuarioLogado", nomeUsuario);
        cookie.setMaxAge(7 * 24 * 60 * 60);
        cookie.setHttpOnly(true);
        cookie.setPath("/");

        model.addAttribute("usuarioLogado", nomeUsuario);

        model.addAttribute("clientes", clienteRepository.findAll());

        response.addCookie(cookie);
    }
    public boolean IsUsuarioLogado(HttpServletRequest request, Model model){
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("usuarioLogado")){
                    model.addAttribute("usuarioLogado", cookie.getValue());
                    return true;
                }
            }
        }
        return  false;
    }

    //Todo: Pagina Cliente

    //Todo: Agendamento

}