package com.example.LavaJatoOnlinesw.controller;

import com.example.LavaJatoOnlinesw.DTO.ClienteDTO;
import com.example.LavaJatoOnlinesw.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // Listar todos os clientes
    @GetMapping
    public List<ClienteDTO> findAll() {
        return clienteService.findAll();
    }

    // Encontrar cliente pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Long id) {
        ClienteDTO clienteDTO = clienteService.findById(id);
        return ResponseEntity.ok(clienteDTO);
    }

    // Criar um novo cliente
    @PostMapping
    public ResponseEntity<ClienteDTO> create(@RequestBody ClienteDTO clienteDTO) {
        ClienteDTO novoCliente = clienteService.create(clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCliente);
    }

    // Atualizar um cliente existente
    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
        ClienteDTO clienteAtualizado = clienteService.update(id, clienteDTO);
        return ResponseEntity.ok(clienteAtualizado);
    }

    // Deletar um cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
