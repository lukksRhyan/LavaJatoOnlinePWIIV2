package com.example.LavaJatoOnlinesw.repository;

import com.example.LavaJatoOnlinesw.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarroRepository extends JpaRepository<Carro, Long> {
    public List<Carro> findAllByProprietarioId(Long ProprietarioId);
}
