package com.example.LavaJatoOnlinesw.controller;

import com.example.LavaJatoOnlinesw.model.Carro;
import com.example.LavaJatoOnlinesw.model.Cliente;
import com.example.LavaJatoOnlinesw.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carros")
public class CarroController {

    @Autowired
    private CarroRepository carroRepository;

    // Listar todos os carros
    @GetMapping("/")
    public List<Carro> getAllCarros() {
        return carroRepository.findAll();
    }

    // Buscar carro por ID
    @GetMapping("/{id}")
    public ResponseEntity<Carro> getCarroById(@PathVariable Long id) {
        Optional<Carro> carro = carroRepository.findById(id);
        if (carro.isPresent()) {
            return ResponseEntity.ok(carro.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/cliente/{clienteId}")
    public List<Carro> getCarrosByClienteId(@PathVariable Long clienteId) {
        return carroRepository.findAllByProprietarioId(clienteId);
    }
    // Criar um novo carro
    @PostMapping("/novo")
    public Carro createCarro(@RequestParam String placa,
                             @RequestParam String modelo,
                             @RequestParam String cor,
                             @RequestParam Cliente cliente) {

        return carroRepository.save(new Carro(placa,modelo,cor,cliente));
    }

    // Atualizar um carro existente
    @PutMapping("/{id}")
    public ResponseEntity<Carro> updateCarro(@PathVariable Long id, Carro carroDetails) {
        Optional<Carro> carro = carroRepository.findById(id);
        if (carro.isPresent()) {
            Carro updatedCarro = carro.get();
            updatedCarro.setPlaca(carroDetails.getPlaca());
            updatedCarro.setModelo(carroDetails.getModelo());
            updatedCarro.setCor(carroDetails.getCor());
            updatedCarro.setProprietarioId(carroDetails.getProprietarioId());
            return ResponseEntity.ok(carroRepository.save(updatedCarro));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Deletar um carro
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarro(@PathVariable Long id) {
        if (carroRepository.existsById(id)) {
            carroRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}