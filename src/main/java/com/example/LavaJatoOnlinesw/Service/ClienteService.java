package com.example.LavaJatoOnlinesw.Service;

import com.example.LavaJatoOnlinesw.DTO.ClienteDTO;
import com.example.LavaJatoOnlinesw.model.Cliente;
import com.example.LavaJatoOnlinesw.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;


    public List<ClienteDTO> findAll() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream()
                .map(ClienteDTO::fromEntity)
                .collect(Collectors.toList());
    }


    public ClienteDTO findById(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        return ClienteDTO.fromEntity(cliente);
    }

    public ClienteDTO create(ClienteDTO clienteDTO) {
        Cliente cliente = ClienteDTO.toEntity(clienteDTO);
        cliente = clienteRepository.save(cliente);
        return ClienteDTO.fromEntity(cliente);
    }

    public ClienteDTO update(Long id, ClienteDTO clienteDTO) {
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        clienteExistente.setNome(clienteDTO.getNome());
        clienteExistente.setTelefone(clienteDTO.getTelefone());
        clienteExistente.setEmail(clienteDTO.getEmail());

        clienteRepository.save(clienteExistente);
        return ClienteDTO.fromEntity(clienteExistente);
    }


    public void delete(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        clienteRepository.delete(cliente);
    }
}
