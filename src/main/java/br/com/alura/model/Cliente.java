package br.com.alura.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CLIENTES")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	/*
	 * Estamos embutindo essa classe que possui outros atributos 
	 * que irão compor a tabela CLIENTES
	 */
	@Embedded
	private DadosPessoais dadosPessoais;

	public Cliente() {
	}

	public Cliente(String nome, String cpf) {
		this.dadosPessoais = new DadosPessoais(nome, cpf);
	}

	public Long getId() {
		return id;
	}

	public DadosPessoais getDadosPessoais() {
		return dadosPessoais;
	}
	
}
