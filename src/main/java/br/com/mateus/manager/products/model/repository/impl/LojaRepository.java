package br.com.mateus.manager.products.model.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.mateus.manager.products.model.entity.Loja;
import br.com.mateus.manager.products.model.repository.AbstractRepository;

public class LojaRepository extends AbstractRepository<Loja> {

	private EntityManager entityManager;

	public LojaRepository() {
		entityManager = obterConexao();
	}

	public boolean save(Loja loja) {
		try {
			entityManager.getTransaction().begin();
			if (loja.getId() == null) {
				entityManager.persist(loja);
			} else {
				entityManager.merge(loja);
			}
			entityManager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			System.err.println(e);
			return false;
		}
	}

	public Loja findById(Long id) {
		return entityManager.find(Loja.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Loja> findAll() {
		return entityManager.createQuery("FROM Loja l").getResultList();
	}

	public boolean remove(Long id) {
		try {
			Loja loja = findById(id);
			if (loja != null) {
				entityManager.getTransaction().begin();
				entityManager.remove(loja);
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
