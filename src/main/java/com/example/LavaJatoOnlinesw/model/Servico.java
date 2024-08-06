package com.example.LavaJatoOnlinesw.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Setter @Getter Long Id;
    private @Setter @Getter String nome ;
    private @Setter @Getter double preco;
	public Servico(Long id, String nome, double preco) {
		super();
		this.Id = id;
		this.nome = nome;
		this.preco = preco;
	}

	public Servico() {

	}
}