package br.com.alura.testes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.model.Categoria;
import br.com.alura.model.Pedido;
import br.com.alura.model.Produto;
import br.com.alura.model.StatusEnum;
import br.com.alura.model.dao.CategoriaDao;
import br.com.alura.model.dao.PedidoDao;
import br.com.alura.model.dao.ProdutoDao;
import br.com.alura.util.JPAUtil;

public class BuscarProdutos {

	private static ProdutoDao produtoDao;
	private static CategoriaDao categoriaDao;
	private static PedidoDao pedidoDao;

	public static void main(String[] args) {

		EntityManager em = JPAUtil.getEntityManager();
		produtoDao = new ProdutoDao(em);
		categoriaDao = new CategoriaDao(em);
		pedidoDao = new PedidoDao(em);

		cadastroDeProdutos(em);
		
		// Buscando por ID
		System.out.println("\nSELECIONANDO PRODUTO POR ID");
		Produto livro = produtoDao.buscar(2L);
		System.out.println(livro.getNome());
		
		System.out.println("\nSELECIONANDO TODOS OS PROTUDOS");
		List<Produto> todos = produtoDao.buscar();
		todos.forEach(p -> System.out.println(p.getNome()));
		
		System.out.println("\nSELECIONANDO PELO NOME DO PROTUDO");
		todos = produtoDao.buscarPorNome("LG X Power");
		todos.forEach(p -> System.out.println(p.getNome()));
		
		System.out.println("\nSELECIONANDO PELO NOME DA CATEGORIA");
		todos = produtoDao.buscarPorNomeDaCategoria("Diversos");
		todos.forEach(p -> System.out.println(p.getNome()));

		System.out.println("\nSELECIONANDO PREÇO POR NOME DO PRODUTO");
		BigDecimal preco = produtoDao.buscarPrecoPorNome("LG X Power");
		System.out.println(preco);
		
		System.out.println("\nSELECIONANDO PREÇO POR NOME, PREÇO OU DATA");
		List<Produto> produtos = produtoDao.buscaSlecionada(null, null, LocalDate.now());
		produtos.forEach(p -> {
			System.out.println(p.getNome());
		});
		
		em.close();
	}

	private static void cadastroDeProdutos(EntityManager em) {

		Categoria categoria = new Categoria("Diversos");

		Produto cel1 = criarProduto("LG X Power", "Celular da LG", new BigDecimal(1080.90), categoria);
		Produto liv1 = criarProduto("Senhor dos Anéis", "Livro do Senhor dos ANéis", new BigDecimal(99.50), categoria);

		em.getTransaction().begin();
		categoriaDao.salvar(categoria);
		produtoDao.salvar(cel1);
		produtoDao.salvar(liv1);
		em.getTransaction().commit();
		em.clear();
	}

	private static Produto criarProduto(String nome, String descricao, BigDecimal preco, Categoria categoria) {
		Produto produto = new Produto();
		produto.setNome(nome);
		produto.setDescricao(descricao);
		produto.setPreco(preco);
		produto.setStatus(StatusEnum.ATIVO);
		produto.setCategoria(categoria);
		return produto;
	}
}
