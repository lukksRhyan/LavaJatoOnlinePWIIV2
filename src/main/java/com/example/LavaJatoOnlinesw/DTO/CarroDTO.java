package com.example.LavaJatoOnlinesw.DTO;


import com.example.LavaJatoOnlinesw.model.Carro;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarroDTO {

    private Long id;
    private String placa;
    private String modelo;
    private String cor;
    private Long clienteId;  // Apenas o ID do cliente, para associar o carro

    // Construtores
    public CarroDTO() {}

    public CarroDTO(Long id, String placa, String modelo, String cor, Long clienteId) {
        this.id = id;
        this.placa = placa;
        this.modelo = modelo;
        this.cor = cor;
        this.clienteId = clienteId;
    }


    // Métodos de conversão
    public static CarroDTO fromEntity(Carro carro) {
        return new CarroDTO(carro.getId(), carro.getPlaca(), carro.getModelo(), carro.getCor(), carro.getProprietarioId());
    }

    public static Carro toEntity(CarroDTO carroDTO) {
        Carro carro = new Carro();
        carro.setId(carroDTO.getId());
        carro.setPlaca(carroDTO.getPlaca());
        carro.setModelo(carroDTO.getModelo());
        carro.setCor(carroDTO.getCor());
        carro.setProprietarioId(carroDTO.getClienteId());  // Associar ao cliente
        return carro;
    }
}
