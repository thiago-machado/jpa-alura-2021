package br.com.alura.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.alura.model.StatusEnum;
import br.com.alura.model.dao.CategoriaDao;
import br.com.alura.model.dao.ProdutoDao;
import br.com.alura.model.Categoria;
import br.com.alura.model.Produto;
import br.com.alura.util.JPAUtil;

public class CadastroDeProduto {

	public static void main(String[] args) {

		Categoria categoria = new Categoria("Informática");
		
		Produto celular = new Produto();
		celular.setNome("LG X Power");
		celular.setDescricao("Celular da LG");
		celular.setPreco(new BigDecimal(1080.90));
		celular.setStatus(StatusEnum.ATIVO);
		celular.setCategoria(categoria);

		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		em.getTransaction().begin();
		categoriaDao.salvar(categoria);
		produtoDao.salvar(celular);
		em.getTransaction().commit();
		em.close();
	}
}
