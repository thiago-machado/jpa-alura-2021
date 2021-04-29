package br.com.alura.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/*
 * Embeddable define que essa classe � embut�vel. 
 * Ou seja, alguma Entity pode ter essa classe embutida.
 * Isso � �til quando queremos separar alguns atributos em classes
 * distintas, para n�o amontar uma classe com v�rios atributos.
 * 
 * Por exemplo: se estamos criando uma tabela de clientes, onde 
 * telefone e endere�o estar�o na mesma tabela, nada melhor que 
 * criar classes para separar as informa��es de telefone e endere�o.
 * Na base de dadps, tudo ficar� em uma s� tabela, mas no Java estar�
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
