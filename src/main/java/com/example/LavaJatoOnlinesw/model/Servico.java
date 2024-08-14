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
    private String descricao ;
    private double preco;
	public Servico(String descricao, double preco) {
		super();
		this.descricao = descricao;
		this.preco = preco;
	}

	public Servico() {

	}
}