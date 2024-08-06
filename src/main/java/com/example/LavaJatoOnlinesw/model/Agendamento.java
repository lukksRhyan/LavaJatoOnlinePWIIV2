package com.example.LavaJatoOnlinesw.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table (name = "agendamento")
public class Agendamento {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private LocalDateTime dataHora;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Servico servico;

    
    
	public Agendamento(Long id, LocalDateTime dataHora, Cliente cliente, Servico servico) {
		super();
		this.Id = id;
		this.dataHora = dataHora;
		this.cliente = cliente;
		this.servico = servico;
	}

	public Agendamento() {

	}


    // Getters and Setters
    
}