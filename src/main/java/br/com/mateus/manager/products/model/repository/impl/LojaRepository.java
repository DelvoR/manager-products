package br.com.mateus.manager.products.model.repository.impl;

import br.com.mateus.manager.products.model.entity.Loja;
import br.com.mateus.manager.products.model.repository.AbstractRepository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Fornece Servi&ccedil;o CRUD (Create - Update - Delete) para a Entidade loja
 *
 * @since 09/11/2018
 */
public class LojaRepository extends AbstractRepository<Loja> {

	private EntityManager entityManager;

	/**
	 * Cria nova loja
	 *
	 * @param loja que ser&aacute; salva no banco dados
	 * @return retorna o mesmo objeto recebido por par&acirc;metro se houver sucesso
	 */
	@SuppressWarnings("Duplicates")
	@Override
	public Loja save(Loja loja) {
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
		return loja;
	}

	/**
	 * Atualiza uma loja existente
	 *
	 * @param loja que ser&aacute; atualizada no banco de dados
	 */
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

	/**
	 * Busca loja atrav&eacute;s do seu ID (PK)
	 *
	 * @param id da loja no banco dados
	 * @return a loja encontrada
	 */
	@Override
	public Loja findById(Long id) {
		Loja loja = entityManager.find(Loja.class, id);
		entityManager.close();
		return loja;
	}

	/**
	 * Busca todas as lojas cadastadas
	 *
	 * @return Lista com todas as lojas encontadas
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Loja> findAll() {
		List<Loja> lojas = entityManager.createQuery("SELECT l FROM " + Loja.class.getSimpleName() + " l").getResultList();
		entityManager.close();
		return lojas;
	}

	/**
	 * Remove uma loja atrav&eacute;s do seu ID (PK)
	 *
	 * @param id da loja que ser&aacute; removida
	 * @return a loja encontrada no banco de dados
	 */
	@Override
	public Loja remove(Long id) {
		Loja loja;
		try {
			loja = entityManager.find(Loja.class, id);
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
		return loja;
	}

	/**
	 * Atribui EntityManager para as conex&otilde;es com o banco de dados
	 *
	 * @param entityManager para transa&ccedil;&otilde;es
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
