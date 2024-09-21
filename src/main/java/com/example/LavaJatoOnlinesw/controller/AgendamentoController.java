package com.example.LavaJatoOnlinesw.controller;

import com.example.LavaJatoOnlinesw.DTO.AgendamentoDTO;
import com.example.LavaJatoOnlinesw.Service.AgendamentoService;
import com.example.LavaJatoOnlinesw.model.Agendamento;
import com.example.LavaJatoOnlinesw.model.Carro;
import com.example.LavaJatoOnlinesw.model.Cliente;
import com.example.LavaJatoOnlinesw.model.Operacao;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {


    AgendamentoService agendamentoService;

    @GetMapping
    public List<AgendamentoDTO> getAllAgendamentos() {
        return agendamentoService.findAll();
    }

    @PostMapping("/novo")
    public AgendamentoDTO createAgendamento(@RequestParam LocalDateTime datahora,
                                         @RequestParam Cliente cliente,
                                         @RequestParam Carro carro,
                                         @RequestParam List<Operacao> operacoes) {
        return agendamentoService.create(new AgendamentoDTO(datahora,cliente.getId(),carro.getId(), operacoes));
    }

    @GetMapping("/cliente/{clienteid}")
    public List<AgendamentoDTO> getAllByCliente(Long clienteId) {
        return agendamentoService.findAllByClienteId(clienteId);
    }

    @GetMapping("/{id}")
    public AgendamentoDTO getAgendamentoById(@PathVariable Long id) {
        return agendamentoService.findById(id);
    }

    @PutMapping("/{id}")
    public AgendamentoDTO updateAgendamento(@PathVariable Long id, @RequestBody Agendamento agendamentoDetails) {
        AgendamentoDTO agendamentoDTO = agendamentoService.findById(id);
        agendamentoDTO.setDataHora(agendamentoDetails.getDataHora());
        agendamentoDTO.setClienteId(agendamentoDetails.getClienteId());
        agendamentoDTO.setOperacoes(agendamentoDetails.getOperacoes());
        return agendamentoService.create(agendamentoDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteAgendamento(@PathVariable Long id) {
        AgendamentoDTO agendamentoDTO = agendamentoService.findById(id);
        agendamentoService.delete(agendamentoDTO.getId());
    }
}
