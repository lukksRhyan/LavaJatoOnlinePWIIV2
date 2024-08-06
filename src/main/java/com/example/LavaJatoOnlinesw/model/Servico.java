package com.example.LavaJatoOnlinesw.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table (name = "servico")
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String nome ;
    private double preco;
	public Servico(Long id, String nome, double preco) {
		super();
		this.Id = id;
		this.nome = nome;
		this.preco = preco;
	}

	public Servico() {

	}
}