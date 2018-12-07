package br.com.mateus.manager.products.model.repository;

import java.util.List;

/**
 * Abstra&ccedil;&atilde;o dos m&eacute;todos CRUD (Create - Update - Delete)
 *
 * @param &lt;Entity&gt; tipo da entidade relacional
 */
public abstract class AbstractRepository<Entity> {

	/**
	 * Define contrato do método usado para criar entidades
	 *
	 * @param entity Tipo da entidade relacional
	 * @return a entidade salva
	 */
	public abstract Entity save(Entity entity);

	/**
	 * Define contrato do método usado para atualizar entidades
	 *
	 * @param entity tipo da entidade relacional
	 */
	public abstract void update(Entity entity);

	/**
	 * Define contrato do método usado para buscar entidades por id (PK)
	 * @param id da entidade no banco de dados (PK)
	 * @return a entidade encontrada
	 */
	public abstract Entity findById(Long id);


	public abstract List<Entity> findAll();

	/**
	 * Define contrato do m&eacute;todo usado para remover uma entidade a partir do ID (PK)
	 *
	 * @param id da entidade relacional
	 * @return a entidade removida
	 */
	public abstract Entity remove(Long id);
}
