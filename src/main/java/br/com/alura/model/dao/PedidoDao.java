package br.com.alura.model.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.model.Pedido;
import br.com.alura.model.dto.RelatorioVendasDto;

public class PedidoDao {

	private EntityManager em;

	public PedidoDao(EntityManager em) {
		this.em = em;
	}

	public void salvar(Pedido entity) {
		em.persist(entity);
	}

	public Pedido buscar(Long id) {
		return em.find(Pedido.class, id);
	}

	public BigDecimal buscarTotal() {
		String jpql = "SELECT SUM(p.total) FROM Pedido p";
		return em.createQuery(jpql, BigDecimal.class).getSingleResult();
	}

	/*
	 * Retorna uma lista da quantidade produtos vendidos e a �ltima data da venda
	 * realizada.
	 * 
	 * Esse m�todo retorna um lista de array de objetos. Apesar de funcionar, n�o �
	 * uma maneira elegante e f�cil de manter.
	 */
	public List<Object[]> relatorioDeVendas() {
		String jpql = "SELECT produto.nome, SUM(item.quantidade), MAX(pedido.data) " + "FROM Pedido pedido "
				+ "JOIN pedido.itens item " + "JOIN item.produto produto " + "GROUP BY produto.nome "
				+ "ORDER BY item.quantidade DESC ";
		return em.createQuery(jpql, Object[].class).getResultList();
	}

	/*
	 * A JPA permite instanciar objetos Java dentro do JPQL. No caso, estamos
	 * criando uma inst�ncia de RelatorioVendasDto, onde em seu construtor ele
	 * receber� as informa��es retornadas pelo SQL.
	 * 
	 * E para cada linha retornada, uma inst�ncia de RelatorioVendasDto ser� criada.
	 */
	public List<RelatorioVendasDto> relatorioDeVendasDto() {
		String jpql = "SELECT new br.com.alura.model.dto.RelatorioVendasDto("
				+ "produto.nome, SUM(item.quantidade), MAX(pedido.data)"
				+ ") "
				+ "FROM Pedido pedido " 
				+ "JOIN pedido.itens item " 
				+ "JOIN item.produto produto "
				+ "GROUP BY produto.nome " 
				+ "ORDER BY item.quantidade DESC ";
		return em.createQuery(jpql, RelatorioVendasDto.class).getResultList();
	}
	
	public Pedido buscarPedidoComCliente(Long id) {
		String jpql = "SELECT p FROM Pedido p JOIN FETCH p.cliente c WHERE p.id = :id";
		return em.createQuery(jpql, Pedido.class)
				.setParameter("id", id)
				.getSingleResult();
	}

}
