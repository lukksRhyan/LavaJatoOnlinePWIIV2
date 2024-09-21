package com.example.LavaJatoOnlinesw.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table (name = "servico")
public class Operacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String descricao ;
    private double preco;
	private Integer duracao;
	public Operacao(String descricao, double preco, Integer duracao) {
		super();
		this.descricao = descricao;
		this.preco = preco;
		this.duracao = duracao;
	}

	public Operacao() {

	}
}