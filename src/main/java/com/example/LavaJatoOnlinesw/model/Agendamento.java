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
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;


	@ManyToOne
	@JoinColumn(name = "carro_id")
	private Carro carro;

    @ManyToOne
    private Servico servico;

    
    
	public Agendamento(Long id, LocalDateTime dataHora,  Cliente cliente, Carro carro ,Servico servico) {
		super();
		this.Id = id;
		this.dataHora = dataHora;
		this.cliente = cliente;/*trocar por id*/
		this.carro = carro;
		this.servico = servico;/*trocar por id*/
	}

	public Agendamento() {

	}
}