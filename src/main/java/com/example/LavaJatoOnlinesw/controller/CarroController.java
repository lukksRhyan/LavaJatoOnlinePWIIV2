package com.example.LavaJatoOnlinesw.controller;

import com.example.LavaJatoOnlinesw.DTO.CarroDTO;
import com.example.LavaJatoOnlinesw.Service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carros")
public class CarroController {

    @Autowired
    private CarroService carroService;

    // Listar todos os carros
    @GetMapping
    public List<CarroDTO> findAll() {
        return carroService.findAll();
    }

    // Encontrar carro pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<CarroDTO> findById(@PathVariable Long id) {
        CarroDTO carroDTO = carroService.findById(id);
        return ResponseEntity.ok(carroDTO);
    }

    // Criar um novo carro
    @PostMapping
    public ResponseEntity<CarroDTO> create(@RequestBody CarroDTO carroDTO) {
        CarroDTO novoCarro = carroService.create(carroDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCarro);
    }

    // Atualizar um carro existente
    @PutMapping("/{id}")
    public ResponseEntity<CarroDTO> update(@PathVariable Long id, @RequestBody CarroDTO carroDTO) {
        CarroDTO carroAtualizado = carroService.update(id, carroDTO);
        return ResponseEntity.ok(carroAtualizado);
    }

    // Deletar um carro
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        carroService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
