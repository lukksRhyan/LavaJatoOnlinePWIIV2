package com.example.LavaJatoOnlinesw.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "cliente")
public class Cliente {

	private String nome;
	private String telefone;
	private String email;

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Agendamento> agendamentos;

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Carro> carros;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;



	public Cliente(String nome, String telefone, String email) {
		super();
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.carros = new ArrayList<>();
		this.agendamentos = new ArrayList<>();
	}

	public Cliente() {
		this.agendamentos = new ArrayList<>();
	}


}
