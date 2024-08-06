package com.example.LavaJatoOnlinesw.model;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Id;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Getter
@Setter
@Entity
public class Cliente {

	private String nome;
	private String telefone;
	private String email;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;



	public Cliente(Long id, String nome, String telefone, String email) {
		super();
		this.Id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
	}

	public Cliente() {

	}


}
