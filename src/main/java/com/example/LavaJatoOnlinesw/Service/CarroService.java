package com.example.LavaJatoOnlinesw.Service;

import com.example.LavaJatoOnlinesw.DTO.CarroDTO;
import com.example.LavaJatoOnlinesw.DTO.ClienteDTO;
import com.example.LavaJatoOnlinesw.model.Carro;
import com.example.LavaJatoOnlinesw.model.Cliente;
import com.example.LavaJatoOnlinesw.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;

    @Autowired
    private ClienteService clienteService;

    public List<CarroDTO> findAll() {
        List<Carro> carros = carroRepository.findAll();
        return carros.stream()
                .map(CarroDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public CarroDTO findById(Long id) {
        Carro carro = carroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carro não encontrado"));
        return CarroDTO.fromEntity(carro);
    }

    public CarroDTO create(CarroDTO carroDTO) {
        Cliente cliente = ClienteDTO.toEntity(clienteService.findById(carroDTO.getClienteId()));
        Carro carro = CarroDTO.toEntity(carroDTO);
        carro = carroRepository.save(carro);
        return CarroDTO.fromEntity(carro);
    }

    public CarroDTO update(Long id, CarroDTO carroDTO) {
        Carro carroExistente = carroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carro não encontrado"));

        carroExistente.setPlaca(carroDTO.getPlaca());
        carroExistente.setModelo(carroDTO.getModelo());
        carroExistente.setCor(carroDTO.getCor());

        carroRepository.save(carroExistente);
        return CarroDTO.fromEntity(carroExistente);
    }

    public void delete(Long id) {
        Carro carro = carroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carro não encontrado"));
        carroRepository.delete(carro);
    }
}
