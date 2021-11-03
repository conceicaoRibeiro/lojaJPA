package br.com.carolica.lojaJPA.dao;

import javax.persistence.EntityManager;

import br.com.carolica.lojaJPA.modelo.Cliente;

public class ClienteDao {

	private EntityManager em;

	public ClienteDao(EntityManager em) {
		this.em = em;
	}

	public void cadastrar(Cliente cliente) {
		this.em.persist(cliente);
	}

	public Cliente buscarPorId(long id) {
		return em.find(Cliente.class, id);
	}

}
