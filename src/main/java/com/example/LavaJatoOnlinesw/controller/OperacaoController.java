package com.example.LavaJatoOnlinesw.controller;

import com.example.LavaJatoOnlinesw.DTO.OperacaoDTO;
import com.example.LavaJatoOnlinesw.Service.OperacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/operacoes")
public class OperacaoController {

    @Autowired
    private OperacaoService operacaoService;

    // Listar todas as operações
    @GetMapping
    public List<OperacaoDTO> findAll() {
        return operacaoService.findAll();
    }

    // Encontrar operação pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<OperacaoDTO> findById(@PathVariable Long id) {
        OperacaoDTO operacaoDTO = operacaoService.findById(id);
        return ResponseEntity.ok(operacaoDTO);
    }

    // Criar uma nova operação
    @PostMapping
    public ResponseEntity<OperacaoDTO> create(@RequestBody OperacaoDTO operacaoDTO) {
        OperacaoDTO novaOperacao = operacaoService.create(operacaoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaOperacao);
    }

    // Atualizar uma operação existente
    @PutMapping("/{id}")
    public ResponseEntity<OperacaoDTO> update(@PathVariable Long id, @RequestBody OperacaoDTO operacaoDTO) {
        OperacaoDTO operacaoAtualizada = operacaoService.update(id, operacaoDTO);
        return ResponseEntity.ok(operacaoAtualizada);
    }

    // Deletar uma operação
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        operacaoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
