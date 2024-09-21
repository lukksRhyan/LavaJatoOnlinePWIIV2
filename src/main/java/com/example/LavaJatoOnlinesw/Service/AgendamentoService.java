package com.example.LavaJatoOnlinesw.Service;

import com.example.LavaJatoOnlinesw.DTO.AgendamentoDTO;
import com.example.LavaJatoOnlinesw.DTO.CarroDTO;
import com.example.LavaJatoOnlinesw.DTO.ClienteDTO;
import com.example.LavaJatoOnlinesw.DTO.OperacaoDTO;
import com.example.LavaJatoOnlinesw.model.Agendamento;
import com.example.LavaJatoOnlinesw.model.Carro;
import com.example.LavaJatoOnlinesw.model.Cliente;
import com.example.LavaJatoOnlinesw.model.Operacao;
import com.example.LavaJatoOnlinesw.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private CarroService carroService;

    @Autowired
    private OperacaoService operacaoService;  // Ajuste para a nova entidade

    // Listar todos os agendamentos
    public List<AgendamentoDTO> findAll() {
        List<Agendamento> agendamentos = agendamentoRepository.findAll();
        return agendamentos.stream()
                .map(AgendamentoDTO::fromEntity)
                .collect(Collectors.toList());
    }

    // Encontrar um agendamento pelo ID
    public AgendamentoDTO findById(Long id) {
        Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));
        return AgendamentoDTO.fromEntity(agendamento);
    }

    // Criar um novo agendamento
    public AgendamentoDTO create(AgendamentoDTO agendamentoDTO) {
        Cliente cliente = ClienteDTO.toEntity(clienteService.findById(agendamentoDTO.getClienteId()));
        Carro carro = CarroDTO.toEntity(carroService.findById(agendamentoDTO.getCarroId()));
        List<Operacao> operacoes = agendamentoDTO.getOperacoes();

        Agendamento agendamento = AgendamentoDTO.toEntity(agendamentoDTO, cliente, carro, operacoes);
        agendamento = agendamentoRepository.save(agendamento);
        return AgendamentoDTO.fromEntity(agendamento);
    }

    // Listar agendamentos por cliente ID
    public List<AgendamentoDTO> findAllByClienteId(Long clienteId) {
        List<Agendamento> agendamentos = agendamentoRepository.findAllByClienteId(clienteId);
        return agendamentos.stream()
                .map(AgendamentoDTO::fromEntity)
                .collect(Collectors.toList());
    }

    // Atualizar um agendamento existente
    public AgendamentoDTO update(Long id, AgendamentoDTO agendamentoDTO) {
        Agendamento agendamentoExistente = agendamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));

        Cliente cliente = ClienteDTO.toEntity(clienteService.findById(agendamentoDTO.getClienteId()));
        Carro carro = CarroDTO.toEntity(carroService.findById(agendamentoDTO.getCarroId()));
        List<Operacao> operacoes = agendamentoDTO.getOperacoes();

        agendamentoExistente.setDataHora(agendamentoDTO.getDataHora());
        agendamentoExistente.setClienteId(cliente.getId());
        agendamentoExistente.setCarro(carro);
        agendamentoExistente.setOperacoes(operacoes);  // Ajuste aqui

        agendamentoRepository.save(agendamentoExistente);
        return AgendamentoDTO.fromEntity(agendamentoExistente);
    }

    // Deletar um agendamento
    public void delete(Long id) {
        Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));
        agendamentoRepository.delete(agendamento);
    }
}

