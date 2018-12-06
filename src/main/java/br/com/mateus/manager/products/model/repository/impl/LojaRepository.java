package br.com.mateus.manager.products.model.repository.impl;

import br.com.mateus.manager.products.model.entity.Loja;
import br.com.mateus.manager.products.model.repository.AbstractRepository;

import javax.persistence.EntityManager;
import java.util.List;

public class LojaRepository extends AbstractRepository<Loja> {

	private EntityManager entityManager;

	@SuppressWarnings("Duplicates")
	@Override
	public void save(Loja loja) {
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
		Loja loja = entityManager.find(Loja.class, id);
		entityManager.close();
		return loja;
	}

	@SuppressWarnings("unchecked")
	public List<Loja> findAll() {
		List<Loja> lojas = entityManager.createQuery("SELECT l FROM " + Loja.class.getSimpleName() + " l").getResultList();
		entityManager.close();
		return lojas;
	}

	public void remove(Long id) {
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

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
