package br.com.alura.testes;

import java.math.BigDecimal;

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

public class CadastroPedido {

	private static EntityManager em = JPAUtil.getEntityManager();
	private static ProdutoDao produtoDao;
	private static CategoriaDao categoriaDao;
	private static ClienteDao clienteDao;

	public static void main(String[] args) {
		popuplarBaseDados();
		PedidoDao pedidoDao = new PedidoDao(em);
		
		Produto celularLG = produtoDao.buscar(1L);
		Produto celularXIAOMI = produtoDao.buscar(2L);
		Cliente cliente = clienteDao.buscar(1L);
		Pedido pedido = new Pedido(cliente);
		pedido.adicionarItem(new ItemPedido(10, celularLG));
		pedido.adicionarItem(new ItemPedido(5, celularXIAOMI));
		
		em.getTransaction().begin();
		pedidoDao.salvar(pedido);
		

		System.out.println("\nSELECIONANDO PEDIDO COM CLIENTE");
		Pedido pedidoBuscado = pedidoDao.buscarPedidoComCliente(1L);
		System.out.println(pedidoBuscado.getCliente().getDadosPessoais().getNome());
		
		em.getTransaction().commit();
		em.close();
	}

	private static void popuplarBaseDados() {
		
		produtoDao = new ProdutoDao(em);
		categoriaDao = new CategoriaDao(em);
		clienteDao = new ClienteDao(em);
		
		Categoria categoria = new Categoria("Celular");
		Cliente cliente = new Cliente("Elaine", "101.102.999-00");
		
		em.getTransaction().begin();
		categoriaDao.salvar(categoria);
		produtoDao.salvar(criarProduto("LG X Power", "Celular", new BigDecimal(1080.90), categoria));
		produtoDao.salvar(criarProduto("Xiami Redmi", "Celular", new BigDecimal(2000.90), categoria));
		clienteDao.salvar(cliente);
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
