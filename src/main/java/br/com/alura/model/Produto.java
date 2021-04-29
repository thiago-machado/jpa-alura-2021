package br.com.alura.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * 
 * SOBRE O INHERITANCE
 * 
 * Inheritance significa que vamos usar essa classe Produto como herança. No
 * caso, Livro e Informatica herdam dessa classe. Além disso, precisamos passar
 * a estratégia.
 * 
 * InheritanceType.SINGLE_TABLE = vai criar uma só tabela, no caso, Produto, com
 * todos os campos definidos aqui e nas entidas que herdam da mesma. Além disso,
 * a JPA criará um campo chamado DTYPE na tabela. Essa coluna serve para que o
 * Hibernate consiga saber se o que está salvo nessa tabela é um Livro, ou um
 * produto de Informática. Obs.: Há como personalizar o nome e o tipo desse
 * campo. Por padrão, será salvo o nome da classe.
 * 
 * InheritanceType.JOINED = uma tabela será criada para Produto e para cada
 * classe que extende do mesmo. A desvantagem é que toda busca em um Livro, por
 * exemplo. precisará de um JOIN com Produto.
 *
 */
@Entity
@Table(name = "PRODUTOS")
@NamedQuery(name = "Produto.produtosPorCategria", query = "SELECT p FROM Produto p WHERE p.categoria.nome = :pNome")
@Inheritance(strategy = InheritanceType.JOINED)
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "NOME")
	private String nome;

	@Column(name = "DESCRICAO")
	private String descricao;

	@Column(name = "PRECO")
	private BigDecimal preco;

	@Column(name = "DATA_CRIACAO")
	private LocalDate dataCriacao = LocalDate.now();

	/*
	 * EnumType.ORDINAL = salva a posição da constante EnumType.STRING = salva o
	 * texto da constante
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS")
	private StatusEnum status;

	/*
	 * Uma categoria pode pertencer a muitos produtos. Ex.: categoria LIVROS pode
	 * estar vinculada aos produtos LIVRO SENHOR DOS ANÉIS, LIVRO NARNIA e assim por
	 * diante.
	 */
	@ManyToOne
	@JoinColumn(name = "CATEGORIA_ID") // Nomeando a FK
	private Categoria categoria;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}
