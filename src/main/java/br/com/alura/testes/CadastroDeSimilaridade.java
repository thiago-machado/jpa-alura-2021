package br.com.alura.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.alura.model.Categoria;
import br.com.alura.model.Informatica;
import br.com.alura.model.Livro;
import br.com.alura.model.Produto;
import br.com.alura.model.ProdutoSimilaridade;
import br.com.alura.model.ProdutoSimilaridadeId;
import br.com.alura.model.StatusEnum;
import br.com.alura.model.dao.CategoriaDao;
import br.com.alura.model.dao.ProdutoDao;
import br.com.alura.util.JPAUtil;

public class CadastroDeSimilaridade {

	public static void main(String[] args) {

		Categoria categoria = new Categoria("DIVERSOS");
		Produto celular = criarProdutoInformatica("LG X POWER", "Celular da LG", new BigDecimal(1090.90), categoria, "LG", "X POWER");
		Produto livro = criarProdutoLivro("Senhor dos Anéis", "Livro do Tolkien", new BigDecimal(100.90), categoria, "J.R.R.Tolkien", "Levante", 810);
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		em.getTransaction().begin();
		
		categoriaDao.salvar(categoria);
		produtoDao.salvar(celular);
		produtoDao.salvar(livro);
		
		ProdutoSimilaridade similar = new ProdutoSimilaridade(celular.getId(), livro.getId(), true);
		em.persist(similar);
		
		ProdutoSimilaridade s = em.find(ProdutoSimilaridade.class, new ProdutoSimilaridadeId(celular.getId(), livro.getId()));
		System.out.println("RUIM? " + s.getRuim());
		
		em.getTransaction().commit();
		em.close();
	}

	private static Produto criarProdutoInformatica(String nome, String descricao, BigDecimal preco, Categoria categoria,
			String marca, String modelo) {
		Informatica produto = new Informatica(marca, modelo);
		produto.setNome(nome);
		produto.setDescricao(descricao);
		produto.setPreco(preco);
		produto.setStatus(StatusEnum.ATIVO);
		produto.setCategoria(categoria);
		return produto;
	}

	private static Produto criarProdutoLivro(String nome, String descricao, BigDecimal preco, Categoria categoria,
			String autor, String editora, Integer paginas) {
		Livro produto = new Livro(nome, editora, paginas);
		produto.setNome(nome);
		produto.setDescricao(descricao);
		produto.setPreco(preco);
		produto.setStatus(StatusEnum.ATIVO);
		produto.setCategoria(categoria);
		return produto;
	}
}
