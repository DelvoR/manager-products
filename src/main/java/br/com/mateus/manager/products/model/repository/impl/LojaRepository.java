package br.com.mateus.manager.products.model.repository.impl;

import br.com.mateus.manager.products.model.entity.Loja;
import br.com.mateus.manager.products.model.repository.AbstractRepository;

import javax.persistence.EntityManager;
import java.util.List;

public class LojaRepository extends AbstractRepository<Loja> {

	@SuppressWarnings("Duplicates")
	@Override
	public void save(Loja loja) {
		EntityManager entityManager = obterConexao();
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(loja);
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
	public void update(Loja loja) {
		EntityManager entityManager = obterConexao();
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(loja);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw e;
		} finally {
			entityManager.close();
		}
	}

	public Loja findById(Long id) {
		EntityManager entityManager = obterConexao();
		Loja loja = entityManager.find(Loja.class, id);
		entityManager.close();
		return loja;
	}

	@SuppressWarnings("unchecked")
	public List<Loja> findAll() {
		EntityManager entityManager = obterConexao();
		List<Loja> lojas = entityManager.createQuery("SELECT l FROM " + Loja.class.getSimpleName() + " l").getResultList();
		entityManager.close();
		return lojas;
	}

	public void remove(Long id) {
		EntityManager entityManager = obterConexao();
		try {
			Loja loja = entityManager.find(Loja.class, id);
			if (loja != null) {
				entityManager.getTransaction().begin();
				entityManager.remove(loja);
				entityManager.getTransaction().commit();
			}
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw e;
		} finally {
			entityManager.close();
		}
	}
}
