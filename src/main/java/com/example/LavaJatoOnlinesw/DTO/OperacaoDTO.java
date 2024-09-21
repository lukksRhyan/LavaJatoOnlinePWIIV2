package com.example.LavaJatoOnlinesw.DTO;

import com.example.LavaJatoOnlinesw.model.Operacao;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OperacaoDTO {
    private Long id;
    private String descricao;
    private Double preco;
    private Integer duracao;

    // Converte de entidade para DTO
    public static OperacaoDTO fromEntity(Operacao operacao) {
        OperacaoDTO dto = new OperacaoDTO();
        dto.setId(operacao.getId());
        dto.setDescricao(operacao.getDescricao());
        dto.setPreco(operacao.getPreco());
        dto.setDuracao(operacao.getDuracao());
        return dto;
    }

    // Converte de DTO para entidade
    public static Operacao toEntity(OperacaoDTO dto) {
        Operacao operacao = new Operacao();
        operacao.setId(dto.getId());
        operacao.setDescricao(dto.getDescricao());
        operacao.setPreco(dto.getPreco());
        operacao.setDuracao(dto.getDuracao());
        return operacao;
    }

    // Getters e setters
}
