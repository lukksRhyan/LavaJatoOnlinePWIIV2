package com.example.LavaJatoOnlinesw.model;


import  com.example.LavaJatoOnlinesw.model.Cliente;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table (name = "carro")
public class Carro {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String placa;
	private String modelo;
	private String cor;

	@ManyToOne
	private Cliente proprietario;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Carro carro = (Carro) o;
		return Objects.equals(id, carro.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, placa, modelo, cor, proprietario);
	}
}