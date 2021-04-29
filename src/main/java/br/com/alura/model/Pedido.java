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
	 * Todo relacionando toOne, por padr�o, � EAGER.
	 * � recomend�vel que seja LAZY, mas isso vai 
	 * depender do contexto da aplica��o, 
	 * do uso e etc.
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CLIENTE_ID") // Nomeando a FK
	private Cliente cliente;
	
	
	/*
	 * Se precis�ssemos somente de uma tabela com os IDs de PEDIDO
	 * e PRODUTO, as linhas abaixo resolveriam a quest�o.
	 * 
	 * Contudo, nossa tabela de relacionamento possuir� mais campos e 
	 * por isso o relacionamento ser� de outra maneira.
	 */
	//@ManyToMany
	//@JoinTable(name = "PEDIDOS_PRODUTOS") // Nomeando Tabela e podemos nomear as chaves tamb�m se quisermos
	//private List<Produto> produtos;

	/*
	 * Um pedido tem muitos ItensPedido.
	 * 
	 * SOBRE O MAPPEDBY
	 * 
	 * Atrav�s da mappedby, estamos dizendo a JPA
	 * que o relacionamento j� est� mapeado em ItemPedido,
	 * e que o campo em ItemPedido que faz relacionamento com 
	 * essa nossa entidade (Pedido) � o atributo "pedido".
	 * 
	 * Ou seja, o mappedby diz quem � o lado forte do relacionamento.
	 * Com isso, consideguimos criar um relacionamento bidirecional.
	 * 
	 * Geralmente, � no lado do OnetoMany que inserimos o mappedBy.
	 * 
	 * SOBRE O CASCADE
	 * 
	 * cascade = CascadeType.ALL: tudo o que acontecer com pedido,
	 * ter� efeito em ItemPedido. 
	 * Se excluirmos um Pedido, todos os ItemPedido ser�o exclu�dos.
	 * Se salvarmos um Pedido, todos os ItemPedido ser�o salvos.
	 * Dessa forma, n�o precisamos antes salvar um Pedido, 
	 * para depois salvarmos os ItemPedido.
	 * Basta popular uma inst�ncia de Pedido e mandar persistir.
	 * 
	 * SOBRE O LAZY
	 * 
	 * Todo relacionando toMany, por padr�o, � LAZY.
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
