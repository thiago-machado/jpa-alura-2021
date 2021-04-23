package br.com.alura.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	/*
	 * Como no persistence.xm estamos definindo transaction-type="RESOURCE_LOCAL",
	 * precisamos sempre iniciar e commitar uma transação
	 * 
	 * Caso estivéssemos utilizando transaction-type="JTA", que significaria que
	 * estaríamos utilizando um servidor de aplicação, abrir e commitar transações
	 * não seria preciso.
	 */
	private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("loja");

	public static EntityManager getEntityManager() {
		return FACTORY.createEntityManager();
	}
}
