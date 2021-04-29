package br.com.alura.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PEDIDOS")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "TOTAL")
	private BigDecimal total = BigDecimal.ZERO;

	@Column(name = "DATA")
	private LocalDate data = LocalDate.now();

	/*
	 * Um cliente pode ter muitos pedidos.
	 * Mas um pedido pode ter somente um cliente. 
	 * 
	 * Todo relacionando toOne, por padrão, é EAGER.
	 * É recomendável que seja LAZY, mas isso vai 
	 * depender do contexto da aplicação, 
	 * do uso e etc.
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CLIENTE_ID") // Nomeando a FK
	private Cliente cliente;
	
	
	/*
	 * Se precisássemos somente de uma tabela com os IDs de PEDIDO
	 * e PRODUTO, as linhas abaixo resolveriam a questão.
	 * 
	 * Contudo, nossa tabela de relacionamento possuirá mais campos e 
	 * por isso o relacionamento será de outra maneira.
	 */
	//@ManyToMany
	//@JoinTable(name = "PEDIDOS_PRODUTOS") // Nomeando Tabela e podemos nomear as chaves também se quisermos
	//private List<Produto> produtos;

	/*
	 * Um pedido tem muitos ItensPedido.
	 * 
	 * SOBRE O MAPPEDBY
	 * 
	 * Através da mappedby, estamos dizendo a JPA
	 * que o relacionamento já está mapeado em ItemPedido,
	 * e que o campo em ItemPedido que faz relacionamento com 
	 * essa nossa entidade (Pedido) é o atributo "pedido".
	 * 
	 * Ou seja, o mappedby diz quem é o lado forte do relacionamento.
	 * Com isso, consideguimos criar um relacionamento bidirecional.
	 * 
	 * Geralmente, é no lado do OnetoMany que inserimos o mappedBy.
	 * 
	 * SOBRE O CASCADE
	 * 
	 * cascade = CascadeType.ALL: tudo o que acontecer com pedido,
	 * terá efeito em ItemPedido. 
	 * Se excluirmos um Pedido, todos os ItemPedido serão excluídos.
	 * Se salvarmos um Pedido, todos os ItemPedido serão salvos.
	 * Dessa forma, não precisamos antes salvar um Pedido, 
	 * para depois salvarmos os ItemPedido.
	 * Basta popular uma instância de Pedido e mandar persistir.
	 * 
	 * SOBRE O LAZY
	 * 
	 * Todo relacionando toMany, por padrão, é LAZY.
	 */
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	private List<ItemPedido> itens = new ArrayList<>();
	
	public Pedido() {
	}

	public Pedido(Cliente cliente) {
		this.cliente = cliente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public void adicionarItem(ItemPedido item) {
		item.setPedido(this);
		itens.add(item);
		total = total.add(item.total());
	}

}
