package com.example.LavaJatoOnlinesw.Service;

import com.example.LavaJatoOnlinesw.DTO.OperacaoDTO;
import com.example.LavaJatoOnlinesw.model.Operacao;
import com.example.LavaJatoOnlinesw.repository.OperacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OperacaoService {

    @Autowired
    private OperacaoRepository operacaoRepository;

    // Listar todas as operações
    public List<OperacaoDTO> findAll() {
        List<Operacao> operacoes = operacaoRepository.findAll();
        return operacoes.stream()
                .map(OperacaoDTO::fromEntity)
                .collect(Collectors.toList());
    }

    // Encontrar uma operação pelo ID
    public OperacaoDTO findById(Long id) {
        Operacao operacao = operacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Operação não encontrada"));
        return OperacaoDTO.fromEntity(operacao);
    }

    // Criar uma nova operação
    public OperacaoDTO create(OperacaoDTO operacaoDTO) {
        Operacao operacao = OperacaoDTO.toEntity(operacaoDTO);
        operacao = operacaoRepository.save(operacao);
        return OperacaoDTO.fromEntity(operacao);
    }

    // Atualizar uma operação existente
    public OperacaoDTO update(Long id, OperacaoDTO operacaoDTO) {
        Operacao operacaoExistente = operacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Operação não encontrada"));

        operacaoExistente.setDescricao(operacaoDTO.getDescricao());
        operacaoExistente.setPreco(operacaoDTO.getPreco());
        operacaoExistente.setDuracao(operacaoDTO.getDuracao());

        operacaoRepository.save(operacaoExistente);
        return OperacaoDTO.fromEntity(operacaoExistente);
    }


    // Deletar uma operação
    public void delete(Long id) {
        Operacao operacao = operacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Operação não encontrada"));
        operacaoRepository.delete(operacao);
    }


}
