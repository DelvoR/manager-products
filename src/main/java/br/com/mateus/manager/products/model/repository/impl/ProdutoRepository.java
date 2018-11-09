package br.com.mateus.manager.products.model.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.mateus.manager.products.model.entity.Produto;
import br.com.mateus.manager.products.model.repository.AbstractRepository;

public class ProdutoRepository extends AbstractRepository<Produto> {

	private EntityManager entityManager;

	public ProdutoRepository() {
		entityManager = obterConexao();
	}

	public boolean save(Produto produto) {
		try {
			entityManager.getTransaction().begin();
			if (produto.getId() == null) {
				entityManager.persist(produto);
			} else {
				entityManager.merge(produto);
			}
			entityManager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			System.err.println(e);
			return false;
		}
	}

	public Produto findById(Long id) {
		return entityManager.find(Produto.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Produto> findAll() {
		return entityManager.createQuery("FROM Produto p").getResultList();
	}

	public boolean remove(Long id) {
		try {
			Produto produto = findById(id);
			if (produto != null) {
				entityManager.getTransaction().begin();
				entityManager.remove(produto);
				entityManager.getTransaction().commit();
			}
			return true;
		} catch (Exception e) {
			System.err.println(e);
			entityManager.getTransaction().rollback();
			return false;
		}
	}
}
