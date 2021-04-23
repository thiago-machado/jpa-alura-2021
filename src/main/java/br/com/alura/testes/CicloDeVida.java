package br.com.alura.testes;

import javax.persistence.EntityManager;

import br.com.alura.model.Categoria;
import br.com.alura.model.dao.CategoriaDao;
import br.com.alura.util.JPAUtil;

public class CicloDeVida {

	public static void main(String[] args) {

		Categoria categoria = new Categoria("Inform�tica");

		EntityManager em = JPAUtil.getEntityManager();
		CategoriaDao categoriaDao = new CategoriaDao(em);
		
		em.getTransaction().begin();
		
		categoriaDao.salvar(categoria);
		categoria.setNome("Livros");
		em.flush(); // Sincroniza as altera��es com o banco de todas as entidades que est�o MANAGED
		em.clear(); // Todas as entidades MANAGED se tornar�o DETACHED
		
		categoria.setNome("Manuais");
		em.flush(); // Nada ser� sincroniza pois "categoria" est� DETACHED
		
		categoria = categoriaDao.sinconizar(categoria); // Tornando a entidade MANAGED novamente
		categoria.setNome("Manuais"); 
		em.flush(); // Com a entidade em estado MANAGED, � poss�vel sincronizar com o banco
		
		categoriaDao.remover(categoria);
		em.flush(); // Ao remover uma entidade, seu estado � REMOVED
		em.close();
	}
}
