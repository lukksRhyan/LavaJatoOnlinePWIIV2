package com.example.LavaJatoOnlinesw.model;

import java.time.LocalDateTime;
import java.util.List;

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


	@Column(name = "cliente_id")
	private Long clienteId;

	private Integer duracaoTotal;

	private Double valorTotal;

	@ManyToOne
	@JoinColumn(name = "carro_id")
	private Carro carro;

	@ManyToMany
	@JoinTable(
			name = "agendamento_servico",
			joinColumns = @JoinColumn(name = "agendamento_id"),
			inverseJoinColumns = @JoinColumn(name = "servico_id")
	)
	private List<Operacao> operacoes;
    
    
	public Agendamento( LocalDateTime dataHora,  Cliente cliente, Carro carro ,List<Operacao> operacoes) {
		super();
		this.dataHora = dataHora;
		this.clienteId = cliente.getId();/*trocar por id*/
		this.carro = carro;
		this.operacoes = operacoes;/*trocar por id*/

		Integer subtotalDuracao = 0;
		Double subtotalValor = 0.0;

		for(Operacao operacao : operacoes){
			subtotalDuracao += operacao.getDuracao();
			subtotalValor += operacao.getPreco();
		}
		this.duracaoTotal = subtotalDuracao;
		this.valorTotal = subtotalValor;
	}

	public Agendamento() {

	}
}