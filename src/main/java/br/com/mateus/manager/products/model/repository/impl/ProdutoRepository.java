package br.com.mateus.manager.products.model.repository.impl;

import br.com.mateus.manager.products.model.entity.Loja;
import br.com.mateus.manager.products.model.entity.Produto;
import br.com.mateus.manager.products.model.repository.AbstractRepository;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import java.util.List;

public class ProdutoRepository extends AbstractRepository<Produto> {


	@SuppressWarnings("Duplicates")
	@Override
	public void save(Produto produto) {
		EntityManager entityManager = obterConexao();
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(produto);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw e;
		} finally {
			entityManager.close();
		}
	}

	@SuppressWarnings("Duplicates")
	@Override
	public void update(Produto produto) {
		EntityManager entityManager = obterConexao();
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(produto);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw e;
		} finally {
			entityManager.close();
		}
	}

	public Produto findById(Long id) {
		EntityManager entityManager = obterConexao();
		Produto produto = entityManager.find(Produto.class, id);
		entityManager.close();
		return produto;
	}

	@SuppressWarnings("unchecked")
	public List<Produto> findAll() {
		EntityManager entityManager = obterConexao();
		List<Produto> produtos = entityManager.createQuery("SELECT p FROM " + Produto.class.getSimpleName() + " p").getResultList();
		entityManager.close();
		return produtos;
	}

	public void remove(Long id) {
		EntityManager entityManager = obterConexao();
		try {
			Produto produto = entityManager.find(Produto.class, id);
			entityManager.getTransaction().begin();
			Loja loja = produto.getLoja();
			entityManager.remove(produto);
			loja.getProdutos().remove(produto);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw e;
		} finally {
			entityManager.close();
		}
	}
}
