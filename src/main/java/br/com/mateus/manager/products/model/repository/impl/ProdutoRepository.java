package br.com.mateus.manager.products.model.repository.impl;

import br.com.mateus.manager.products.model.entity.Loja;
import br.com.mateus.manager.products.model.entity.Produto;
import br.com.mateus.manager.products.model.repository.AbstractRepository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Fornece Servi&ccedil;o CRUD (Create - Update - Delete) para a Entidade produto
 *
 * @since 09/11/2018
 */
public class ProdutoRepository extends AbstractRepository<Produto> {

	private EntityManager entityManager;

	/**
	 * Cria novo produto
	 *
	 * @param produto que ser&aacute; salva no banco dados
	 * @return retorna o mesmo objeto recebido por par&acirc;metro se houver sucesso
	 */
	@SuppressWarnings("Duplicates")
	@Override
	public Produto save(Produto produto) {
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
		return produto;
	}

	/**
	 * Atualiza uma produto existente
	 *
	 * @param produto que ser&aacute; atualizada no banco de dados
	 */
	@SuppressWarnings("Duplicates")
	@Override
	public void update(Produto produto) {
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

	/**
	 * Busca produto atrav&eacute;s do seu ID (PK)
	 *
	 * @param id da produto no banco dados
	 * @return a produto encontrada
	 */
	public Produto findById(Long id) {
		Produto produto = entityManager.find(Produto.class, id);
		entityManager.close();
		return produto;
	}

	/**
	 * Busca todos os produto cadastados
	 *
	 * @return Lista com todas os produto encontados
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Produto> findAll() {
		List<Produto> produtos = entityManager.createQuery("SELECT p FROM " + Produto.class.getSimpleName() + " p").getResultList();
		entityManager.close();
		return produtos;
	}

	/**
	 * Remove uma produto atrav&eacute;s do seu ID (PK)
	 *
	 * @param id do produto que ser&aacute; removido
	 * @return a produto encontrado no banco de dados
	 */
	@Override
	public Produto remove(Long id) {
		Produto produto;
		try {
			produto = entityManager.find(Produto.class, id);
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
		return produto;
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
