package br.com.alura.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/*
 * Embeddable define que essa classe é embutível. 
 * Ou seja, alguma Entity pode ter essa classe embutida.
 * Isso é útil quando queremos separar alguns atributos em classes
 * distintas, para não amontar uma classe com vários atributos.
 * 
 * Por exemplo: se estamos criando uma tabela de clientes, onde 
 * telefone e endereço estarão na mesma tabela, nada melhor que 
 * criar classes para separar as informações de telefone e endereço.
 * Na base de dadps, tudo ficará em uma só tabela, mas no Java estará
 * separado.
 */
@Embeddable
public class DadosPessoais {

	@Column(name = "NOME")
	private String nome;

	@Column(name = "CPF")
	private String cpf;

	public DadosPessoais() {
	}
	
	public DadosPessoais(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

}
