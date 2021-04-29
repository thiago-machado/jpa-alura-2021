package br.com.alura.testes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.model.StatusEnum;
import br.com.alura.model.dao.CategoriaDao;
import br.com.alura.model.dao.ClienteDao;
import br.com.alura.model.dao.PedidoDao;
import br.com.alura.model.dao.ProdutoDao;
import br.com.alura.model.Categoria;
import br.com.alura.model.Cliente;
import br.com.alura.model.ItemPedido;
import br.com.alura.model.Pedido;
import br.com.alura.model.Produto;
import br.com.alura.util.JPAUtil;

public class RelatorioDePedidos {

	private static EntityManager em = JPAUtil.getEntityManager();
	private static ProdutoDao produtoDao;
	private static CategoriaDao categoriaDao;
	private static ClienteDao clienteDao;

	public static void main(String[] args) {
		popuplarBaseDados();
		PedidoDao pedidoDao = new PedidoDao(em);

		Produto celularLG = produtoDao.buscar(1L);
		Produto celularXIAOMI = produtoDao.buscar(2L);
		Cliente elaine = clienteDao.buscar(1L);
		Cliente thiago = clienteDao.buscar(1L);
		Pedido pedidoElaine = criarPedido(elaine, Arrays.asList(celularLG));
		Pedido pedidoThiago1 = criarPedido(thiago, Arrays.asList(celularLG, celularXIAOMI));
		Pedido pedidoThiago2 = criarPedido(thiago, Arrays.asList(celularLG));
		pedidoThiago2.setData(LocalDate.now().plusDays(1));

		em.getTransaction().begin();
		pedidoDao.salvar(pedidoElaine);
		pedidoDao.salvar(pedidoThiago1);
		pedidoDao.salvar(pedidoThiago2);
		em.getTransaction().commit();

		System.out.println("TOTAL DOS PEDIDOS: " + pedidoDao.buscarTotal());

		/*
		 * O retorno deve ser:
		 * LG		9   DATA_DE_AMANHÃ
		 * XIAOMI	3	DIA_DE_HOJE
		 * 
		 */
		System.out.println("RELATÓRIO USANDO ARRAY DE OBJETOS");
		pedidoDao.relatorioDeVendas().forEach(obj -> {
			System.out.println("\n-----------------------");
			System.out.print(obj[0]);
			System.out.print(" | ");
			System.out.print(obj[1]);
			System.out.print(" | ");
			System.out.print(obj[2]);
		});
		
		System.out.println("\n\nRELATÓRIO USANDO LISTA DE DTO");
		pedidoDao.relatorioDeVendasDto().forEach(relatorio -> {
			System.out.println("\n-----------------------");
			System.out.print(relatorio.getNomeProduto());
			System.out.print(" | ");
			System.out.print(relatorio.getQuantidadeVendida());
			System.out.print(" | ");
			System.out.print(relatorio.getDataUltimaVenda());
		});
		em.close();
	}

	private static Pedido criarPedido(Cliente cliente, List<Produto> produtos) {
		Pedido pedido = new Pedido(cliente);
		produtos.forEach(produto -> pedido.adicionarItem(new ItemPedido(3, produto)));
		return pedido;
	}

	private static void popuplarBaseDados() {

		produtoDao = new ProdutoDao(em);
		categoriaDao = new CategoriaDao(em);
		clienteDao = new ClienteDao(em);

		Categoria categoria = new Categoria("Celular");
		Cliente elaine = new Cliente("Elaine", "101.102.999-00");
		Cliente thiago = new Cliente("Thiago", "991.102.999-11");

		em.getTransaction().begin();
		categoriaDao.salvar(categoria);
		produtoDao.salvar(criarProduto("LG X Power", "Celular", new BigDecimal(1080.90), categoria));
		produtoDao.salvar(criarProduto("Xiami Redmi", "Celular", new BigDecimal(2000.90), categoria));
		clienteDao.salvar(elaine);
		clienteDao.salvar(thiago);
		em.getTransaction().commit();

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
