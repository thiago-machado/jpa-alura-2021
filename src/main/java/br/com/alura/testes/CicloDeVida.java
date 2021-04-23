package br.com.alura.testes;

import javax.persistence.EntityManager;

import br.com.alura.model.Categoria;
import br.com.alura.model.dao.CategoriaDao;
import br.com.alura.util.JPAUtil;

public class CicloDeVida {

	public static void main(String[] args) {

		Categoria categoria = new Categoria("Informática");

		EntityManager em = JPAUtil.getEntityManager();
		CategoriaDao categoriaDao = new CategoriaDao(em);
		
		em.getTransaction().begin();
		
		categoriaDao.salvar(categoria);
		categoria.setNome("Livros");
		em.flush(); // Sincroniza as alterações com o banco de todas as entidades que estão MANAGED
		em.clear(); // Todas as entidades MANAGED se tornarão DETACHED
		
		categoria.setNome("Manuais");
		em.flush(); // Nada será sincroniza pois "categoria" está DETACHED
		
		categoria = categoriaDao.sinconizar(categoria); // Tornando a entidade MANAGED novamente
		categoria.setNome("Manuais"); 
		em.flush(); // Com a entidade em estado MANAGED, é possível sincronizar com o banco
		
		categoriaDao.remover(categoria);
		em.flush(); // Ao remover uma entidade, seu estado é REMOVED
		em.close();
	}
}
