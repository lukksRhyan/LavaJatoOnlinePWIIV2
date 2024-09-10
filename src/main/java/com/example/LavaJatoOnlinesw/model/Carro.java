package com.example.LavaJatoOnlinesw.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "carro")
public class Carro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String placa;
	private String modelo;
	private String cor;

	@ManyToOne
	@JoinColumn(name = "proprietario_id") // Nome da coluna de chave estrangeira
	private Cliente proprietario;

	public Carro() {
	}

	public Carro(String placa, String modelo, String cor, Cliente proprietario) {
		this.placa = placa;
		this.modelo = modelo;
		this.cor = cor;
		this.proprietario = proprietario;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Carro carro = (Carro) o;
		return Objects.equals(id, carro.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
