package br.com.alura.model.dao;

import javax.persistence.EntityManager;

import br.com.alura.model.Cliente;

public class ClienteDao {

	private EntityManager em;
	
	public ClienteDao(EntityManager em) {
		this.em = em;
	}
	
	public void salvar(Cliente entity) {
		em.persist(entity);
	}
	
	public Cliente buscar(Long id) {
		return em.find(Cliente.class, id);
	}
}
