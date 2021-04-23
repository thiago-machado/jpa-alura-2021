package br.com.alura.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	/*
	 * Como no persistence.xm estamos definindo transaction-type="RESOURCE_LOCAL",
	 * precisamos sempre iniciar e commitar uma transa��o
	 * 
	 * Caso estiv�ssemos utilizando transaction-type="JTA", que significaria que
	 * estar�amos utilizando um servidor de aplica��o, abrir e commitar transa��es
	 * n�o seria preciso.
	 */
	private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("loja");

	public static EntityManager getEntityManager() {
		return FACTORY.createEntityManager();
	}
}
