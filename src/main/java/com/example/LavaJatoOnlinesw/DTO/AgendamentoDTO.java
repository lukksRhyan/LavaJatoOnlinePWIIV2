package com.example.LavaJatoOnlinesw.DTO;

import com.example.LavaJatoOnlinesw.model.Agendamento;
import com.example.LavaJatoOnlinesw.model.Carro;
import com.example.LavaJatoOnlinesw.model.Cliente;
import com.example.LavaJatoOnlinesw.model.Operacao;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class AgendamentoDTO {


    private Long id;
    private LocalDateTime dataHora;
    private Long clienteId;
    private Long carroId;
    private List<Operacao> operacoes;  // Lista de IDs de serviços associados

    public AgendamentoDTO() {}

    public AgendamentoDTO( LocalDateTime dataHora, Long clienteId, Long carroId, List<Operacao> operacoes) {
        this.dataHora = dataHora;
        this.clienteId = clienteId;
        this.carroId = carroId;
        this.operacoes = operacoes;
    }

    public static AgendamentoDTO fromEntity(Agendamento agendamento) {
        List<Operacao> operacoes = agendamento.getOperacoes();
        return new AgendamentoDTO(
                agendamento.getDataHora(),
                agendamento.getClienteId(),
                agendamento.getCarro().getId(),
                operacoes
        );
    }

    public static Agendamento toEntity(AgendamentoDTO agendamentoDTO, Cliente cliente, Carro carro, List<Operacao> operacaos) {
        Agendamento agendamento = new Agendamento();
        agendamento.setDataHora(agendamentoDTO.getDataHora());
        agendamento.setClienteId(cliente.getId());
        agendamento.setCarro(carro);
        agendamento.setOperacoes(operacaos);
        return agendamento;
    }
}
