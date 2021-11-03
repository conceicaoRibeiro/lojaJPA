package br.com.carolica.lojaJPA.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.carolica.lojaJPA.dao.CategoriaDao;
import br.com.carolica.lojaJPA.dao.ProdutoDao;
import br.com.carolica.lojaJPA.modelo.Categoria;
import br.com.carolica.lojaJPA.modelo.Produto;
import br.com.carolica.lojaJPA.util.JPAUtil;

public class CadastroDeProduto {

	public static void main(String[] args) {
		cadastrarProduto();
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		
		Produto p = produtoDao.buscarPorId(1l);
		System.out.println(p.getPreco());
		
		List<Produto> todos = produtoDao.buscarPorNomeDaCategoria("CELULARES");
		todos.forEach(p2 -> System.out.println(p.getNome()));
	
		BigDecimal precoDoProduto = produtoDao.buscarPrecoDoProdutoComNome("Xiaomi Redmi");
		System.out.println("Preco do Produto: " +precoDoProduto);
	}

	private static void cadastrarProduto() {
		Categoria celulares = new Categoria("1234");
		Produto celular = new Produto("Samsung Galaxy", "Smatphone M32", new BigDecimal("1500"), celulares);
		Produto prod = new Produto("Xiomi XTPO", "CHINA", new BigDecimal("1500"), celulares);

		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao dao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);

		em.getTransaction().begin();
//		categoriaDao.cadastrar(celulares);
//		dao.cadastrar(celular);
//		em.persist(celulares);
//		celulares.setNome("XPTO");
//		em.flush();
//		em.merge(celulares);
//		em.clear();
//		celulares = em.merge(celulares);
//		celulares.setNome("1234");
//		em.flush();
//		em.remove(celulares);
//		em.flush();
		em.getTransaction().commit();
		em.close();
	}

}
