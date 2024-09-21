package com.example.LavaJatoOnlinesw.DTO;

import com.example.LavaJatoOnlinesw.model.Cliente;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO {

    private Long id;
    private String nome;
    private String telefone;
    private String email;


    public ClienteDTO() {}

    public ClienteDTO(Long id, String nome, String telefone, String email) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }


    // Métodos de conversão
    public static ClienteDTO fromEntity(Cliente cliente) {
        return new ClienteDTO(cliente.getId(), cliente.getNome(), cliente.getTelefone(), cliente.getEmail());
    }

    public static Cliente toEntity(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setId(clienteDTO.getId());
        cliente.setNome(clienteDTO.getNome());
        cliente.setTelefone(clienteDTO.getTelefone());
        cliente.setEmail(clienteDTO.getEmail());
        return cliente;
    }
}
