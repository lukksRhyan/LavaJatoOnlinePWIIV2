package com.example.LavaJatoOnlinesw.controller;

import com.example.LavaJatoOnlinesw.model.Agendamento;
import com.example.LavaJatoOnlinesw.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @GetMapping
    public List<Agendamento> getAllAgendamentos() {
        return agendamentoRepository.findAll();
    }

    @PostMapping
    public Agendamento createAgendamento(@RequestBody Agendamento agendamento) {
        return agendamentoRepository.save(agendamento);
    }

    @GetMapping("/{id}")
    public Agendamento getAgendamentoById(@PathVariable Long id) {
        return agendamentoRepository.findById(id).orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));
    }

    @PutMapping("/{id}")
    public Agendamento updateAgendamento(@PathVariable Long id, @RequestBody Agendamento agendamentoDetails) {
        Agendamento agendamento = agendamentoRepository.findById(id).orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));
        agendamento.setDataHora(agendamentoDetails.getDataHora());
        agendamento.setCliente(agendamentoDetails.getCliente());
        agendamento.setServico(agendamentoDetails.getServico());
        return agendamentoRepository.save(agendamento);
    }

    @DeleteMapping("/{id}")
    public void deleteAgendamento(@PathVariable Long id) {
        Agendamento agendamento = agendamentoRepository.findById(id).orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));
        agendamentoRepository.delete(agendamento);
    }
}
