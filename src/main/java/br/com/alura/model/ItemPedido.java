package br.com.alura.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ITENS_PEDIDO")
public class ItemPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "VALOR_UNITARIO")
	private BigDecimal valorUnitario;

	@Column(name = "QUANTIDADE")
	private Integer quantidade;

	/*
	 * Um pedido pode ter muitos ItensPedido.
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PEDIDO_ID") // Nomeando a FK
	private Pedido pedido;

	/*
	 * Um produto pode ter muitos ItensPedido.
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUTO_ID") // Nomeando a FK
	private Produto produto;

	public ItemPedido() {
	}

	public ItemPedido(Integer quantidade, Produto produto) {
		this.quantidade = quantidade;
		this.produto = produto;
		this.valorUnitario = produto.getPreco();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public BigDecimal total() {
		return valorUnitario.multiply(new BigDecimal(quantidade));
	}

}
