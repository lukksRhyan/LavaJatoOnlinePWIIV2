package com.example.LavaJatoOnlinesw.controller;

import com.example.LavaJatoOnlinesw.model.Agendamento;
import com.example.LavaJatoOnlinesw.model.Carro;
import com.example.LavaJatoOnlinesw.model.Cliente;
import com.example.LavaJatoOnlinesw.model.Servico;
import com.example.LavaJatoOnlinesw.repository.AgendamentoRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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

    @PostMapping("/novo")
    public Agendamento createAgendamento(@RequestParam LocalDateTime datahora,
                                         @RequestParam Cliente cliente,
                                         @RequestParam Carro carro,
                                         @RequestParam List<Servico> servicos) {
        return agendamentoRepository.save(new Agendamento(datahora, cliente, carro, servicos));
    }

    @GetMapping("/cliente/{clienteid}")
    public List<Agendamento> getAllByCliente(Cliente cliente) {
        return agendamentoRepository.findAllByClienteId(cliente.getId());
    }

    @GetMapping("/{id}")
    public Agendamento getAgendamentoById(@PathVariable Long id) {
        return agendamentoRepository.findById(id).orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));
    }

    @PutMapping("/{id}")
    public Agendamento updateAgendamento(@PathVariable Long id, @RequestBody Agendamento agendamentoDetails) {
        Agendamento agendamento = agendamentoRepository.findById(id).orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));
        agendamento.setDataHora(agendamentoDetails.getDataHora());
        agendamento.setClienteId(agendamentoDetails.getClienteId());
        agendamento.setServicos(agendamentoDetails.getServicos());
        return agendamentoRepository.save(agendamento);
    }

    @DeleteMapping("/{id}")
    public void deleteAgendamento(@PathVariable Long id) {
        Agendamento agendamento = agendamentoRepository.findById(id).orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));
        agendamentoRepository.delete(agendamento);
    }
}
