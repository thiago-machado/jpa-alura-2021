package br.com.alura.model.dao;

import javax.persistence.EntityManager;

import br.com.alura.model.Categoria;

public class CategoriaDao {

	private EntityManager em;
	
	public CategoriaDao(EntityManager em) {
		this.em = em;
	}
	
	public void salvar(Categoria entity) {
		em.persist(entity);
	}
	
	public Categoria sinconizar(Categoria entity) {
		return em.merge(entity);
	}
	
	/*
	 * Como não sabemos se uma entidade está DETACHED, antes de remover,
	 * fazemos um MERGE para garantir que sua instância seja MANAGED
	 */
	public void remover(Categoria entity) {
		em.remove(sinconizar(entity));
	}
}
