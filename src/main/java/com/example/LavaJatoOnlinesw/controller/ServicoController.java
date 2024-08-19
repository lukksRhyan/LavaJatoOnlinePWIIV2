package com.example.LavaJatoOnlinesw.controller;

import com.example.LavaJatoOnlinesw.model.Servico;
import com.example.LavaJatoOnlinesw.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servicos")
public class ServicoController {

    @Autowired
    private ServicoRepository servicoRepository;

    @GetMapping
    public List<Servico> getAllServicos() {
        return servicoRepository.findAll();
    }

    @PostMapping
    public Servico createServico(@RequestParam String descricao, @RequestParam Double preco) {

        return servicoRepository.save(new Servico());
    }

    @GetMapping("/{id}")
    public Servico getServicoById(@PathVariable Long id) {
        return servicoRepository.findById(id).orElseThrow(() -> new RuntimeException("Serviço não encontrado"));
    }

    @PutMapping("/{id}")
    public Servico updateServico(@PathVariable Long id, @RequestBody Servico servicoDetails) {
        Servico servico = servicoRepository.findById(id).orElseThrow(() -> new RuntimeException("Serviço não encontrado"));
        servico.setDescricao(servicoDetails.getDescricao());
        servico.setPreco(servicoDetails.getPreco());
        return servicoRepository.save(servico);
    }

    @DeleteMapping("/{id}")
    public void deleteServico(@PathVariable Long id) {
        Servico servico = servicoRepository.findById(id).orElseThrow(() -> new RuntimeException("Serviço não encontrado"));
        servicoRepository.delete(servico);
    }
}
