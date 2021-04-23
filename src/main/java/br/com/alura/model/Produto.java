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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUTOS")
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
	 * Uma categoria pode pertencer a muitos produtos.
	 * Ex.: categoria LIVROS pode estar vinculada aos produtos 
	 * LIVRO SENHOR DOS ANÉIS, LIVRO NARNIA e assim por diante.
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
