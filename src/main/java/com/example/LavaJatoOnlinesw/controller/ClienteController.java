package com.example.LavaJatoOnlinesw.controller;

import com.example.LavaJatoOnlinesw.model.Cliente;
import com.example.LavaJatoOnlinesw.repository.ClienteRepository;
import com.example.LavaJatoOnlinesw.model.Carro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public static List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }



    //teste commit
    @PostMapping
    public Cliente createCliente(  @RequestParam("nome") String nome,
                                   @RequestParam("email") String email,
                                   @RequestParam("telefone") String telefone) {

        return clienteRepository.save(new Cliente(nome,telefone,email));
    }

    @GetMapping("/{id}")
    public String getClienteById(@PathVariable Long id, Model model) {
        Cliente cliente = clienteRepository.findById(id).
                orElseThrow(()-> new RuntimeException("Cliente não encontrado"));
        model.addAttribute("cliente",cliente);
        model.addAttribute("novoveiculo",new Carro());
        return "cliente.html";
    }

    @PutMapping(value = "/{id}")
    public Cliente updateCliente(@PathVariable Long id, @RequestBody Cliente clienteDetails) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        cliente.setNome(clienteDetails.getNome());
        cliente.setTelefone(clienteDetails.getTelefone());
        cliente.setEmail(clienteDetails.getEmail());
        return clienteRepository.save(cliente);
    }

    @DeleteMapping("/{id}")
    public void deleteCliente(@PathVariable Long id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        clienteRepository.delete(cliente);
    }
}
