package com.example.LavaJatoOnlinesw.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cliente")
public class Cliente {

	private String nome;
	private String telefone;
	private String email;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;



	public Cliente(String nome, String telefone, String email) {
		super();
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
	}

	public Cliente() {

	}


}
