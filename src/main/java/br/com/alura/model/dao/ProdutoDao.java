package br.com.alura.model.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.alura.model.Produto;

public class ProdutoDao {

	private EntityManager em;
	
	public ProdutoDao(EntityManager em) {
		this.em = em;
	}
	
	public void salvar(Produto entity) {
		em.persist(entity);
	}
	
	public Produto buscar(Long id) {
		return em.find(Produto.class, id);
	}
	
	public List<Produto> buscar() {
		String jpql = "SELECT p FROM Produto p";
		return em.createQuery(jpql, Produto.class).getResultList();
	}
	
	public List<Produto> buscarPorNome(String nome) {
		String jpql = "SELECT p FROM Produto p WHERE p.nome = :pNome";
		return em.createQuery(jpql, Produto.class)
				.setParameter("pNome", nome)
				.getResultList();
	}
	
	/*
	 * Fazendo uma consulta através da NamedQuery.
	 * O JPQL está inserido na entidade Produto.
	 * Para rodarmos aquele JPQL, chamaos a função createNamedQuery do EntityManager 
	 * e informamos o nome da query desejada.
	 * 
	 * Passar parâmetros não tem nada de  diferente do que vínhamos fazendo.
	 * 
	 */
	public List<Produto> buscarPorNomeDaCategoria(String nome) {
		return em.createNamedQuery("Produto.produtosPorCategria", Produto.class)
				.setParameter("pNome", nome)
				.getResultList();
	}
	
	public BigDecimal buscarPrecoPorNome(String nome) {
		String jpql = "SELECT p.preco FROM Produto p WHERE p.nome = :pNome";
		return em.createQuery(jpql, BigDecimal.class)
				.setParameter("pNome", nome)
				.getSingleResult();
	}
	
	public List<Produto> buscaSlecionada(String nome, BigDecimal preco, LocalDate data) {

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Produto> query = builder.createQuery(Produto.class);
		Root<Produto> from = query.from(Produto.class);
		
		Predicate filtros = builder.and();
		if(nome != null && !nome.trim().isEmpty()) {
			filtros = builder.and(filtros, builder.equal(from.get("nome"), nome));
		}
		if(preco != null) {
			filtros = builder.and(filtros, builder.equal(from.get("preco"), preco));
		}
		if(data != null) {
			filtros = builder.and(filtros, builder.equal(from.get("dataCriacao"), data));
		}
		query.where(filtros);
		return em.createQuery(query).getResultList();
	}
}
