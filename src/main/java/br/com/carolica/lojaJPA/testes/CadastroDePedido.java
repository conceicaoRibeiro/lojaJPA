package br.com.carolica.lojaJPA.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.carolica.lojaJPA.dao.CategoriaDao;
import br.com.carolica.lojaJPA.dao.ClienteDao;
import br.com.carolica.lojaJPA.dao.PedidoDao;
import br.com.carolica.lojaJPA.dao.ProdutoDao;
import br.com.carolica.lojaJPA.modelo.Categoria;
import br.com.carolica.lojaJPA.modelo.Cliente;
import br.com.carolica.lojaJPA.modelo.ItemPedido;
import br.com.carolica.lojaJPA.modelo.Pedido;
import br.com.carolica.lojaJPA.modelo.Produto;
import br.com.carolica.lojaJPA.util.JPAUtil;
import br.com.carolica.lojaJPA.vo.RelatorioDeVendasVo;

public class CadastroDePedido {

	public static void main(String[] args) {
		popularBancoDeDados();
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		ClienteDao clienteDao = new ClienteDao(em);

		Produto produto = produtoDao.buscarPorId(64l);
		Produto produto2 = produtoDao.buscarPorId(65l);
		Produto produto3 = produtoDao.buscarPorId(63l);

		Cliente cliente = clienteDao.buscarPorId(12l);

		em.getTransaction().begin();

		Pedido pedido = new Pedido(cliente);
		pedido.adicionarItem(new ItemPedido(10, pedido, produto));
		pedido.adicionarItem(new ItemPedido(1, pedido, produto2));
		pedido.adicionarItem(new ItemPedido(1, pedido, produto3));

		Pedido pedido2 = new Pedido(cliente);
		pedido.adicionarItem(new ItemPedido(1, pedido2, produto3));

		PedidoDao pedidoDao = new PedidoDao(em);
		//pedidoDao.cadastrar(pedido);

		em.getTransaction().commit();

		BigDecimal totalVendido = pedidoDao.valorTotaVendido();
		System.out.println("VALOR TOTAL: " + totalVendido);

		List<RelatorioDeVendasVo> relatorio = pedidoDao.relatorioDevendas();
		relatorio.forEach(System.out::println);
		
		pedido2 =pedidoDao.buscarPedidoComCliente(1l);
		

		em.close();
		System.out.println(pedido2.getCliente().getNome());
	}

	private static void popularBancoDeDados() {
		Categoria celulares = new Categoria("CELULARES");
		Categoria videogames = new Categoria("VIDEOGAME");
		Categoria informatica = new Categoria("INFORMATICA");

		Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares);
		Produto videogame = new Produto("PS5", "PlayStation 5", new BigDecimal("4.500"), videogames);
		Produto dell = new Produto("Dell", "Dell Latitude 320", new BigDecimal("5.000"), informatica);

		Cliente cliente = new Cliente("Thereza Chaninha Jade Ribeiro", "100-001-009-CAT");

		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		ClienteDao clienteDao = new ClienteDao(em);
		em.getTransaction().begin();

//		categoriaDao.cadastrar(celulares);
//		categoriaDao.cadastrar(videogames);
//		categoriaDao.cadastrar(informatica);
//		
//		produtoDao.cadastrar(celular);
//		produtoDao.cadastrar(videogame);
//		produtoDao.cadastrar(dell);
//		
//		clienteDao.cadastrar(cliente);

		em.getTransaction().commit();
		em.close();
	}
}